package com.bahlai.vlad.arbitrage.exception;

public class BinanceSymbolNotFoundException extends RuntimeException {

    public BinanceSymbolNotFoundException(Long id) {
        super("BinanceToken doesn't exist with id = " + id);
    }
}
