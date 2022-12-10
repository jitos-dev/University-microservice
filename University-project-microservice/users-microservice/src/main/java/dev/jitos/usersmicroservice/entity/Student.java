package dev.jitos.usersmicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

}
