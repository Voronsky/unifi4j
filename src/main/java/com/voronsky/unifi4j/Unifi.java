package com.voronsky.unifi4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


public class Unifi {
    private static final Logger log = LoggerFactory.getLogger(Unifi.class);
    private final RestClient restClient;
    private final String endpoint;

    public Unifi(String url, String apiKey){
        this.endpoint = url;
        this.restClient = RestClient.builder().baseUrl(this.endpoint).defaultHeaders(
                httpHeaders -> {
                    httpHeaders.set("Accept","application/json");
                    httpHeaders.set("X-API-KEY", apiKey);
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

    public List<String> getHardware() throws Exception{
        List<String> hardwares = new ArrayList<>();
        ResponseEntity<String> p = this.restClient.get().uri("/hosts").retrieve().toEntity(String.class);
        //Data p = this.restClient.get().uri("/hosts").retrieve().body(Data.class);
        log.info("Response Obj: "+p);

        // Map the JSON String from the response to a Json node object
        JsonNode jsonObject = new ObjectMapper().readTree(p.getBody());
        log.info("JSON Tree object: "+jsonObject);
        log.info("Reported State: " + jsonObject.get("data").get(0).get("reportedState").get("hardware"));
        ObjectMapper objMapper = new ObjectMapper();
        Hardware h = objMapper.treeToValue(jsonObject.get("data").get(0).get("reportedState").asText(), Hardware.class);
        hardwares.add(h.name());
        return hardwares;
    }

}
