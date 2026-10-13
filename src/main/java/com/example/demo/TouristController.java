package com.example.demo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourists")
public class TouristController {

    private final TouristRepository touristRepository;

    public TouristController(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @PostMapping
    public ResponseEntity<Tourist> addTourist(@RequestBody Tourist tourist) {
        tourist.setId(null); // let the database generate the id
        Tourist saved = touristRepository.save(tourist);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }
}
