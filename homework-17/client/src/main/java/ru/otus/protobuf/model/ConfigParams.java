package ru.otus.protobuf.model;

public class ConfigParams {
    private Integer clientFirstValue;
    private Integer clientLastValue;
    private Integer serverFirstValue;
    private Integer serverLastValue;

    public ConfigParams(Integer clientFirstValue, Integer clientLastValue, Integer serverFirstValue, Integer serverLastValue) {
        this.clientFirstValue = clientFirstValue;
        this.clientLastValue = clientLastValue;
        this.serverFirstValue = serverFirstValue;
        this.serverLastValue = serverLastValue;
    }

    public Integer getClientFirstValue() {
        return clientFirstValue;
    }

    public Integer getClientLastValue() {
        return clientLastValue;
    }

    public Integer getServerFirstValue() {
        return serverFirstValue;
    }

    public Integer getServerLastValue() {
        return serverLastValue;
    }
}
