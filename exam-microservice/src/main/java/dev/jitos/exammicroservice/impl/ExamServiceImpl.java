package dev.jitos.exammicroservice.impl;

import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.exammicroservice.repository.ExamRepository;
import dev.jitos.exammicroservice.service.ExamService;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends GenericServiceImpl<Exam, ExamRepository> implements ExamService {
}
