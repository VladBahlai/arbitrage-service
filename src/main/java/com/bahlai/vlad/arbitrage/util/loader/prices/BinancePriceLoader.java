package com.bahlai.vlad.arbitrage.util.loader.prices;

import com.bahlai.vlad.arbitrage.model.BinanceSymbol;
import com.bahlai.vlad.arbitrage.service.BinanceSymbolService;
import com.binance.connector.client.impl.SpotClientImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Component
@AllArgsConstructor
public class BinancePriceLoader {

    private final BinanceSymbolService service;
    private final ObjectMapper objectMapper;


    public Map<String, BigDecimal> getPricesFromBinance() throws Exception {
        SpotClientImpl client = new SpotClientImpl();
        List<BinanceSymbol> tokens = service.getTokens();
        Map<String, BigDecimal> prices = new HashMap<>();
        int batchSize = 500;
        for (int i = 0; i < tokens.size(); i += batchSize) {
            List<String> symbols = new ArrayList<>();
            for (int j = i; j < Math.min(i + batchSize, tokens.size()); j++) {
                symbols.add(tokens.get(j).getSymbol());
            }
            LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
            parameters.put("symbols", symbols);
            prices.putAll(readBinancePrice(client, parameters));
        }

        return prices;
    }

    private Map<String, BigDecimal> readBinancePrice(SpotClientImpl client, LinkedHashMap<String, Object> parameters) throws IOException {
        Map<String, BigDecimal> prices = new HashMap<>();
        for (JsonNode jsonNode : objectMapper.readTree(client.createMarket().tickerSymbol(parameters))) {
            prices.put(jsonNode.get("symbol").asText(), BigDecimal.valueOf(jsonNode.get("price").asDouble()));
        }
        return prices;
    }

}
