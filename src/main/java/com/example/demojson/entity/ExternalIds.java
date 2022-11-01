package com.example.demojson.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalIds {
    @JsonProperty("HUTT_INC")
    private String huttInc;
    @JsonProperty("TRADE_FED")
    private String tradeFed;
}
