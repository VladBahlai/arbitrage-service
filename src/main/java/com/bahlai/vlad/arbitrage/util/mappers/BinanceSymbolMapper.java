package com.bahlai.vlad.arbitrage.util.mappers;

import com.bahlai.vlad.arbitrage.dto.BinanceSymbolDto;
import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BinanceSymbolMapper {

    private final ModelMapper mapper;

    public BinanceSymbol convertToBinanceSymbol(BinanceSymbolDto binanceSymbolDto) {
        return mapper.map(binanceSymbolDto, BinanceSymbol.class);
    }
}
