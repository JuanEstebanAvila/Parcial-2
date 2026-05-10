package com.triatlon.repository;

import com.triatlon.model.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para el manejo de la entidad Atleta.
 * Spring Data genera automáticamente las consultas a partir del nombre de los métodos.
 */
@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {

    /**
     * Busca atletas por identificación.
     * @param identificacion número de identificación del atleta
     * @return atleta encontrado (si existe)
     */
    Optional<Atleta> findByIdentificacion(String identificacion);

    /**
     * Filtra atletas por género.
     * @param genero M o F
     * @return lista de atletas con ese género
     */
    List<Atleta> findByGenero(String genero);

    /**
     * Filtra atletas por categoría.
     * @param categoria nombre de la categoría
     * @return lista de atletas en esa categoría
     */
    List<Atleta> findByCategoria(String categoria);

    /**
     * Filtra atletas por especialidad de triatlón.
     * @param especialidad nombre de la especialidad
     * @return lista de atletas con esa especialidad
     */
    List<Atleta> findByEspecialidad(String especialidad);

    /**
     * Filtra atletas por participación en modalidad cross.
     * @param modalidadCross true si participa en cross
     * @return lista de atletas filtrados
     */
    List<Atleta> findByModalidadCross(Boolean modalidadCross);
}