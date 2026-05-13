package co.edu.udistrital.backend.controller;

import co.edu.udistrital.backend.model.RequestDTO;
import co.edu.udistrital.backend.model.ResponseDTO;
import co.edu.udistrital.backend.service.IMicroservicios;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Controller de la entidad Triatleta.
 * Encargada de los diferentes endpoints y de la delegación de la lógica
 * del negocio al service.
 *
 * @author Grupo Programación Avanzada
 * @version 1.5
 */

@RestController
@RequestMapping("/triatleta")
public class TriatletaController {

    @Autowired
    private IMicroservicios service;

    /**
     * Para los diferentes microservicios de modificación de un atributo
     * en específico se optó por PATCH como verbo HTTP, pues es más
     * eficiente: no carga el objeto con todos sus atributos en cada
     * consulta del cliente, sino solo los atributos que cambian.
     */

    // Método para modificar el nombre de un triatleta. 
    @RequestMapping(value = "/modificarnombre/{identificacion}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarNombre(
            @PathVariable("identificacion") Long id,
            @RequestBody RequestDTO nombreactualizado) {
        try {
            return ResponseEntity.ok(service.patchNombre(id, nombreactualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Método para modificar el número de identificación de un triatleta. 
    @RequestMapping(value = "/modificaridentificacion/{identificacion}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarIdentificacion(
            @PathVariable("identificacion") Long identificacion,
            @RequestBody RequestDTO identificacionactualizada) {
        try {
            return ResponseEntity.ok(service.patchIdentificacion(identificacion, identificacionactualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para modificar la categoría de un triatleta. 
    @RequestMapping(value = "/modificarcategoria/{identificacion}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseDTO> modificarCategoria(
            @PathVariable("identificacion") Long id,
            @RequestBody RequestDTO categoriaactualizada) {
        try {
            return ResponseEntity.ok(service.patchCategoria(id, categoriaactualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para consultar a un triatleta por su identificación. 
    @RequestMapping(value = "/consultar/{identificacion}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> consultarTriatleta(@PathVariable("identificacion") Long identificacion) {
        try {
            return ResponseEntity.ok(service.getAtletaIdentificacion(identificacion));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Consultar triatletas por género . 
    @RequestMapping(value = "/grupo/consultar/genero/{genero}", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseDTO>> consultarGenero(@PathVariable("genero") String genero) {
        return ResponseEntity.ok(service.getTriatletasGenero(genero));
    }

    //Consultar triatletas por categoría. 
    @RequestMapping(value = "/grupo/consultar/categoria/{categoria}", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseDTO>> consultarCategoria(@PathVariable("categoria") String categoria) {
        return ResponseEntity.ok(service.getTriatletasCategoria(categoria));
    }

    //Consultar triatletas por especialidad. 
    @RequestMapping(value = "/grupo/consultar/especialidad/{especialidad}", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseDTO>> consultarEspecialidad(@PathVariable("especialidad") String especialidad) {
        return ResponseEntity.ok(service.getTriatletasEspecialidad(especialidad));
    }

    //Consultar triatletas por modalidad Cross (true/false). 
    @RequestMapping(value = "/grupo/consultar/cross/{cross}", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseDTO>> consultarCross(@PathVariable("cross") boolean modalidadCross) {
        return ResponseEntity.ok(service.getTriatletasCross(modalidadCross));
    }

    // Método para eliminar un triatleta. 
    @RequestMapping(value = "/eliminar/{identificacion}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> eliminarAtleta(@PathVariable("identificacion") Long id) {
        try {
            return ResponseEntity.ok(service.deleteTriatleta(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Método para crear un triatleta. *
    @RequestMapping(value = "/crearatleta", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> crearAtleta(@Valid @RequestBody RequestDTO datosNuevoAtleta) {
        try {
            return ResponseEntity.ok(service.postAtleta(datosNuevoAtleta));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Método para mostrar el listado de todos los triatletas. 
    @RequestMapping(value = "/mostraratletas", method = RequestMethod.GET)
    public ResponseEntity<List<ResponseDTO>> mostrarTriatletas() {
        return ResponseEntity.ok(service.getTriatletas());
    }
}
