package com.example.Final_Project_mutso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKey {
    @Value("${YOUTUBE_APIKEY}")
    private String apiKey;

    public String getApiKey(){
        return apiKey;
    }

    @Override
    public String toString(){
        return "ApiKey [apiKey=" + apiKey +"]";
    }

}
