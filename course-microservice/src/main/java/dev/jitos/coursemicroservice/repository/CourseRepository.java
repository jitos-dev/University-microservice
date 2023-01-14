package dev.jitos.coursemicroservice.repository;

import dev.jitos.coursemicroservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c join fetch c.students a where a.id = ?1")
    Course findCourseByStudentId(Long id);
}
