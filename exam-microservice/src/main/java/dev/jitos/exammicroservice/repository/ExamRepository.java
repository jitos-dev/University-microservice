package dev.jitos.exammicroservice.repository;

import dev.jitos.exammicroservice.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
