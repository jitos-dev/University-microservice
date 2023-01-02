package dev.jitos.commonsmicroservice.controller;
import dev.jitos.commonsmicroservice.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*No le ponemos @RestController porque es genérico.
* El método de editar no lo hacemos genérico porque depende de los getters y setters del objeto
* Las iniciales de la parte genérica son: E para la entidad y S para el service
* El service lo hacemos protected para poder utilizarlo en las clases que hereden de esta*/
public class GenericController<E, S extends GenericService<E>> {

    @Autowired
    protected S service;

    /**
     * Anotamos este método con @GetMapping para indicar que lo llamamos por get y no le indicamos
     * ninguna url porque lo llamamos desde la raíz del controlador. Le indicamos un parámetro genérico
     * con la interrogación para indicar que puede ser de cualquier cosa.
     * @return indicamos que tiene que devolver una respuesta ok (status 200) y la guardamos en el body que convierte
     * automáticamente lo que devuelva (puede ser un List, objeto, etc) a formato Json
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<E> optional = service.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody E entity) {
        Optional<E> optional = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(optional.isPresent());
    }


    /**
     * En este como el método de borrar no devuelve nada solo devolvemos un ResonseEntity.noContent
     * @param id id del objeto que queremos borrar
     * @return ResponseEntity.noContent() (status 204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
