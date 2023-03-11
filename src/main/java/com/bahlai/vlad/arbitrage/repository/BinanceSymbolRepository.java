package com.bahlai.vlad.arbitrage.repository;

import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinanceSymbolRepository extends JpaRepository<BinanceSymbol, Long> {

    boolean existsBySymbol(String symbol);
}
