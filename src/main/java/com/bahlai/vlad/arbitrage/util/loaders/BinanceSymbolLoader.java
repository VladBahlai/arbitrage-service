package com.bahlai.vlad.arbitrage.util.loaders;

import com.bahlai.vlad.arbitrage.dto.BinanceSymbolDto;
import com.bahlai.vlad.arbitrage.service.BinanceSymbolService;
import com.bahlai.vlad.arbitrage.util.mappers.BinanceSymbolMapper;
import com.binance.connector.client.impl.SpotClientImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class BinanceSymbolLoader {

    private final BinanceSymbolService service;
    private final BinanceSymbolMapper mapper;

    public void loadBinanceSymbols() throws IOException {
        service.saveAll(getTokens().stream().map(mapper::convertToBinanceSymbol).toList());
    }

    private List<BinanceSymbolDto> getTokens() throws IOException {
        SpotClientImpl client = new SpotClientImpl();
        ObjectMapper mapper = new ObjectMapper();
        List<BinanceSymbolDto> tokens = new ArrayList<>();
        JsonNode symbolsNode = mapper.readTree(client.createMarket().exchangeInfo(new LinkedHashMap<>())).get("symbols");
        symbolsNode.elements().forEachRemaining(symbolNode -> {
            if (symbolNode.path("status").asText().equals("TRADING")) {
                tokens.add(BinanceSymbolDto.builder()
                        .symbol(symbolNode.path("symbol").asText())
                        .baseAsset(symbolNode.path("baseAsset").asText())
                        .quoteAsset(symbolNode.path("quoteAsset").asText())
                        .build());
            }
        });
        return tokens;
    }
}