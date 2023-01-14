package dev.jitos.usersmicroservice.repository;

import dev.jitos.commonsstudent.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

    /*Para realizar una "Query" hay que hacerlo de esta forma para que no de error. Al utilizar
    * like no distingue entre mayúsculas y minúsculas*/
    @Query("SELECT s FROM Student s WHERE s.name like %?1% or s.surname like %?1%")
    List<Student> findByNameOrSurname(String value);

    //Esta es otra forma de hacerlo
    //List<Student> findByNameLikeOrSurnameLike(String value);
}
