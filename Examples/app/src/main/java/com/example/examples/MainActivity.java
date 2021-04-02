package com.example.examples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    AnimalsServerApi animalServerApi;
    MutableLiveData<List<Animal>> findResponse;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animalServerApi = NetworkManager.getInstance().getAnimalServerApi();

        findResponse = new MutableLiveData<>();
        findResponse.observe(this, this::deleteAnimals);

        testAddFunctional();
        testFindFunctional();
    }


    private void testDeleteFunctional(Animal animal){
        System.out.println("\nНачало функции удаления для id "+animal.getId()+"\n");
        animalServerApi.deleteAnimal(animal.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("deleteFunction/success "+ response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("deleteFunction/failure "+ t.getMessage());
            }
        });
    }
    private void testFindFunctional(){
        System.out.println("\nНачало функции поиска\n");


        animalServerApi.findAnimals("", "","", "",
                "", "", "", "").enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                findResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                System.out.println("Ошибка: "+t.getMessage());
            }
        });
    }
    private void testAddFunctional(){
        System.out.println("\nНачало функции добавления\n");

        NetworkManager.getInstance().getAnimalServerApi().addAnimal(generateRandomAnimal()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void deleteAnimals(List<Animal> animals){
        System.out.println("Вывод найденных животных:");
        for (Animal animal: animals){
            System.out.println(animal.toString());
        }
        System.out.println("Удаление наденных животных:");
        for (Animal animal: animals){
            testDeleteFunctional(animal);
        }
    }

    private Animal generateRandomAnimal(){
        Animal animal = new Animal();
        Random r = new Random();

        animal.setAge(String.valueOf(r.nextFloat()));
        animal.setOwner(String.valueOf(r.nextFloat()));
        animal.setName("CHECK ADDED FROM ANDROID APP");
        animal.setBreed(String.valueOf(r.nextFloat()));
        animal.setLat(String.valueOf(r.nextFloat()));
        animal.setLon(String.valueOf(r.nextFloat()));
        animal.setPhotoPath(String.valueOf(r.nextFloat()));
        animal.setTypeAnimal(String.valueOf(r.nextFloat()));

        return animal;
    }
}
