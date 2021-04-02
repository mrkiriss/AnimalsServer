package com.example.examples;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimalsServerApi {

    @GET("/animals/find")
    Call<List<Animal>> findAnimals(@Query("someInput") String someInput,
                                          @Query("name") String name,
                                          @Query("breed") String breed,
                                          @Query("age") String age,
                                          @Query("lat") String lat,
                                          @Query("lon") String lon,
                                          @Query("typeAnimal") String typeAnimal,
                                          @Query("owner") String owner);

    @POST("/animals")
    Call<String> addAnimal(@Body Animal animal);

    @DELETE("/animals/{id}")
    Call<String> deleteAnimal(@Path("id") Long id);
}
