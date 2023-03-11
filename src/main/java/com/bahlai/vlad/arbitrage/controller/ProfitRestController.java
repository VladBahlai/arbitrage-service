package com.bahlai.vlad.arbitrage.controller;

import com.bahlai.vlad.arbitrage.dto.SymbolProfitDto;
import com.bahlai.vlad.arbitrage.util.ProfitEvaluator;
import com.bahlai.vlad.arbitrage.util.mappers.ProfitMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@AllArgsConstructor
public class ProfitRestController {

    private final ProfitEvaluator profitEvaluator;
    private final ProfitMapper profitMapper;

    @GetMapping("/binance-mexc")
    public ResponseEntity<List<SymbolProfitDto>> getBinanceMexcProfits() throws Exception {
        return ResponseEntity.ok(profitEvaluator.getProfit().stream().map(profitMapper::convertToSymbolProfitDto).toList());
    }

}
