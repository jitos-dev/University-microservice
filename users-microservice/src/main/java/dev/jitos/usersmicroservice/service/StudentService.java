package dev.jitos.usersmicroservice.service;

import dev.jitos.commonsmicroservice.service.GenericService;
import dev.jitos.commonsstudent.entity.Student;

import java.util.List;

/**
 * Servicio para trabajar con la entidad Alumno. Creamos el servicio para desacoplar las operaciones
 * con la BBDD de los controladores y de esta forma evitar problemas futuros si hay que modificar algo.
 */
public interface StudentService extends GenericService<Student> {

    List<Student> findByNameOrSurname(String value);
}
