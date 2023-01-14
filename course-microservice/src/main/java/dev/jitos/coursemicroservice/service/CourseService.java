package dev.jitos.coursemicroservice.service;

import dev.jitos.commonsmicroservice.service.GenericService;
import dev.jitos.coursemicroservice.entity.Course;

public interface CourseService extends GenericService<Course> {

    Course findCourseByStudentId(Long id);
}
