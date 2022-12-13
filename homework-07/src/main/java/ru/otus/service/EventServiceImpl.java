package ru.otus.service;

import java.time.LocalDateTime;

public class EventServiceImpl implements EventService {

    private final DateTimeProvider dateTimeProvider;

    public EventServiceImpl(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public LocalDateTime event() {
        return dateTimeProvider.getDate();
    }
}
