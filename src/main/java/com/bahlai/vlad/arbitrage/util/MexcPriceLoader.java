package com.bahlai.vlad.arbitrage.util;

import Mexc.Sdk.Spot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class MexcPriceLoader {

    private final ObjectMapper objectMapper;

    public Map<String, BigDecimal> getPricesFromMexc() throws JsonProcessingException {
        Map<String, BigDecimal> prices = new HashMap<>();
        Spot spot = new Spot();
        for (JsonNode jsonNode : objectMapper.readTree(objectMapper.writeValueAsString(spot.tickerPrice()))) {
            prices.put(jsonNode.get("symbol").asText(), BigDecimal.valueOf(jsonNode.get("price").asDouble()));
        }
        return prices;
    }
}
