package com.voronsky.unifi4j;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(String data, Long httpStatusCode, String traceId) {

}
