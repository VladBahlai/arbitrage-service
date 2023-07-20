package com.bahlai.vlad.arbitrage.service;

import com.bahlai.vlad.arbitrage.model.BinanceSymbol;

import java.util.List;

public interface BinanceSymbolService {

    List<BinanceSymbol> getTokens();

    void saveAll(List<BinanceSymbol> symbols);

}
