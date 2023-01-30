package dev.jitos.exammicroservice.controller;

import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commonsmicroservice.controller.GenericController;
import dev.jitos.exammicroservice.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*La entity Exam y Question la hemos sacado a una librería aparte porque la vamos a utilizar en otros microservicios*/
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

    /*Find and exam by name with the Like operator. The parameter can be included anywhere*/
    @GetMapping("/filter/{parameter}")
    public ResponseEntity<?> filter(@PathVariable String parameter) {
        if (parameter == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.findByLikeName(parameter));
    }
}
