package com.bahlai.vlad.arbitrage.util;

import com.bahlai.vlad.arbitrage.model.Exchange;
import com.bahlai.vlad.arbitrage.model.ExchangePair;
import com.bahlai.vlad.arbitrage.model.SymbolProfit;
import com.bahlai.vlad.arbitrage.util.loader.prices.BinancePriceLoader;
import com.bahlai.vlad.arbitrage.util.loader.prices.GateIoPriceLoader;
import com.bahlai.vlad.arbitrage.util.loader.prices.MexcPriceLoader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProfitEvaluator {

    private final BinancePriceLoader binancePriceLoader;
    private final MexcPriceLoader mexcPriceLoader;
    private final GateIoPriceLoader gateIoPriceLoader;

    public List<SymbolProfit> getProfit(ExchangePair exchangePair){
        Map<String, BigDecimal> firstExchangePrices = exchangePair.getFirstExchangePrices();
        Map<String, BigDecimal> secondExchangePrices = exchangePair.getSecondExchangePrices();
        Exchange firstExchange = exchangePair.getFirstExchange();
        Exchange secondExchange = exchangePair.getSecondExchange();
        return firstExchangePrices.entrySet().stream()
                .filter(e -> secondExchangePrices.containsKey(e.getKey()))
                .map(e -> {
                    String symbol = e.getKey();
                    BigDecimal secondPrice = secondExchangePrices.get(symbol);
                    BigDecimal firstPrice = e.getValue();
                    double profitValue;
                    Exchange buyExchange;
                    Exchange sellExchange;
                    if (secondPrice.compareTo(firstPrice) > 0) {
                        profitValue = secondPrice.subtract(firstPrice, MathContext.DECIMAL128)
                                .divide(secondPrice, MathContext.DECIMAL128)
                                .multiply(BigDecimal.valueOf(100), MathContext.DECIMAL128)
                                .doubleValue();
                        buyExchange = firstExchange;
                        sellExchange = secondExchange;
                    } else {
                        profitValue = firstPrice.subtract(secondPrice, MathContext.DECIMAL128)
                                .divide(firstPrice, MathContext.DECIMAL128)
                                .multiply(BigDecimal.valueOf(100), MathContext.DECIMAL128)
                                .doubleValue();
                        buyExchange = secondExchange;
                        sellExchange = firstExchange;
                    }
                    return SymbolProfit.builder()
                            .symbol(symbol)
                            .profit(profitValue)
                            .buyExchange(buyExchange)
                            .sellExchange(sellExchange)
                            .build();
                })
                .filter(symbolProfit -> symbolProfit.getProfit() > 2)
                .sorted(Comparator.comparing(SymbolProfit::getProfit).reversed())
                .toList();
    }
}
