package com.bahlai.vlad.arbitrage.util;

import com.bahlai.vlad.arbitrage.model.Market;
import com.bahlai.vlad.arbitrage.model.SymbolProfit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProfitEvaluator {

    private final BinancePriceLoader binancePriceLoader;
    private final MexcPriceLoader mexcPriceLoader;

    public List<SymbolProfit> getProfit() throws Exception {
        Map<String, BigDecimal> binancePrices = binancePriceLoader.getPricesFromBinance();
        Map<String, BigDecimal> mexcPrices = mexcPriceLoader.getPricesFromMexc();
        List<SymbolProfit> profits = new ArrayList<>();

        return binancePrices.entrySet().stream()
                .filter(e -> mexcPrices.containsKey(e.getKey()))
                .map(e -> {
                    String symbol = e.getKey();
                    BigDecimal mexcPriceValue = mexcPrices.get(symbol);
                    BigDecimal binancePriceValue = e.getValue();
                    double profitValue;
                    Market buyMarket;
                    Market sellMarket;
                    if (mexcPriceValue.compareTo(binancePriceValue) > 0) {
                        profitValue = mexcPriceValue.subtract(binancePriceValue, MathContext.DECIMAL128)
                                .divide(mexcPriceValue, MathContext.DECIMAL128)
                                .multiply(BigDecimal.valueOf(100), MathContext.DECIMAL128)
                                .doubleValue();
                        buyMarket = Market.BINANCE;
                        sellMarket = Market.MEXC;
                    } else {
                        profitValue = binancePriceValue.subtract(mexcPriceValue, MathContext.DECIMAL128)
                                .divide(binancePriceValue, MathContext.DECIMAL128)
                                .multiply(BigDecimal.valueOf(100), MathContext.DECIMAL128)
                                .doubleValue();
                        buyMarket = Market.MEXC;
                        sellMarket = Market.BINANCE;
                    }
                    return SymbolProfit.builder()
                            .symbol(symbol)
                            .profit(profitValue)
                            .buyMarket(buyMarket)
                            .sellMarket(sellMarket)
                            .build();
                })
                .filter(symbolProfit -> symbolProfit.getProfit() > 2)
                .sorted(Comparator.comparing(SymbolProfit::getProfit).reversed())
                .toList();
    }
}
