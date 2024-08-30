package com.voronsky.unifi4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * Returns the raw JSON response from the hosts endpoint
     * @return JSON string
     */
    public String getHosts() {
        try{
            log.info("Obtaining hosts from console..");
            log.info(this.endpoint);
            return this.restClient.get().uri("/hosts").retrieve().body(String.class);
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

    /**
     * Returns a list of only the hardware names associated with the account
     * @return A list of hardware names
     * @throws Exception
     */
    public List<String> getHardwareList() throws Exception{
        List<String> hardwares = new ArrayList<>();
        ResponseEntity<String> json = this.restClient.get().uri("/hosts").retrieve().toEntity(String.class);
        log.debug("JSON response: "+json);

        // Map the JSON String from the response to a Json node object
        JsonNode jsonObject = new ObjectMapper().readTree(json.getBody());
        ObjectMapper mapper = new ObjectMapper();
        UnifiResponse ur = mapper.readValue(jsonObject.toString(), UnifiResponse.class);
        List<Data> data = ur.data();
        log.info("List: "+ur);
        for(Data itr: data){
            log.info("RS: "+itr.reportedState().hardware());
            hardwares.add(itr.reportedState().hardware().name());
        }

        return hardwares;
    }

    public List<Devices> getDevices() throws Exception{
        List<Devices> devices = new ArrayList<>();
        ResponseEntity<String> json = this.restClient.get().uri("/devices").retrieve().toEntity(String.class);
        log.debug("JSON response: "+ json);

        //Convert to Json Node Object to do POJO
        JsonNode jsonObject = new ObjectMapper().readTree(json.getBody());
        ObjectMapper mapper = new ObjectMapper();
        UnifiResponse ur = mapper.readValue(jsonObject.toString(), UnifiResponse.class);
        List<Data> data = ur.data();
        log.info("List: "+ur);
        // Polynomial O(N^2) , it was a list within a list in the JSON response
        for (Data dataItr: data){
            devices.addAll(dataItr.devices());
        }
        log.debug("Devices: "+devices);
        return devices;
    }

}
