package com.ebru.service;

import com.ebru.repository.PublisherRepository;
import com.ebru.repository.entity.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<List<Publisher>> getAllPublishers(){
        List<Publisher> publishers = publisherRepository.findAll();
        if(publishers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Publisher> getPublisherById(Long id){
        Optional<Publisher> optionalData = publisherRepository.findById(id);
        return optionalData.map(pub -> new ResponseEntity<>(pub, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
