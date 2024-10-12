package com.restaurant.restaurant.repository;

import com.restaurant.restaurant.entity.Oder;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OderSaveRepositary extends MongoRepository<Oder, String> {

    // If there are other fields in Oder you want to search by, you can define methods here.
    // Example: List<Oder> findByName(String name);

}
