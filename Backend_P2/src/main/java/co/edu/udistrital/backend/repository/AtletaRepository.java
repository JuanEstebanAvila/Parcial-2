package co.edu.udistrital.backend.repository;

import com.triatlon.repository.*;
import com.triatlon.model.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {

}
