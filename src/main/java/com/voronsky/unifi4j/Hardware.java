package com.voronsky.unifi4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Hardware(String name, String shortName, String firmwareVersion) {}
