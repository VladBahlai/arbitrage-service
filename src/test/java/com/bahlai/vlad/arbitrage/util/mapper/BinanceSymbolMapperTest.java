package com.bahlai.vlad.arbitrage.util.mapper;

import com.bahlai.vlad.arbitrage.dto.BinanceSymbolDto;
import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class BinanceSymbolMapperTest {


    @Autowired
    BinanceSymbolMapper binanceSymbolMapper;

    @Test
    void shouldConvertToBinanceSymbol() {
        BinanceSymbol expected = BinanceSymbol.builder()
                .symbol("TNT")
                .baseAsset("Test1")
                .quoteAsset("Test2")
                .build();
        BinanceSymbolDto toConvert = BinanceSymbolDto.builder()
                .symbol("TNT")
                .baseAsset("Test1")
                .quoteAsset("Test2")
                .build();
        BinanceSymbol actual = binanceSymbolMapper.convertToBinanceSymbol(toConvert);
        assertEquals(expected, actual);

    }
}
