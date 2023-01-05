package dev.jitos.coursemicroservice.controller;

import dev.jitos.commonsmicroservice.controller.GenericController;
import dev.jitos.commonsstudent.entity.Student;
import dev.jitos.coursemicroservice.entity.Course;
import dev.jitos.coursemicroservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*Comento el @RequestMapping porque en gateway-zuul ya tiene la ruta a courses*/
@RestController
//@RequestMapping("courses")
public class CourseController extends GenericController<Course, CourseService> {

    @PutMapping({"/{id}"})
    public ResponseEntity<?> update(@RequestBody Course course, @PathVariable Long id){
        Optional<Course> optional = service.findById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        /*modificamos los valores del objeto para guardarlo ya modificado. En este caso solo en nombre porque el id
         y la fecha de creación no se modifican*/
        Course courseDB = optional.get();
        courseDB.setName(course.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }

    /*Este método y el de abajo los hago con @PutMapping porque aunque sea para añadir o eliminar lo que estamos
    * haciendo es modificando un objeto de la entity Course*/
    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> assignStudents(@PathVariable Long id, @RequestBody List<Student> students) {
        Optional<Course> course = service.findById(id);

        if (course.isEmpty())
            return ResponseEntity.notFound().build();

        Course courseDB = course.get();
        students.forEach(courseDB::addStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> removeStudents(@PathVariable Long id, @RequestBody Student student) {
        Optional<Course> course = service.findById(id);

        if (course.isEmpty())
            return ResponseEntity.notFound().build();

        if (student == null || student.getId() == null)
            return ResponseEntity.notFound().build();

        Course courseDB = course.get();
        courseDB.deleteStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }

}
