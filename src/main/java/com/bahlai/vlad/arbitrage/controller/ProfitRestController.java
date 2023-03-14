package com.bahlai.vlad.arbitrage.controller;

import com.bahlai.vlad.arbitrage.dto.SymbolProfitDto;
import com.bahlai.vlad.arbitrage.model.Exchange;
import com.bahlai.vlad.arbitrage.model.ExchangePair;
import com.bahlai.vlad.arbitrage.util.ExchangePairCreator;
import com.bahlai.vlad.arbitrage.util.ProfitEvaluator;
import com.bahlai.vlad.arbitrage.util.mappers.ProfitMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final ExchangePairCreator exchangePairCreator;
    private final ProfitMapper profitMapper;


    @Operation(summary = "Get list profits from Binance/MEXC exchanges.")
    @ApiResponse(responseCode = "200", description = "Received profits.",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SymbolProfitDto.class)))})
    @GetMapping("/binance-mexc")
    public ResponseEntity<List<SymbolProfitDto>> getBinanceMexcProfits() throws Exception {
        ExchangePair exchangePair = exchangePairCreator.createExchangePair(Exchange.BINANCE, Exchange.MEXC);
        return ResponseEntity.ok(profitEvaluator.getProfit(exchangePair).stream().map(profitMapper::convertToSymbolProfitDto).toList());
    }

    @Operation(summary = "Get list profits from Binance/Gate.io exchanges.")
    @ApiResponse(responseCode = "200", description = "Received profits.",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SymbolProfitDto.class)))})
    @GetMapping("/binance-gate-io")
    public ResponseEntity<List<SymbolProfitDto>> getBinanceGateIoProfits() throws Exception {
        ExchangePair exchangePair = exchangePairCreator.createExchangePair(Exchange.BINANCE, Exchange.GATE_IO);
        return ResponseEntity.ok(profitEvaluator.getProfit(exchangePair).stream().map(profitMapper::convertToSymbolProfitDto).toList());
    }

    @Operation(summary = "Get list profits from Mexc/MEXC exchanges.")
    @ApiResponse(responseCode = "200", description = "Received profits.",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SymbolProfitDto.class)))})
    @GetMapping("/mexc-gate-io")
    public ResponseEntity<List<SymbolProfitDto>> getMexcGateIoProfits() throws Exception {
        ExchangePair exchangePair = exchangePairCreator.createExchangePair(Exchange.MEXC, Exchange.GATE_IO);
        return ResponseEntity.ok(profitEvaluator.getProfit(exchangePair).stream().map(profitMapper::convertToSymbolProfitDto).toList());
    }

}
