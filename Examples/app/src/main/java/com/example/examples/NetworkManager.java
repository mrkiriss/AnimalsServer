package com.example.examples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static NetworkManager networkManager;
    private Retrofit retrofit;

    private NetworkManager(){
        String baseUrl = "http://10.0.2.2:8080";

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit=new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
    }

    public static NetworkManager getInstance(){
        if (networkManager==null){
            networkManager=new NetworkManager();
        }
        return networkManager;
    }

    public AnimalsServerApi getAnimalServerApi(){
        return retrofit.create(AnimalsServerApi.class);
    }


}
