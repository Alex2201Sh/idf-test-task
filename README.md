# CryptoСurrency watcher

Написать простой REST-сервис просмотра котировок криптовалют

## Функциональность

* Просмотр списка доступных криптовалют (REST-метод) >>>
  _GET http://localhost:8080/currencies_
* Просмотр актуальной цены для указанной криптовалюты (REST-метод - код валюты передается пользователем) >>>
  _GET http://localhost:8080/currencies/{id}_
* Записать в лог сообщение об изменении цены более чем на 1%. Для это пользователь регистрирует себя с помощью
  REST-метода
  notify и передает свое имя(username) и код криптовалюты(symbol). В момент регистрации сохраняется текущую цена
  указанной
  криптовалюты с привязкой к пользователю. Как только актуальная цена для указанной валюты поменялась более чем на 1%.,
  в
  лог сервера выводится сообщение уровня WARN в котором указан: код валюты, имя пользователя и процент изменения цены с
  момента регистрации. >>>

<details>
  <summary>использование</summary>
 POST http://localhost:8080/currencies
  Content-Type: application/json

{
"userName": "alex",
"cryptoCurrencySymbol": "BTC"
}
</details>

## Требования

* Вы можете использовать Java или Kotlin (любой версии) >>> _Java 17_
* Используйте Spring или Spring Boot (можно использовать https://start.spring.io/ для ускорения) >>> _Spring Boot 3.0.6_
* Актуальные цены храните в реляционной базе - можно использовать любую (H2, Mysql, Postgres,…) >>> _Postgres_
* Список доступных криптовалют предопределен и является частью конфигурации сервера
  Список валют:
  [ {“id”:”90”,”symbol”:”BTC”}, {“id”:”80”,”symbol”:”ETH”}, {“id”:”48543”,”symbol”:”SOL”} ]

_resources/sql/2.fill_tables.sql_

* Раз в минуту актуальные цены для доступных криптовалют запрашиваются c внешнего источника CoinLore и сохраняются в
  базу
  данных. >>>
  _Реализовано в классе by/shumilov/idfinancelabtesttask/service/CurrencyRefresher.java при помощи аннотации
  @Scheduled_
* Что бы получить актуальные цену по коду криптовалюты используйте open API Crypto API | CoinLore
  Меthod Ticker (Specific Coin): https://api.coinlore.net/api/ticker/?id=90 (BTC)
* Когда пользователь запрашивает актуальную цену для указанной криптовалюты - данные должны быть получены из базы
  данных. >>> _Реализовано в сервисе, который запрашивает данные из репозитория_

## Примечания
* В папке resources/sql/ находятся скрипты для создания и первоначального заполнения базы данных.
* Логика оповещения в лог сервера при изменении реализована в классе 
by/shumilov/idfinancelabtesttask/service/CurrencyRefresher.java
в методе refreshData().