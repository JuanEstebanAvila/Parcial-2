package co.edu.udistrital.backend.controller;

import co.edu.udistrital.backend.model.Atleta;
import co.edu.udistrital.backend.model.RequestDTO;
import co.edu.udistrital.backend.model.ResponseDTO;
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
//Mismo origin de ruta para los diferentes endpoints
@RequestMapping("/triatleta")
public class TriatletaController {
    
    //Inyección automatica por parte de Spring Boot
    @Autowired
    private IMicroservicios service;
    
    /**
     * Para los diferentes microservicios de modificación de un atributo en especificio
     * se opto por PATCH como verbo HTTP, pues este resulta más eficiente ya que no carga 
     * el objeto con todos sus atributos en cada consulta del cliente sino que solo carga aquellos atributos que le interesan al cliente
     */
    
    //método para modificar el nombre de un triatleta
    @RequestMapping(value=("/modificarnombre/{id}"), method=RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarNombre(@PathVariable("id") Long id, @RequestBody RequestDTO nombreactualizado){
        ResponseDTO nuevonombre = service.PatchNombre(id, nombreactualizado);
        return ResponseEntity.ok(nuevonombre);
    }
    
    
    //método para modificar el número de identificación de un triatleta
    @RequestMapping(value = "/modificaridentificacion/{identificacion}", method=RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarIdentificacion (@PathVariable("identificacion") Long identificacion, @RequestBody RequestDTO identificacionactualizada){
     ResponseDTO nuevaidentificacion = service.patchIdentificacion(identificacion, identificacionactualizada);
     return ResponseEntity.ok(nuevaidentificacion);
    }
    
    
    //método para modificar la categoría de un triatleta
    @RequestMapping(value = ("/modificarcategoria/{categoria}"), method = RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarCategoria (@PathVariable ("categoria") String categoria, @RequestBody RequestDTO categoriaactualizada){
        ResponseDTO nuevacategoria = service.patchCategoria(categoria, categoriaactualizada);
        return ResponseEntity.ok(nuevacategoria);
    }
    
    
    //método para consultar a un triatleta por su identificación 
    @RequestMapping(value = ("/consultar/{identificacion}"), method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> consultarTriatleta (@PathVariable("identificacion") Long identificacion){
        return ResponseEntity.ok(service.getTriatleta(identificacion));
    }
    
    //método para consultar triatletas por 
}
