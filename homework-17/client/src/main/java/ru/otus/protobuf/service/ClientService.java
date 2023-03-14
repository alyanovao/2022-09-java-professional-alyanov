package ru.otus.protobuf.service;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.otus.protobuf.generated.ExchangeServiceGrpc;
import ru.otus.protobuf.generated.MessageIn;
import ru.otus.protobuf.generated.MessageOut;
import ru.otus.protobuf.model.ConfigParams;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ClientService {

    private final Integer clientFirstValue;
    private final Integer clientLastValue;
    private final Integer serverFirstValue;
    private final Integer serverLastValue;

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public ClientService(ConfigParams config) {
        this.clientFirstValue = config.getClientFirstValue();
        this.clientLastValue = config.getClientLastValue();
        this.serverFirstValue = config.getServerFirstValue();
        this.serverLastValue = config.getServerLastValue();
    }

    private final AtomicInteger serverResponse = new AtomicInteger();
    private final AtomicInteger calcValue = new AtomicInteger();

    public void createMessage() {

        MessageIn inboundMessage = MessageIn.newBuilder()
                .setFirstValue(serverFirstValue)
                .setLastValue(serverLastValue)
                .build();

        run();

        ExchangeServiceGrpc.ExchangeServiceStub newStub = ExchangeServiceGrpc.newStub(serverChannel);

        newStub.createMessage(inboundMessage, new StreamObserver<>() {

            @Override
            public void onNext(MessageOut outboundMessage) {
                System.out.println("server response: " + outboundMessage.getValue());
                serverResponse.set(outboundMessage.getValue());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("Завершили!");
            }
        });

        System.out.println("Закончили");
    }

    private void run() {
        var executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            calcValue.set(0);
            for (int i = clientFirstValue; i < clientLastValue; i++) {
                int localVal = serverResponse.getAndSet(0);

                System.out.println("valueFromServer = " + localVal);

                calcValue.set(calcValue.get() + localVal + 1);
                if (serverResponse.get() > 0) serverResponse.set(0);
                System.out.println("calcValue after add increment = " + calcValue);
                System.out.println();
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
