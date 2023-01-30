package dev.jitos.exammicroservice.repository;

import dev.jitos.commons_exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT e FROM Exam e WHERE e.name like %?1%")
    List<Exam> findByLikeName(String parameter);
}
