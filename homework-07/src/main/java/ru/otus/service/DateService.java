package ru.otus.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateService {

    private final ZoneId zoneId;

    public DateService(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public long getSeconds(LocalDateTime dateTime) {
        ZonedDateTime zoneDateTime = dateTime.atZone(zoneId);
        return zoneDateTime.toInstant().toEpochMilli()/1000;
    }

    public boolean checkSec(long sec) {
        return sec%2 == 0;
    }
}
