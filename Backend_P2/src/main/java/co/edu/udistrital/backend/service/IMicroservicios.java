package co.edu.udistrital.backend.service;

import co.edu.udistrital.backend.model.RequestDTO;
import co.edu.udistrital.backend.model.ResponseDTO;

/**
 * Clase que da los diferentes contratos de los diferentes métodos y ayuda a cumplir los SOLID con su inversión de dependencias 
 * 
 * @author Grupo Programación Avanzada
 * @version 1.0
 */

public interface IMicroservicios {
    
    //Permite cambiar el nombre de un atleta en especifico según su id 
    ResponseDTO patchNombre(Long id, RequestDTO nombreactualizado);
    //Permite cambiar el atributo "identificación" de un atleta 
    ResponseDTO patchIdentificacion(Long  identificacion, RequestDTO identificacionactualizada);
    //Permite cambiar el atributo "categoría" de un atleta 
    ResponseDTO patchCategoria(String categoria, RequestDTO categoriaactualizada);
    //Devuelve un atleta según su identificación (cédula)
    ResponseDTO getAtletaIdentificacion(Long identificacion);
    //Devuelve los atletas que sean mujeres o que sean hombres 
    ResponseDTO getTriatletasGenero(String genero);
    //Devuelve lso atletas que esten en una categoría en particular
    ResponseDTO getTriatletasCategoria(String categoria);
    //Devuelve los atletas que esten dentro de una Especialidad en particular 
    ResponseDTO getTriatletasEspecialidad(String especialidad);
    //Devuelve los atletas que esten en la modalidad Cross
    ResponseDTO getTriatletasCross(boolean modalidadCross);
    //Borra un Atleta
    ResponseDTO deleteTriatleta(Long id);
    //Crear un nuevo Atleta
    ResponseDTO postAtleta(RequestDTO datosNuevoAtleta);
    //Devuelve todos los Atletas
    ResponseDTO getTriatletas();
}
