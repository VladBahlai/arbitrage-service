package com.bahlai.vlad.arbitrage.util;

import com.bahlai.vlad.arbitrage.model.Exchange;
import com.bahlai.vlad.arbitrage.model.ExchangePair;
import com.bahlai.vlad.arbitrage.util.loaders.prices.BinancePriceLoader;
import com.bahlai.vlad.arbitrage.util.loaders.prices.GateIoPriceLoader;
import com.bahlai.vlad.arbitrage.util.loaders.prices.MexcPriceLoader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@AllArgsConstructor
public class ExchangePairCreator {

    private final BinancePriceLoader binancePriceLoader;
    private final MexcPriceLoader mexcPriceLoader;
    private final GateIoPriceLoader gateIoPriceLoader;

    public ExchangePair createExchangePair(Exchange firstExchange, Exchange secondExchange) throws Exception {
        return ExchangePair.builder()
                .firstExchange(firstExchange)
                .secondExchange(secondExchange)
                .firstExchangePrices(getPrices(firstExchange))
                .secondExchangePrices(getPrices(secondExchange))
                .build();
    }

    private Map<String,BigDecimal> getPrices(Exchange exchange) throws Exception {
        return switch (exchange) {
            case BINANCE -> binancePriceLoader.getPricesFromBinance();
            case MEXC -> mexcPriceLoader.getPricesFromMexc();
            case GATE_IO -> gateIoPriceLoader.getGateIoPrices();
        };

    }

}
