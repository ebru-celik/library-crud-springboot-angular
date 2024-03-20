package com.ebru.controller;

import com.ebru.repository.entity.Publisher;
import com.ebru.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") //  http://localhost:8080/api
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publishers/{id}")
    ResponseEntity<Publisher> getPublisherById(@PathVariable("id") Long id){
        return publisherService.getPublisherById(id);
    }
}
