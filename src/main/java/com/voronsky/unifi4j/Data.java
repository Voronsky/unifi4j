package com.voronsky.unifi4j;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(String hardwareId, String id, String ipAddress, ReportedState reportedState, List<Devices> devices) {}
