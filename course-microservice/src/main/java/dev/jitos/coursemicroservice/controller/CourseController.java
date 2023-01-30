package dev.jitos.coursemicroservice.controller;

import dev.jitos.commons_exam.entity.Exam;
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
        return assign(id, students);
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> removeStudents(@PathVariable Long id, @RequestBody Student student) {
        return remove(id, student);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findCourseByStudentId(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.findCourseByStudentId(id));
    }

    @PutMapping("/{id}/asignar-examen")
    public ResponseEntity<?> assignExam(@PathVariable Long id, @RequestBody List<Exam> exams) {
        return assign(id, exams);
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> deleteExam(@PathVariable Long id, @RequestBody Exam exam) {
        return remove(id, exam);
    }

    /*Generic method to avoid duplicating code*/
    private ResponseEntity<?> assign(Long id, List<?> genericList) {
        Optional<Course> optionalCourse = service.findById(id);

        if (optionalCourse.isEmpty())
            return ResponseEntity.notFound().build();

        Course courseDB = optionalCourse.get();

        genericList.forEach(generic -> {
            if (generic instanceof Exam) {
                courseDB.addExam((Exam) generic);

            } else if (generic instanceof Student)
                courseDB.addStudent((Student) generic);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }

    /*Generic method to avoid duplicating code*/
    private <E> ResponseEntity<?> remove(Long id, E entity) {
        Optional<Course> course = service.findById(id);

        if (course.isEmpty())
            return ResponseEntity.notFound().build();

        Course courseDB = course.get();

        if (entity instanceof Student)
            courseDB.deleteStudent((Student) entity);

        else if (entity instanceof Exam)
            courseDB.deleteExam((Exam) entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
    }

}
