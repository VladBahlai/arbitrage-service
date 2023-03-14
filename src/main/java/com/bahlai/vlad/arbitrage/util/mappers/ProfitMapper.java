package com.bahlai.vlad.arbitrage.util.mappers;

import com.bahlai.vlad.arbitrage.dto.SymbolProfitDto;
import com.bahlai.vlad.arbitrage.model.SymbolProfit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProfitMapper {

    public SymbolProfitDto convertToSymbolProfitDto(SymbolProfit symbolProfit) {
        return SymbolProfitDto.builder()
                .symbol(symbolProfit.getSymbol())
                .profit(String.format("%.2f%%", symbolProfit.getProfit()))
                .action("Buy on " + symbolProfit.getBuyExchange().getExchangeValue() + " -> sell on " + symbolProfit.getSellExchange().getExchangeValue())
                .build();

    }

}
