package com.bahlai.vlad.arbitrage;

import com.bahlai.vlad.arbitrage.service.BinanceSymbolService;
import com.bahlai.vlad.arbitrage.util.loader.BinanceSymbolLoader;
import com.bahlai.vlad.arbitrage.util.loader.GateIoSymbolLoader;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@AllArgsConstructor
public class ArbitrageApplicationRunner implements ApplicationRunner {

    private final BinanceSymbolService service;
    private final BinanceSymbolLoader binanceSymbolLoader;
    private final GateIoSymbolLoader gateIoSymbolLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (service.getTokens().isEmpty()) {
            binanceSymbolLoader.loadBinanceSymbols();
            gateIoSymbolLoader.loadGateIoSymbols();
        }
    }
}
