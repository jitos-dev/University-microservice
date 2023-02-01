package dev.jitos.commons_exam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*La lógica de esta clase Asignatura es que la vamos a relacionar con Exam. Un examen es de
* una asignatura, pero aquí en asignatura no nos hace falta tener una lista de Exam a los que
* pertenece la asignatura por eso aquí no tenemos ningún atributo de relación.
* Luego esta clase se relaciona consigo misma (relación circular lo mismo que con la de empleado
* y jefe que un jefe es un empleado y a la vez un empleado tiene jefe) porque una asignatura
* puede ser asignatura y sub asignatura. Por ejemplo podemos tener la asignatura de matemáticas y
* luego algebra que es una asignatura, pero también es una sub asignatura de matemáticas como también
* por ejemplo Ciencias es una asignatura y Naturales también es una asignatura, pero es una sub asignatura
* de Ciencias. A esto se le llama "SELF JOIN". Esta tabla tendrá un campo que será la llave foránea
* de la asignatura a la que pertenece */
@Entity
@Table(name = "subjects")
@Getter @Setter
public class Subject { //Asignatura

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*Si recordamos la primera parte representa la clase (Many) y la segunda al atributo (one)
    * por lo que una asignatura padre (parentSignature) puede tener muchas asignaturas*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_signature_id")
    @JsonIgnoreProperties(value = {"daughterSignatures"})
    @JsonBackReference
    private Subject parentSignature;

    @JsonIgnoreProperties(value = {"parentSignature"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentSignature", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Subject> daughterSignatures;

    public Subject() {
        daughterSignatures = new ArrayList<>();
    }
}
