package com.bahlai.vlad.arbitrage.repository;

import com.bahlai.vlad.arbitrage.model.GateIoSymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GateIoSymbolRepository extends JpaRepository<GateIoSymbol, Long> {

    @Query("select symbol from GateIoSymbol")
    Set<String> getAllSymbols();
}
