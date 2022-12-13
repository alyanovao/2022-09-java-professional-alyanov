package ru.otus.processor;

import org.junit.jupiter.api.Test;
import ru.otus.exception.EvenSecondException;
import ru.otus.model.Message;
import ru.otus.service.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessorWithCheckTest {

    private ZoneId zoneId = ZoneId.of("Europe/Moscow");
    private LocalDateTime evenDateTime = LocalDateTime.of(2022, 12, 07, 22, 40, 10, 640000);
    private LocalDateTime notEvenDateTime = LocalDateTime.of(2022, 12, 07, 22, 40, 11, 640000);

    @Test
    void checkEvenSecond() {
        DateService dateService = new DateService(zoneId);
        EventService eventService = mock(EventService.class);
        when(eventService.event()).thenReturn(evenDateTime);

        Message message = new Message.Builder(1L).build();

        var processor = new ProcessorWithCheck(dateService, eventService);
        assertThrows(EvenSecondException.class, () -> processor.process(message));
    }

    @Test
    void checkENotEvenSecond() {
        DateService dateService = new DateService(zoneId);
        EventService eventService = mock(EventService.class);
        when(eventService.event()).thenReturn(notEvenDateTime);

        Message message = new Message.Builder(1L).build();

        var processor = new ProcessorWithCheck(dateService, eventService);
        var res = processor.process(message);
        assertThat(processor.process(message)).isEqualTo(message);
    }
}