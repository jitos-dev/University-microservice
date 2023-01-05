package dev.jitos.commonsstudent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "students")
@Getter @Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP) //Guarda fecha y hora
    private Date createAt;

    /**
     * Con @PrePersist ejecutamos el código del método antes de guardar en BBDD. Para
     * este ejemplo inicializamos la fecha
     */
    @PrePersist
    public void assingDate(){
        this.createAt = new Date();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
