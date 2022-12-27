package dev.jitos.coursemicroservice.controller;

import dev.jitos.commonsmicroservice.controller.GenericController;
import dev.jitos.coursemicroservice.entity.Course;
import dev.jitos.coursemicroservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CourseController extends GenericController<Course, CourseService> {

    @PutMapping({"/{id}"})
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id){
        Optional<Course> optional = service.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        /*modificamos los valores del objeto para guardarlo ya modificado. En este caso solo en nombre porque el id
         y la fecha de creación no se modifican*/
        Course courseDB = optional.get();
        courseDB.setName(course.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }
}
