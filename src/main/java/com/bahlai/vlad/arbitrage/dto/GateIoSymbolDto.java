package com.bahlai.vlad.arbitrage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GateIoSymbolDto {

    private String symbol;
    private String baseAsset;
    private String quoteAsset;
}
