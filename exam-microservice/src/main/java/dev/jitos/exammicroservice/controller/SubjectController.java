package dev.jitos.exammicroservice.controller;

import dev.jitos.exammicroservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    @Qualifier("subjectRepository")
    private SubjectRepository subjectRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }
}
