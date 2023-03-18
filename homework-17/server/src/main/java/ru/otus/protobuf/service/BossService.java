package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.generated.ExchangeServiceGrpc;
import ru.otus.protobuf.generated.MessageIn;
import ru.otus.protobuf.generated.MessageOut;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@GrpcService
public class BossService extends ExchangeServiceGrpc.ExchangeServiceImplBase {
    private final Logger log = LoggerFactory.getLogger(BossService.class);

    @Override
    public void createMessage(MessageIn request, StreamObserver<MessageOut> responseObserver) {
        System.out.println(String.format("first: %s :: last: %s", request.getFirstValue(), request.getLastValue()));
        var currentValue = new AtomicInteger(request.getFirstValue());
        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            var value = currentValue.incrementAndGet();
            log.info("send value: {}", value);
            var response = MessageOut.newBuilder().setValue(value).build();
            responseObserver.onNext(response);
            if (value == request.getLastValue()) {
                executor.shutdown();
                responseObserver.onCompleted();
                System.out.println("Закончили");
            }
        };
        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
}
