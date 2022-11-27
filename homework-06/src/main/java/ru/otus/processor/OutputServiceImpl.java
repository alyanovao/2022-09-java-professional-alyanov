package ru.otus.processor;

import java.io.PrintStream;

public class OutputServiceImpl implements OutputService {

    private final PrintStream output;

    public OutputServiceImpl() {
        output = new PrintStream(System.out);
    }

    @Override
    public void outputMessage(String message) {
        output.println(message);
    }
}
