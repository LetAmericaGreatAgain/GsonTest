package com.example.gsontest;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    @Bean
    public Gson gson(){
        System.out.println("Gson实例已经注入...");
        return GsonUtils.getGson();
    }
}
