package com.bahlai.vlad.arbitrage.model;

public enum Market {
    BINANCE("Binance"),
    MEXC("Mexc");

    private final String marketValue;

    Market(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getMarketValue() {
        return marketValue;
    }
}
