package com.bahlai.vlad.arbitrage.util.loaders.prices;

import com.bahlai.vlad.arbitrage.service.GateIoSymbolService;
import io.gate.gateapi.ApiException;
import io.gate.gateapi.api.SpotApi;
import io.gate.gateapi.models.Ticker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class GateIoPriceLoader {

    private final GateIoSymbolService service;

    public Map<String, BigDecimal> getGateIoPrices() throws ApiException {
        SpotApi spotApi = new SpotApi();
        List<Ticker> tickers = spotApi.listTickers().execute();
        Map<String, BigDecimal> prices = new HashMap<>(tickers.size());
        for (Ticker ticker : tickers) {
            String symbol = ticker.getCurrencyPair().replace("_","");
            prices.put(symbol, new BigDecimal(ticker.getLast()));
        }
        Set<String> symbols = service.getAllSymbols();
        prices.keySet().removeIf(key -> !symbols.contains(key));
        return prices;
    }

}
