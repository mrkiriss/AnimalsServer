package com.mrkiriss.aserver.controllers;

import com.mrkiriss.aserver.data.AnimalRepository;
import com.mrkiriss.aserver.domain.Animal;
import com.mrkiriss.aserver.exceptions.AnimalAlreadyExistException;
import com.mrkiriss.aserver.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/find")
    public ResponseEntity getAnimalsBySomeParams(@RequestParam Map<String,String> allParams){
        try {
            return ResponseEntity.ok(animalService.findAllBySomeParams(new HashMap<>(allParams)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Поиск всех животных по особым параметрам провалился!");
        }
    }

    @PostMapping
    public ResponseEntity<String> addAnimal(@RequestBody Animal animal){
        try {
            System.out.println("Запрос на добавление начат");
            animalService.addAnimal(animal);
            return ResponseEntity.ok("Животное успешно добавлено!");
        } catch (AnimalAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id){
        try{
            System.out.println("Запрос на удаление начат");
            return ResponseEntity.ok(String.format("Животное с id=%d успешно удалено", animalService.deleteById(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(String.format("Удаление животного с id=%d провалилось", id));
        }
    }
}
