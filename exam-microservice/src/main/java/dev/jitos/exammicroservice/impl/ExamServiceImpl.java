package dev.jitos.exammicroservice.impl;

import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.exammicroservice.repository.ExamRepository;
import dev.jitos.exammicroservice.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl extends GenericServiceImpl<Exam, ExamRepository> implements ExamService {

    @Override
    @Transactional(readOnly = true)
    public List<Exam> findByLikeName(String parameter) {
        return repository.findByLikeName(parameter);
    }
}
