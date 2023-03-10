package dev.jitos.commons_exam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions")
@Getter @Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;


    /*Esto es por la relación de la columna porque cuando guardemos una pregunta
     * va relacionada con un Exam por el valor exam_id
     * Aquí a diferencia de Exam con @JsonIgnoreProperties(value = "{questions}") ignoramos el atributo
     * questions que es la lista de Questions de la entity Exam
     * @JsonBackReference es para que no de error por la referencia con la tabla Exam. Esto lo que hace es que
     * que json pueda manejar referencias bidireccionales*/
    @JsonIgnoreProperties(value = "{questions}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    @JsonBackReference
    private Exam exam;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
