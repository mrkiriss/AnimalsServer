package com.mrkiriss.aserver.data;

import com.mrkiriss.aserver.domain.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "animals", path = "animals")
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    Animal findByOwnerAndName(String owner, String name);
}