package com.bahlai.vlad.arbitrage.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "binance_symbols")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BinanceSymbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "base_asset")
    private String baseAsset;
    @Column(name = "quote_asset")
    private String quoteAsset;
}
