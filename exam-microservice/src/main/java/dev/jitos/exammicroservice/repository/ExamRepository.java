package dev.jitos.exammicroservice.repository;

import dev.jitos.commonsexam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
