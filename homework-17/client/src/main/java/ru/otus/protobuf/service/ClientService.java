package ru.otus.protobuf.service;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.protobuf.generated.ExchangeServiceGrpc;
import ru.otus.protobuf.generated.MessageIn;
import ru.otus.protobuf.model.ConfigParams;


@Service
public class ClientService {
    private Logger log = LoggerFactory.getLogger(ClientService.class);

    private final int clientFirstValue;
    private final int clientLastValue;
    private final int serverFirstValue;
    private final int serverLastValue;

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public ClientService(ConfigParams config) {
        this.clientFirstValue = config.clientFirstValue();
        this.clientLastValue = config.clientLastValue();
        this.serverFirstValue = config.serverFirstValue();
        this.serverLastValue = config.serverLastValue();
    }

    private int value = 0;

    public void createMessage() {
        var asyncClient = ExchangeServiceGrpc.newStub(serverChannel);
        clientAction(asyncClient);
    }

    public void clientAction(ExchangeServiceGrpc.ExchangeServiceStub asyncClient) {
        MessageIn inboundMessage = MessageIn.newBuilder()
                .setFirstValue(serverFirstValue)
                .setLastValue(serverLastValue)
                .build();

        var responseObserver = new ClientStreamObserver();
        asyncClient.createMessage(inboundMessage, responseObserver);
        for (int i = clientFirstValue; i < clientLastValue; i++) {
            var valToPrint = getNextValue(responseObserver);
            log.info("value: {}", valToPrint);
            sleep();
        }
    }

    private int getNextValue(ClientStreamObserver clientStreamObserver) {
        var serverValue = clientStreamObserver.getLastValueAndReset();
        log.info("server value: {}", serverValue);
        value = value + serverValue + 1;
        return value;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
