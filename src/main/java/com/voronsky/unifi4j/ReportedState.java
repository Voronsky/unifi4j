package com.voronsky.unifi4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReportedState(String hostname, Hardware hardware, String ip, String Version, String state) {}
