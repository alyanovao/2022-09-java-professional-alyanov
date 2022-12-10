package ru.otus.listener.homework;

import ru.otus.exception.NoDataFoundException;
import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> messageMap = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        messageMap.put(msg.getId(), msg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        try {
            var res = messageMap.get(id);
            return Optional.ofNullable(res);
        }
        catch (Exception e) {
            throw new NoDataFoundException("not found message by id");
        }
    }
}
