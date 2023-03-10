package com.bahlai.vlad.arbitrage.service.impl;

import com.bahlai.vlad.arbitrage.exception.BinanceSymbolNotFoundException;
import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import com.bahlai.vlad.arbitrage.repository.BinanceSymbolRepository;
import com.bahlai.vlad.arbitrage.service.BinanceSymbolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BinanceSymbolServiceImpl implements BinanceSymbolService {

    private final BinanceSymbolRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BinanceSymbol> getTokens() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BinanceSymbol getTokenById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BinanceSymbolNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteToken(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public BinanceSymbol saveToken(BinanceSymbol token) {
        return repository.save(token);
    }

    @Override
    public void saveAll(List<BinanceSymbol> symbols) {
        repository.saveAll(symbols);
    }

}
