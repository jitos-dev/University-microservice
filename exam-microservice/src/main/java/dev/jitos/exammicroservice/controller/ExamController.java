package dev.jitos.exammicroservice.controller;

import dev.jitos.commonsmicroservice.controller.GenericController;
import dev.jitos.exammicroservice.entity.Exam;
import dev.jitos.exammicroservice.entity.Question;
import dev.jitos.exammicroservice.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExamController extends GenericController<Exam, ExamService> {

    @PutMapping({"/{id}"})
    public ResponseEntity<?> update(@RequestBody Exam exam, @PathVariable Long id) {
        Optional<Exam> optionalExam = service.findById(id);

        if (optionalExam.isEmpty())
            return ResponseEntity.notFound().build();

        Exam examDb = optionalExam.get();
        examDb.setName(exam.getName());

        /*We eliminate all the questions that are not in the exam passed by parameter*/
        examDb.getQuestions().forEach(question -> {
            if (!exam.getQuestions().contains(question))
                examDb.removeQuestion(question);
        });

        /*Lo hacemos de esta forma porque setQuestions elimina también la referencia de Question con Exam*/
        examDb.setQuestions(exam.getQuestions());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDb));
    }
}
