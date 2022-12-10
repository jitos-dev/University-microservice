package dev.jitos.usersmicroservice.impl;

import dev.jitos.usersmicroservice.entity.Student;
import dev.jitos.usersmicroservice.repository.StudentRepository;
import dev.jitos.usersmicroservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de la entity Student. Los métodos los marcamos con @Transactional. Los
 * que son de escritura (los que modifican algo como save) los marcamos solo con @Transactional y los
 * demás (de lectura como fingAll) le indicamos readOnly = true para decir que son solo de lectura
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Student> save(Student student) {
        return Optional.of(repository.save(student));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
