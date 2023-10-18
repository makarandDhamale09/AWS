package com.myproject;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RandomRecord(@JsonProperty("Index") String index,@JsonProperty("Date") String date
        ,@JsonProperty("Open") String open, @JsonProperty("High")String high
        , @JsonProperty("Low") String low,@JsonProperty("Close") String close
        ,@JsonProperty("Adj Close") String adjClose,@JsonProperty("Volume") String volume
        ,@JsonProperty("CloseUSD") String closeUsd) {
}
