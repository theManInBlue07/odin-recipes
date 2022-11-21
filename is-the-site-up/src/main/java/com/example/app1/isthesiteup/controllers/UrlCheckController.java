package com.example.app1.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String is_site_up = "Site is up";
    private final String is_site_down = "Site is down";
    private final String incorrect_url = "URL is Incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){

        String returnMessage ="" ;
        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecodecategory = conn.getResponseCode()/100;
            if (responsecodecategory !=2 || responsecodecategory != 3 ){
                returnMessage = is_site_down;
            }else{
                returnMessage = is_site_up;
            }
        } catch (MalformedURLException e) {
            returnMessage = incorrect_url;
        } catch (IOException e) {
           returnMessage = is_site_down;
        }
            return returnMessage;

    }
}
