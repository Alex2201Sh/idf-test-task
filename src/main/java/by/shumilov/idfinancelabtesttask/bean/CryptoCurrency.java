package by.shumilov.idfinancelabtesttask.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CryptoCurrency {
    @Id
    private Long id;
    private String symbol;
    private String name;
    @JsonProperty(value = "nameid")
    private String nameId;
    private Integer rank;
    @JsonProperty(value = "price_usd")
    private Double priceUsd;
    @JsonProperty(value = "percent_change_24h")
    private Double percentChange24h;
    @JsonProperty(value = "percent_change_1h")
    private Double percentChange1h;
    @JsonProperty(value = "percent_change_7d")
    private Double percentChange7d;
    @JsonProperty(value = "market_cap_usd")
    private Double marketCupUsd;
    private Double volume24;
    @JsonProperty(value = "volume24_native")
    private Double volume24Native;
    @JsonProperty(value = "csupply")
    private Double cSupply;
    @JsonProperty(value = "price_btc")
    private Double priceBtc;
    @JsonProperty(value = "tsupply")
    private Double tSupply;
    @JsonProperty(value = "msupply")
    private Long mSupply;


}
