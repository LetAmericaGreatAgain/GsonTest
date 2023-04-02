package com.example.gsontest;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GsonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsonTestApplication.class, args);
        User user  = new User();
        user.setId(1);
        user.setName("taizi123.123");
        user.setWeight(123.9595609210659);
        Gson gson = GsonUtils.getGson();
        String json = gson.toJson(user);
        System.out.println(json);
        User user1  = gson.fromJson(json,User.class);
        System.out.println(user1);
    }

}
