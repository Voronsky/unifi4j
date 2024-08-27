package com.voronsky.unifi4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

public class Unifi {
    private static final Logger log = LoggerFactory.getLogger(Unifi.class);
    private final RestClient restClient;
    private String endpoint;
    private String apiKey;

    public Unifi(String url, String apiKey){
        this.endpoint = url;
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl(this.endpoint).defaultHeaders(
                httpHeaders -> {
                    httpHeaders.set("Accept","application/json");
                    httpHeaders.set("X-API-KEY", System.getenv("UNIFI_KEY"));
                }
        ).build();
    }

    @Profile("!DataRetrieval")
    public ResponseEntity<String> getHosts() {
        try{
            log.info("Obtaining hosts from console..");
            log.info(this.endpoint);
            return this.restClient.get().uri("/hosts").retrieve().toEntity(String.class);
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

}
