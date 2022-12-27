package dev.jitos.coursemicroservice.impl;

import dev.jitos.commonsmicroservice.impl.GenericServiceImpl;
import dev.jitos.coursemicroservice.entity.Course;
import dev.jitos.coursemicroservice.repository.CourseRepository;
import dev.jitos.coursemicroservice.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, CourseRepository> implements CourseService {

}
