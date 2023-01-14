package dev.jitos.usersmicroservice.repository;

import dev.jitos.commonsstudent.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT Student FROM Student WHERE name like %?1% or surname like %?1%")
    List<Student> findByNameOrSurname(String value);
}
