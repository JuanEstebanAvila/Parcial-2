package co.edu.udistrital.backend.controller;

import co.edu.udistrital.backend.model.Atleta;
import co.edu.udistrital.backend.service.IMicroservicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Controller de la entidad Triatleta
 * 
 * Encargada de los diferentes endpoints y de la delegación de la lógica del negocio al service
 * 
 * @author Grupo Programación Avanzada
 * @version 1.0
 */

@RestController
@RequestMapping("/triatleta")
public class TriatletaController {
    
    //Inyección automatica por parte de Spring Boot
    @Autowired
    private IMicroservicios service;
    
    //método para modificar el nombre de un triatleta
    @RequestMapping(value=("/modificarnombre/{id}"), method=RequestMethod.PATCH)
    public ResponseEntity<Atleta> ModificarNombre(@PathVariable("id") Long id, @RequestBody Atleta datosactualizados){
        Atleta nuevonombre = service.PatchNombre(id, datosactualizados);
        return ResponseEntity.ok(nuevonombre);
    }
    
    //método para 
    
    
    
}
