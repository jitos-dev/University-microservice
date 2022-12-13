package dev.jitos.usersmicroservice.impl;

import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.usersmicroservice.entity.Student;
import dev.jitos.usersmicroservice.repository.StudentRepository;
import dev.jitos.usersmicroservice.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de la entity Student. Los métodos los marcamos con @Transactional. Los
 * que son de escritura (los que modifican algo como save) los marcamos solo con @Transactional y los
 * demás (de lectura como fingAll) le indicamos readOnly = true para decir que son solo de lectura
 */
@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, StudentRepository> implements StudentService {

}
