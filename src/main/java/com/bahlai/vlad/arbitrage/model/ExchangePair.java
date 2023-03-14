package com.bahlai.vlad.arbitrage.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Map;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ExchangePair {

    private Exchange firstExchange;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<String, BigDecimal> firstExchangePrices;
    private Exchange secondExchange;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<String, BigDecimal> secondExchangePrices;

}
