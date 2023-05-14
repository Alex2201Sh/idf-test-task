create table public.cryptocurrency
(
    id               bigint not null
        primary key,
    csupply          double precision,
    msupply          bigint,
    marketcupusd     double precision,
    name             varchar(255),
    nameid           varchar(255),
    percentchange1h  double precision,
    percentchange24h double precision,
    percentchange7d  double precision,
    pricebtc         double precision,
    priceusd         double precision,
    rank             integer,
    symbol           varchar(255),
    tsupply          bigint,
    volume24         double precision,
    volume24native   double precision
);

alter table public.cryptocurrency
    owner to postgres;

create table public.note
(
    id                   bigserial
        primary key,
    cryptocurrencysymbol varchar(255),
    priceusd             double precision,
    username             varchar(255)
);

alter table public.note
    owner to postgres;



