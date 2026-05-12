package co.edu.udistrital.backend.repository;

import co.edu.udistrital.backend.model.Atleta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Clase encargada de la comunicación con la BD 
 * 
 *@autor Grupo Programación Avanzada 
 *@version 1.0
 */

public interface AtletaRepository extends JpaRepository<Atleta, Long> {
    List<Atleta> findByGenero(String genero);
    List<Atleta> findByCategoria(String categoria);
    List<Atleta> findByEspecialidad(String especialidad);
    List<Atleta> findByModalidadCross(Boolean modalidadCross);
}
