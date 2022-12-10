package ru.otus.processor;

import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;
import ru.otus.service.DateService;
import ru.otus.service.EventService;

public class ProcessorWithCheck implements Processor {

    private final DateService dateService;
    private final EventService eventService;

    public ProcessorWithCheck(DateService dateService, EventService eventService) {
        this.dateService = dateService;
        this.eventService = eventService;
    }

    @Override
    public Message process(Message message) {
        var s = eventService.event();
        var sec = dateService.getSeconds(s);
        System.out.println("sec: " + sec);
        if (dateService.checkSec(sec)) {
            throw new EvenSecondException("even second");
        }
        return message;
    }
}
