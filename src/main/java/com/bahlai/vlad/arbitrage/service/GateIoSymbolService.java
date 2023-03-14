package com.bahlai.vlad.arbitrage.service;

import com.bahlai.vlad.arbitrage.model.GateIoSymbol;

import java.util.List;
import java.util.Set;

public interface GateIoSymbolService {

    List<GateIoSymbol> getTokens();

    GateIoSymbol getTokenById(Long id);

    void deleteToken(Long id);

    GateIoSymbol saveToken(GateIoSymbol symbol);

    void saveAll(List<GateIoSymbol> symbols);

    Set<String> getAllSymbols();
}
