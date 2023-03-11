package com.bahlai.vlad.arbitrage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SymbolProfit {

    private String symbol;
    private Double profit;
    private Market buyMarket;
    private Market sellMarket;
}
