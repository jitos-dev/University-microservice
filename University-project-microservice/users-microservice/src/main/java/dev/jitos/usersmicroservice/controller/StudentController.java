package dev.jitos.usersmicroservice.controller;

import dev.jitos.commonsmicroservice.controller.GenericController;
import dev.jitos.usersmicroservice.entity.Student;
import dev.jitos.usersmicroservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*No le ponemos path porque se la vamos a signar en el Api Gateway Zuul*/
@RestController
@RequestMapping("students")
public class StudentController extends GenericController<Student, StudentService> {

//    @Autowired
//    @Qualifier("studentServiceImpl")
//    private StudentService service;

//    /**
//     * Anotamos este método con @GetMapping para indicar que lo llamamos por get y no le indicamos
//     * ninguna url porque lo llamamos desde la raíz del controlador. Le indicamos un parámetro genérico
//     * con la interrogación para indicar que puede ser de cualquier cosa.
//     * @return indicamos que tiene que devolver una respuesta ok (status 200) y la guardamos en el body que convierte
//     * automáticamente lo que devuelva (puede ser un List, objeto, etc) a formato Json
//     */
//    @GetMapping
//    public ResponseEntity<?> findAll() {
//        return ResponseEntity.ok().body(service.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        Optional<Student> optional = service.findById(id);
//
//        if (optional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(optional.get());
//    }

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Student student) {
//        Optional<Student> optional = service.save(student);
//        return ResponseEntity.status(HttpStatus.CREATED).body(optional.isPresent());
//    }

    /**
     * Primero obtenemos el objeto de la entidad de la bbdd para ver si existe. Si no existe retornamos
     * un notFound y si existe mapeamos el objeto que nos pasan por parámetro con el del Optional cambiando
     * los valores que queramos (el id ya lo trae el optional). Por último retornamos el status pero en el
     * body llamamos a save del objeto que hemos mapeado (para el ejemplo studentDB)
     * @param student objeto de la entidad Student
     * @param id id del objeto que queremos modificar
     * @return respuesta que recibimos al guardarlo
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Long id) {
        Optional<Student> optional = service.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        //Modificamos los valores del objeto que queramos cambiar. studentDB ya tiene el id
        Student studentDB = optional.get();
        studentDB.setName(student.getName());
        studentDB.setSurname(student.getSurname());
        studentDB.setEmail(student.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDB));
    }

//    /**
//     * En este como el método de borrar no devuelve nada solo devolvemos un ResonseEntity.noContent
//     * @param id id del objeto que queremos borrar
//     * @return ResponseEntity.noContent() (status 204)
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        service.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
