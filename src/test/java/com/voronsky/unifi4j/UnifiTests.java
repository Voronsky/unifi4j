package com.voronsky.unifi4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


public class UnifiTests {

    @Test
    public void GetHostTest(){
        String response;
        System.out.println("UNIFI_KEY: "+System.getenv("UNIFI_KEY"));
        Unifi unifi = new Unifi("https://api.ui.com/ea", System.getenv("UNIFI_KEY"));
        response = unifi.getHosts();
        System.out.println(response);
        assert !response.isEmpty();

    }

    @Test
    public void GetHardwareListTest(){
        List<String> response = new ArrayList<>();
        System.out.println("UNIFI_KEY: "+System.getenv("UNIFI_KEY"));
        Unifi unifi = new Unifi("https://api.ui.com/ea", System.getenv("UNIFI_KEY"));
        try {
            response = unifi.getHardwareList();
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
            assert false;
        }
        System.out.println(response);
        assert !response.isEmpty();
    }
}
