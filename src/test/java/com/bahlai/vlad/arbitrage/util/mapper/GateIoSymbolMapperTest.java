package com.bahlai.vlad.arbitrage.util.mapper;


import com.bahlai.vlad.arbitrage.dto.GateIoSymbolDto;
import com.bahlai.vlad.arbitrage.model.GateIoSymbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class GateIoSymbolMapperTest {

    @Autowired
    GateIoSymbolMapper gateIoSymbolMapper;

    @Test
    void shouldConvertToGateIoSymbol() {
        GateIoSymbol expected = GateIoSymbol.builder()
                .symbol("TNT")
                .baseAsset("Test1")
                .quoteAsset("Test2")
                .build();
        GateIoSymbolDto toConvert = GateIoSymbolDto.builder()
                .symbol("TNT")
                .baseAsset("Test1")
                .quoteAsset("Test2")
                .build();
        GateIoSymbol actual = gateIoSymbolMapper.convertToGateIoSymbol(toConvert);
        assertEquals(expected, actual);

    }


}
