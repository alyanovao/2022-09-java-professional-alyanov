package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


class ATMServiceImplTest {

    private CalculationModule calculationModule;

    private ATMService service;

    @BeforeEach
    void setUp() {
        calculationModule = spy(CalculationModule.class);
        service = spy(ATMService.class);
        doReturn(new ArrayList<>()).when(service).getCash(2400);
        doReturn(new ArrayList<>()).when(calculationModule).getCash(2400);
    }

    @Test
    void getCash() {
        service.getCash(2400);
        verify(service, times(1)).getCash(2400);
    }

    @Test
    void getSum() {
        service.getSum();
        verify(service, times(1)).getSum();
    }
}