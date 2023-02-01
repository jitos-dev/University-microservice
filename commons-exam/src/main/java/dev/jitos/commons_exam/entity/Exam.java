package dev.jitos.commons_exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exams")
@Getter @Setter
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    /*CascadeType.ALL es porque las preguntas estan relacionadas con el examen y si eliminamos el examen
     * también eliminamos las preguntas
     * orphanRemoval = true es porque al eliminar una pregunta la referencia del examen en la tabla se pone a null
     * y para eliminarlo tenemos que poner esto
     * Y mapeddBy= exam es el nombre del atributo de la clase Question que hace referencia al Exam para que se
     * relacionen en la tabla
     *   @JsonIgnoreProperties(value = "{exam}") es porque cuando consultamos a la bbdd el resultado nos va a
     * devolver también la referencia de la tabla Quextion a Exam y esto no lo queremos porque crea un bucle infinito
     * allowSetters = true esto es porque aunque queremos eliminar el campo examen si que queremos la lista de Question
     * y esto es para que utilice los setter para crearla
     * @JsonManagedReference es para que no de error por la relación con la tabla questions. Esto lo que hace es que
     * que json pueda manejar referencias bidireccionales*/
    @JsonIgnoreProperties(value = {"exam"}, allowSetters = true)
    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Exam() {
        this.questions = new ArrayList<>();
    }

    @PrePersist
    private void initCreateAt() {
        this.createAt = new Date();
    }

    /*Este método es para añadir preguntas al array. Como añadimos una pregunta tenemos que darle referencia del examen
     * y para esto lo hacemos con setExam y le pasamos this porque es el examen en el que estamos*/
    public void addQuestions(Question question) {
        this.questions.add(question);
        question.setExam(this);
    }

    /*En este como eliminamos la pregunta del examen la referencia al examen la ponemos a null y por eso es importante
     * lo del orphanRemoval = true*/
    public void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setExam(null);
    }

    /*Sobrescribimos el setQuestions para que utilice el método que hemos creado para que le pase la referencia
     * al Exam*/
    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        questions.forEach(this::addQuestions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exam exam = (Exam) obj;
        return id.equals(exam.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
