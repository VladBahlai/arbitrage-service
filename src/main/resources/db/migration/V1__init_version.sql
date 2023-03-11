CREATE TABLE binance_symbols
(
    id         bigserial PRIMARY KEY,
    symbol     text NOT NULL UNIQUE,
    base_asset  text NOT NULL,
    quote_asset text NOT null
);

