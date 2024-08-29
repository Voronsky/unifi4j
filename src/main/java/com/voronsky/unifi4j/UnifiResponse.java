package com.voronsky.unifi4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UnifiResponse(List<Data> data, String httpStatusCode, String traceId) { }
