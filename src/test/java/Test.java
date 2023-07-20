import com.binance.connector.client.impl.SpotClientImpl;

import java.util.LinkedHashMap;

public class Test {


    @org.junit.jupiter.api.Test
    void name() {

        SpotClientImpl spotClient = new SpotClientImpl();
        System.out.println(spotClient.createMarket().exchangeInfo(new LinkedHashMap<>()));


    }
}
