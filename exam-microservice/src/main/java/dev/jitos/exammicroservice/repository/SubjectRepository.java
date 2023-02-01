package dev.jitos.exammicroservice.repository;

import dev.jitos.commons_exam.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
