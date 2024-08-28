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
        ResponseEntity<String> response;
        System.out.println("UNIFI_KEY: "+System.getenv("UNIFI_KEY"));
        Unifi unifi = new Unifi("https://api.ui.com/ea", System.getenv("UNIFI_KEY"));
        response = unifi.getHosts();
        System.out.println("Status Code:" + response.getStatusCode());
        System.out.println("Content: "+response.getBody());

        assert response.getStatusCode().is2xxSuccessful();
        assert !response.getBody().isEmpty();

        try{
            JSONObject jsonObject = new JSONObject(response.getBody());
            System.out.println(jsonObject);
        } catch (JSONException err){
            System.out.println(err.toString());
        }

    }

    @Test
    public void GetHardwaresTest(){
        List<String> response = new ArrayList<>();
        System.out.println("UNIFI_KEY: "+System.getenv("UNIFI_KEY"));
        Unifi unifi = new Unifi("https://api.ui.com/ea", System.getenv("UNIFI_KEY"));
        try {
            response = unifi.getHardware();
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
            assert false;
        }
        System.out.println(response);
    }
}
