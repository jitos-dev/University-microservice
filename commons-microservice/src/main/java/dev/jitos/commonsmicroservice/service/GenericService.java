package dev.jitos.commonsmicroservice.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicion genérico para reutilizar código con las operaciones básicas de un crud como son
 * findAll(), findById(), save(), deleteById(). La idea es luego reutilizarlo en los demás
 * microservicios. La E indica que el parámetro genérico es una entidad
 */
public interface GenericService<E> {

    List<E> findAll();
    Optional<E> findById(Long id);
    Optional<E> save(E entity);
    void deleteById(Long id);
}
