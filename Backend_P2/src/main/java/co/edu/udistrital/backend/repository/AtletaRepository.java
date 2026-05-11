package co.edu.udistrital.backend.repository;

/**
 * Clase encargada de la comunicación con la BD 
 * 
 *@autor Grupo Programación Avanzada 
 *@version 1.0
 */
import co.edu.udistrital.backend.model.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {
}
