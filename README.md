# ARBITRAGE REST SERVICE (IN PROGRESS)


### Given user

- User can GET:/api/binance-mexc check all symbol pairs, and show all pairs where you can get profit from exchange Binance <---> Mexc.
- User can GET:/api/binance-gate-io check all symbol pairs, and show all pairs where you can get profit from exchange Binance <---> Gate.io.
- User can GET:/api/mexc-gate-io check all symbol pairs, and show all pairs where you can get profit from exchange Mexc <---> Gate.io.

# Future features
- Implement more cryptocurrency exchanges;
- Implement calculate fee for transactions;
- Implement calculate "Depth of Market" for accurate calculation of how you can buy/sell with given price;


# How to run application:

1. Copy project on your device.
2. Open terminal in docker folder.
3. Enter in terminal docker-compose up -d - we start app db.
4. Run application.
5. Application ready to use on localhost:8080