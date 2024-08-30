package com.voronsky.unifi4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Devices(String firmwareStatus,
                      String ip,
                      String id,
                      String mac,
                      String model,
                      String name,
                      String note,
                      String shortname,
                      String status) {
}
