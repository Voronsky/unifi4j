package com.voronsky.unifi4j;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class UnifiTests {

    @Test
    public void GetHostTest(){
        ResponseEntity<String> response;
        System.out.println("UNIFI_KEY: "+System.getenv("UNIFI_KEY"));
        Unifi unifi = new Unifi("https://api.ui.com/ea", System.getenv("UNIFI_KEY"));
        response = unifi.getHosts();
        System.out.println("Status Code:" + response.getStatusCode());
        System.out.println("Content: "+response.getBody());

        assert response.getStatusCode().is2xxSuccessful();
        assert !response.getBody().isEmpty();

    }
}
