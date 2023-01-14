package dev.jitos.coursemicroservice.impl;

import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.coursemicroservice.entity.Course;
import dev.jitos.coursemicroservice.repository.CourseRepository;
import dev.jitos.coursemicroservice.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, CourseRepository> implements CourseService {

    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }
}
