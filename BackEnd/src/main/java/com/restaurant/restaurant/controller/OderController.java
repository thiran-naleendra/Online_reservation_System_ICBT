package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entity.Oder;
import com.restaurant.restaurant.repository.OderSaveRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OderController {

    @Autowired
    private OderSaveRepositary oderSaveRepository;

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Oder>> getAllOrders() {
        List<Oder> orderList = oderSaveRepository.findAll();
        return ResponseEntity.ok(orderList);
    }

    // Add a new order
    @PostMapping("/add")
    public ResponseEntity<Oder> addNewOrder(@RequestBody Oder order) {
        // Calculate total price based on items and their quantities
        double totalPrice = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalPrice(totalPrice); // Set the calculated total price
        Oder newOrder = oderSaveRepository.save(order); // Save the order
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Oder> getOrderById(@PathVariable String id) {
        Oder order = oderSaveRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(order);
    }

    // Update an existing order
    @PutMapping("/update/{id}")
    public ResponseEntity<Oder> updateOrder(@PathVariable String id, @RequestBody Oder updatedOrder) {
        if (oderSaveRepository.existsById(id)) {
            updatedOrder.setId(id);
            Oder savedOrder = oderSaveRepository.save(updatedOrder);
            return ResponseEntity.ok(savedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        if (oderSaveRepository.existsById(id)) {
            oderSaveRepository.deleteById(id);
            return ResponseEntity.ok("Order deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
    }
}
