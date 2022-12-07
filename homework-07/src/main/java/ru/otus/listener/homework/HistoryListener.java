package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    private Map<Long, Message> messageQueue = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        messageQueue.put(msg.getId(), msg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        try {
            var res = messageQueue.get((Long) id);
            return Optional.ofNullable(res);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
