package dev.jitos.commonsmicroservice.impl;

import dev.jitos.commonsmicroservice.service.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio del GenericService. Tenemos que indicarle que va a trabajar con
 * parámetros genéricos y para ello le indicamos la E (Entity) y R extends ... para decirle que
 * vamos a utilizar un repositorio (que llamamos R) que tiene que extender de JpaRepository de la
 * entidad. También implementa el GenericService que hemos creado
 * Los métodos los marcamos con @Transactional. Los que son de escritura (los que modifican algo como save)
 * los marcamos solo con @Transactional y los demás (de lectura como fingAll) le indicamos
 * readOnly = true para decir que son solo de lectura
 */
@Service
public class GenericServiceImpl<E, R extends JpaRepository<E, Long>> implements GenericService<E> {

    //Lo marcamos protected para poder utilizarlo en las clases que hereden de esta
    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<E> save(E entity) {
        return Optional.of(repository.save(entity));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
