package dev.jitos.usersmicroservice.service;

import dev.jitos.usersmicroservice.entity.Student;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para trabajar con la entidad Alumno. Creamos el servicio para desacoplar las operaciones
 * con la BBDD de los controladores y de esta forma evitar problemas futuros si hay que modificar algo.
 */
public interface StudentService {

    List<Student> findAll();
    Optional<Student> findById(Long id);
    Optional<Student> save(Student student);
    void deleteById(Long id);
}
