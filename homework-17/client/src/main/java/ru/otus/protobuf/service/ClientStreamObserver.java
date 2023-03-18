package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.generated.MessageOut;

public class ClientStreamObserver implements StreamObserver<MessageOut> {
    private Logger log = LoggerFactory.getLogger(ClientStreamObserver.class);
    private int lastValue = 0;

    public void onNext(MessageOut message) {
        setLastValue(message.getValue());
    }

    @Override
    public void onError(Throwable t) {
        log.error("error: {}", t);
    }

    @Override
    public void onCompleted() {
        log.info("complete");
    }

    private synchronized void setLastValue(int value) {
        this.lastValue = value;
    }

    public synchronized int getLastValueAndReset() {
        var value = lastValue;
        lastValue = 0;
        return value;
    }
}
