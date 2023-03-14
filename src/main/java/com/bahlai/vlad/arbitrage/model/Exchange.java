package com.bahlai.vlad.arbitrage.model;

public enum Exchange {
    BINANCE("Binance"),
    MEXC("Mexc"),
    GATE_IO("Gate.io");

    private final String exchangeValue;

    Exchange(String exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public String getExchangeValue() {
        return exchangeValue;
    }
}
