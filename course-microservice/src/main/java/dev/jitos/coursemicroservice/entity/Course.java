package dev.jitos.coursemicroservice.entity;

import dev.jitos.commonsstudent.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @PrePersist
    private void initCreateAt(){
        this.createAt = new Date();
    }

    public Course() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }
}
