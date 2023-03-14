package com.bahlai.vlad.arbitrage.service.impl;

import com.bahlai.vlad.arbitrage.exception.GateIoSymbolNotFoundException;
import com.bahlai.vlad.arbitrage.model.GateIoSymbol;
import com.bahlai.vlad.arbitrage.repository.GateIoSymbolRepository;
import com.bahlai.vlad.arbitrage.service.GateIoSymbolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class GateIoSymbolServiceImpl implements GateIoSymbolService {

   private final GateIoSymbolRepository repository;

    @Override
    public List<GateIoSymbol> getTokens() {
        return repository.findAll();
    }

    @Override
    public GateIoSymbol getTokenById(Long id) {
        return repository.findById(id).orElseThrow(()-> new GateIoSymbolNotFoundException(id));
    }

    @Override
    public void deleteToken(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GateIoSymbol saveToken(GateIoSymbol symbol) {
        return repository.save(symbol);
    }

    @Override
    public void saveAll(List<GateIoSymbol> symbols) {
        repository.saveAll(symbols);
    }

    @Override
    public Set<String> getAllSymbols() {
        return repository.getAllSymbols();
    }
}
