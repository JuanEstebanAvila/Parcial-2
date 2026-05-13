package co.edu.udistrital.backend.repository;

import co.edu.udistrital.backend.model.Atleta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositorio:
 * Define consultas derivadas por nombrado de método y actualizaciones
 * parciales con @Modifying para mayor eficiencia.
 *
 * @author Grupo Programación Avanzada
 * @version 2.0
 */

public interface AtletaRepository extends JpaRepository<Atleta, Long> {

    //Busca un atleta por su número de identificación (cédula).
    Optional<Atleta> findByIdentificacion(String identificacion);

    //Muestra los atletas por género 
    List<Atleta> findByGenero(String genero);

    //Muestra los atletas por categoría
    List<Atleta> findByCategoria(String categoria);

    //Muestra los atletas por especialidad. 
    List<Atleta> findByEspecialidad(String especialidad);

    //Muestra los atletas según la modalidad cross. 
    List<Atleta> findByModalidadCross(Boolean modalidadCross);

    // Actualiza únicamente el nombre del atleta. 
    @Modifying//Le dice a Spring que se van a modificar datos
    @Transactional//Para el permiso de escritura
    @Query("UPDATE Atleta a SET a.nombre = :nombre WHERE a.identificacion = :identificacion")//El @Query permite escribir la lógica de actaulización 
    int actualizarNombre(@Param("identificacion") String identificacion,@Param("nombre") String nombre);

    /** Actualiza únicamente la identificación del atleta. */
    @Modifying
    @Transactional
    @Query("UPDATE Atleta a SET a.identificacion = :nueva WHERE a.identificacion = :actual")
    int actualizarIdentificacion(@Param("actual") String actual,@Param("nueva") String nueva);

    /** Actualiza únicamente la categoría del atleta. */
    @Modifying
    @Transactional
    @Query("UPDATE Atleta a SET a.categoria = :categoria WHERE a.identificacion = :identificacion")
    int actualizarCategoria(@Param("identificacion") String identificacion,@Param("categoria") String categoria);
}
