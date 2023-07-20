package com.bahlai.vlad.arbitrage.util.mapper;

import com.bahlai.vlad.arbitrage.dto.SymbolProfitDto;
import com.bahlai.vlad.arbitrage.model.Exchange;
import com.bahlai.vlad.arbitrage.model.SymbolProfit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class ProfitMapperTest {

    @Autowired
    ProfitMapper profitMapper;

    @Test
    void shouldConvertToGateIoSymbol() {
        SymbolProfitDto expected = SymbolProfitDto.builder()
                .symbol("DNTTNT")
                .profit("2,20%")
                .action("Buy on Binance -> sell on Mexc")
                .build();
        SymbolProfit toConvert = SymbolProfit.builder()
                .symbol("DNTTNT")
                .profit(2.2)
                .buyExchange(Exchange.BINANCE)
                .sellExchange(Exchange.MEXC)
                .build();
        SymbolProfitDto actual = profitMapper.convertToSymbolProfitDto(toConvert);
        assertEquals(expected, actual);


    }

}
