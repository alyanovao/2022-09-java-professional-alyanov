package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.otus.protobuf.generated.ExchangeServiceGrpc;
import ru.otus.protobuf.generated.MessageIn;
import ru.otus.protobuf.generated.MessageOut;

import java.util.concurrent.TimeUnit;

@GrpcService
public class BossService extends ExchangeServiceGrpc.ExchangeServiceImplBase {

    @Override
    public void createMessage(MessageIn request, StreamObserver<MessageOut> responseObserver) {
        int firstValue = request.getFirstValue();
        int lastValue = request.getLastValue();

        System.out.println(String.format("first: %s :: last: %s", firstValue, lastValue));

        for (int i = firstValue; i < lastValue; i++) {
            MessageOut out = MessageOut.newBuilder()
                    .setValue(i)
                    .build();

            System.out.println("message: " + out.getValue());
            responseObserver.onNext(out);
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }
}
