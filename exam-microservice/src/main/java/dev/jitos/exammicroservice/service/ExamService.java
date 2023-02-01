package dev.jitos.exammicroservice.service;

import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commons_exam.entity.Subject;
import dev.jitos.commonsmicroservice.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService extends GenericService<Exam> {

    List<Exam> findByLikeName(String parameter);

    List<Subject> findAllSubjects();
}
