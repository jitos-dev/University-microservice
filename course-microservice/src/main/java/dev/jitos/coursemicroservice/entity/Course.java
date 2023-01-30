package dev.jitos.coursemicroservice.entity;

import dev.jitos.commons_exam.entity.Exam;
import dev.jitos.commonsstudent.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter @Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    /*Lo hacemos de esta forma y no mapeando con la entidad de Exam porque aunque esten
    * relacionados en la entity Exam no necesitamos tener un List de los Course del examen
    * por eso con esto es suficiente*/
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Exam> exams;

    @PrePersist
    private void initCreateAt(){
        this.createAt = new Date();
    }

    public Course() {
        this.students = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public void deleteExam(Exam exam) {
        this.exams.remove(exam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
