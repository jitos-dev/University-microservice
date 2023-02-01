package dev.jitos.exammicroservice.impl;

import com.netflix.discovery.converters.Auto;
import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commons_exam.entity.Subject;
import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.exammicroservice.repository.ExamRepository;
import dev.jitos.exammicroservice.repository.SubjectRepository;
import dev.jitos.exammicroservice.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl extends GenericServiceImpl<Exam, ExamRepository> implements ExamService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Exam> findByLikeName(String parameter) {
        return repository.findByLikeName(parameter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAllSubjects() {
    return subjectRepository.findAll();
    }
}
