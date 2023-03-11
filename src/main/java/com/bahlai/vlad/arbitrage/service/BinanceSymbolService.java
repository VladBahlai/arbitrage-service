package com.bahlai.vlad.arbitrage.service;

import com.bahlai.vlad.arbitrage.model.BinanceSymbol;

import java.util.List;

public interface BinanceSymbolService {

    List<BinanceSymbol> getTokens();

    BinanceSymbol getTokenById(Long id);

    void deleteToken(Long id);

    BinanceSymbol saveToken(BinanceSymbol symbol);

    void saveAll(List<BinanceSymbol> symbols);

}
