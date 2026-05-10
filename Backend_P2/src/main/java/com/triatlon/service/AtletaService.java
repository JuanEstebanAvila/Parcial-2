package com.triatlon.service;

import com.triatlon.model.Atleta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interfaz del servicio de Atleta.
 * Define las operaciones disponibles respetando el principio SOLID de separación de interfaces.
 */
public interface AtletaService {

    /** Registra un nuevo atleta en el sistema y envía correo de confirmación. */
    Atleta registrar(Atleta atleta, MultipartFile foto);

    /** Retorna todos los atletas registrados. */
    List<Atleta> listarTodos();

    /** Busca un atleta por su id de base de datos. */
    Atleta buscarPorId(Long id);

    /** Busca un atleta por número de identificación (cédula). */
    Atleta buscarPorIdentificacion(String identificacion);

    /** Actualiza el nombre de un atleta. */
    Atleta actualizarNombre(Long id, String nuevoNombre);

    /** Actualiza la identificación de un atleta. */
    Atleta actualizarIdentificacion(Long id, String nuevaIdentificacion);

    /** Actualiza la categoría de un atleta. */
    Atleta actualizarCategoria(Long id, String nuevaCategoria);

    /** Consulta atletas por género. */
    List<Atleta> buscarPorGenero(String genero);

    /** Consulta atletas por categoría. */
    List<Atleta> buscarPorCategoria(String categoria);

    /** Consulta atletas por especialidad. */
    List<Atleta> buscarPorEspecialidad(String especialidad);

    /** Consulta atletas por modalidad cross. */
    List<Atleta> buscarPorModalidadCross(Boolean modalidadCross);

    /** Elimina un atleta del sistema. */
    void eliminar(Long id);

    /**
     * Determina la categoría automáticamente según la edad del atleta.
     * @param edad edad del atleta
     * @return nombre de la categoría correspondiente
     */
    String calcularCategoria(int edad);
}

