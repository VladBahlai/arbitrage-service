package com.bahlai.vlad.arbitrage.repository;

import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BinanceSymbolRepositoryTest {

    @Autowired
    BinanceSymbolRepository repository;

    @Test
    void shouldReadAndWriteBinanceSymbol() {
        BinanceSymbol expected = repository.save(BinanceSymbol.builder()
                .symbol("TEST")
                .baseAsset("TST")
                .quoteAsset("TST")
                .build());
        Optional<BinanceSymbol> actual = repository.findById(expected.getId());
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}
