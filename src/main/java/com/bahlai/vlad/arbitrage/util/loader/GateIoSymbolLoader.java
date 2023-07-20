package com.bahlai.vlad.arbitrage.util.loader;

import com.bahlai.vlad.arbitrage.dto.GateIoSymbolDto;
import com.bahlai.vlad.arbitrage.service.GateIoSymbolService;
import com.bahlai.vlad.arbitrage.util.mapper.GateIoSymbolMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.gate.gateapi.ApiException;
import io.gate.gateapi.api.SpotApi;
import io.gate.gateapi.models.CurrencyPair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class GateIoSymbolLoader {

    private final GateIoSymbolService service;
    private final GateIoSymbolMapper mapper;

    public void loadGateIoSymbols() throws ApiException, JsonProcessingException {
        service.saveAll(getGateIoSymbols().stream().map(mapper::convertToGateIoSymbol).toList());
    }


    private List<GateIoSymbolDto> getGateIoSymbols() throws ApiException {
        SpotApi apiInstance = new SpotApi();
        List<GateIoSymbolDto> gateIoSymbolDtos = new ArrayList<>();
        List<CurrencyPair> currencyPairs = apiInstance.listCurrencyPairs();
        for (CurrencyPair currencyPair : currencyPairs) {
            if (currencyPair.getTradeStatus().equals(CurrencyPair.TradeStatusEnum.TRADABLE)) {
                gateIoSymbolDtos.add(GateIoSymbolDto.builder()
                        .symbol(currencyPair.getId().replace("_",""))
                        .baseAsset(currencyPair.getBase())
                        .quoteAsset(currencyPair.getQuote())
                        .build());
            }
        }
        return gateIoSymbolDtos;
    }

}






