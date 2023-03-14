package com.bahlai.vlad.arbitrage.exception;

public class BinanceSymbolNotFoundException extends RuntimeException {

    public BinanceSymbolNotFoundException(Long id) {
        super("GateIoSymbol doesn't exist with id = " + id);
    }
}
