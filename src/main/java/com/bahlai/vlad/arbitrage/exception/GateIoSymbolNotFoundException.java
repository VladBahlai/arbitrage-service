package com.bahlai.vlad.arbitrage.exception;

public class GateIoSymbolNotFoundException extends RuntimeException {

    public GateIoSymbolNotFoundException(Long id) {
        super("GateIoSymbol doesn't exist with id = " + id);
    }

}
