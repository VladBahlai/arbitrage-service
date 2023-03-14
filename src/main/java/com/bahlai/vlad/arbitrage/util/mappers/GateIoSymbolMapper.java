package com.bahlai.vlad.arbitrage.util.mappers;

import com.bahlai.vlad.arbitrage.dto.GateIoSymbolDto;
import com.bahlai.vlad.arbitrage.model.GateIoSymbol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GateIoSymbolMapper {

    private final ModelMapper mapper;

    public GateIoSymbol convertToGateIoSymbol(GateIoSymbolDto gateIoSymbolDto) {
        return mapper.map(gateIoSymbolDto, GateIoSymbol.class);
    }

}
