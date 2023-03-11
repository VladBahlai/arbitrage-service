package com.bahlai.vlad.arbitrage;

import com.bahlai.vlad.arbitrage.service.BinanceSymbolService;
import com.bahlai.vlad.arbitrage.util.loaders.BinanceSymbolLoader;
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
    private final BinanceSymbolLoader loader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (service.getTokens().isEmpty()) {
            long start = System.currentTimeMillis();
            System.out.println("there");
            loader.loadBinanceSymbols();
            System.out.println(System.currentTimeMillis() - start);

        }
    }
}
