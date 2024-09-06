package com.restaurant.restaurant.repository;

import com.restaurant.restaurant.entity.Booking;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.restaurant.restaurant.repository")
@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {


    List<Booking> findByHotelId(int hotelId);
}
