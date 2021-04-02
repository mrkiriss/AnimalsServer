package com.mrkiriss.aserver.services;

import com.mrkiriss.aserver.data.AnimalRepository;
import com.mrkiriss.aserver.domain.Animal;
import com.mrkiriss.aserver.exceptions.AnimalAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal addAnimal(Animal animal) throws AnimalAlreadyExistException {
        if (animalRepository.findByOwnerAndName(animal.getOwner(), animal.getName())!=null){
            throw new AnimalAlreadyExistException("Животное уже существует");
        }
        return animalRepository.save(animal);
    }

    public Iterable<Animal> findAll(){
        return animalRepository.findAll();
    }

    public Long deleteById(Long id){
        animalRepository.deleteById(id);
        return id;
    }

    public Iterable<Animal> findAllBySomeParams(Map<String,String> allParams){
        Iterable<Animal> animals = animalRepository.findAll();

        String someInput = "";
        String name = "";
        String breed= "";
        String age= "";
        String lat= "";
        String lon= "";
        String typeAnimal= "";
        String owner= "";
        if (allParams.containsKey("someInput")) someInput=allParams.get("someInput");
        if (allParams.containsKey("name")) name=allParams.get("name");
        if (allParams.containsKey("breed")) breed=allParams.get("breed");
        if (allParams.containsKey("age")) age=allParams.get("age");
        if (allParams.containsKey("lat")) lat=allParams.get("lat");
        if (allParams.containsKey("lon")) lon=allParams.get("lon");
        if (allParams.containsKey("typeAnimal")) typeAnimal=allParams.get("typeAnimal");
        if (allParams.containsKey("owner")) owner=allParams.get("owner");

        List<Animal> preResult = new LinkedList<>();
        for (Animal animal:animals){
            if (
                    somethingIsPresentInAnimal(someInput, animal) && animalNearby(lat, lon, animal) &&
                    (animal.getAge().equals(name) || age.isBlank()) &&
                    (animal.getBreed().equals(breed) || breed.isBlank()) &&
                    (animal.getName().equals(name) || name.isBlank()) &&
                    (animal.getOwner().equals(owner) || owner.isBlank()) &&
                    (animal.getTypeAnimal().equals(typeAnimal) || typeAnimal.isBlank())
            ) preResult.add(animal);
            /*System.out.println("----------------");
            System.out.println("isSome "+(somethingIsPresentInAnimal(someInput, animal)));
            System.out.println("nearby "+(animalNearby(lat, lon, animal)));
            System.out.println("age "+(animal.getAge().equals(name) || age.isBlank()));
            System.out.println("breed "+(animal.getBreed().equals(breed) || breed.isBlank()));
            System.out.println("owner "+(animal.getOwner().equals(owner) || owner.isBlank()));
            System.out.println("type "+(animal.getTypeAnimal().equals(typeAnimal) || typeAnimal.isBlank()));*/
        }
        return preResult;
    }

    private boolean somethingIsPresentInAnimal(String something, Animal animal){
        if (something.isEmpty() || something.isBlank()) return true;
        return (animal.getAge().contains(something) || animal.getBreed().contains(something) || animal.getName().contains(something) ||
                animal.getOwner().contains(something) || animal.getTypeAnimal().contains(something)  );
    }
    private boolean animalNearby(String lat, String lon, Animal animal){
        if (lat.isEmpty() || lat.isBlank() || lon.isBlank() || lon.isEmpty()){
            return true;
        }else {
            // животное в круге радиусом 0,3 с центром в координатах из аргументов
            Double searchRadius = 0.3;
            Double dX = Double.parseDouble(lat)-Double.parseDouble(animal.getLat());
            Double dY = Double.parseDouble(lon)-Double.parseDouble(animal.getLon());
            return (searchRadius > Math.sqrt(dX * dX + dY * dY));
        } }
}
