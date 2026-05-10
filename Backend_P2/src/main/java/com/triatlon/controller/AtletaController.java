package com.triatlon.controller;

import com.triatlon.model.Atleta;
import com.triatlon.service.AtletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de atletas de triatlón.
 * Expone todos los microservicios requeridos por el parcial.
 * Usa @RequestMapping como mapeo base y en cada método, según requerimiento.
 */
@RestController
@RequestMapping("/atletas")
public class AtletaController {

    /** Servicio con la lógica de negocio de atletas. */
    @Autowired
    private AtletaService atletaService;

    // =============================================
    // CRUD PRINCIPAL
    // =============================================

    /**
     * Registra un nuevo atleta con foto.
     * POST /atletas/registrar
     * @param atleta datos del atleta en JSON
     * @param foto   imagen del atleta (opcional)
     * @return atleta registrado con su id generado
     */
    // Con foto (multipart/form-data) - para el frontend
@RequestMapping(value = "/registrar", method = RequestMethod.POST,
        consumes = "multipart/form-data")
public ResponseEntity<Atleta> registrar(
        @Valid @RequestPart("atleta") Atleta atleta,
        @RequestPart(value = "foto", required = false) MultipartFile foto) {
    return ResponseEntity.ok(atletaService.registrar(atleta, foto));
}

// Sin foto (application/json) - para pruebas con Postman
@RequestMapping(value = "/registrar/json", method = RequestMethod.POST,
        consumes = "application/json")
public ResponseEntity<Atleta> registrarJson(@Valid @RequestBody Atleta atleta) {
    return ResponseEntity.ok(atletaService.registrar(atleta, null));
}

    /**
     * Lista todos los atletas registrados.
     * GET /atletas/listar
     * @return lista completa de atletas
     */
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<Atleta>> listar() {
        return ResponseEntity.ok(atletaService.listarTodos());
    }

    /**
     * Consulta un atleta por su id de base de datos.
     * GET /atletas/{id}
     * @param id identificador único en la BD
     * @return atleta encontrado
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Atleta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atletaService.buscarPorId(id));
    }

    /**
     * Consulta un atleta por su número de identificación (cédula).
     * GET /atletas/identificacion/{identificacion}
     * @param identificacion número de cédula o pasaporte
     * @return atleta encontrado
     */
    @RequestMapping(value = "/identificacion/{identificacion}", method = RequestMethod.GET)
    public ResponseEntity<Atleta> buscarPorIdentificacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(atletaService.buscarPorIdentificacion(identificacion));
    }

    /**
     * Elimina un atleta del sistema.
     * DELETE /atletas/{id}
     * @param id identificador del atleta a eliminar
     * @return mensaje de confirmación
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        atletaService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Atleta eliminado exitosamente"));
    }

    // =============================================
    // MODIFICAR CAMPOS ESPECÍFICOS (Microservicios requeridos)
    // =============================================

    /**
     * Modifica únicamente el nombre de un atleta.
     * PATCH /atletas/{id}/nombre
     * Body: { "nombre": "Nuevo Nombre" }
     * @param id    id del atleta
     * @param body  mapa con el nuevo nombre
     * @return atleta actualizado
     */
    @RequestMapping(value = "/{id}/nombre", method = RequestMethod.PATCH)
    public ResponseEntity<Atleta> actualizarNombre(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(atletaService.actualizarNombre(id, body.get("nombre")));
    }

    /**
     * Modifica únicamente la identificación de un atleta.
     * PATCH /atletas/{id}/identificacion
     * Body: { "identificacion": "12345678" }
     * @param id    id del atleta
     * @param body  mapa con la nueva identificación
     * @return atleta actualizado
     */
    @RequestMapping(value = "/{id}/identificacion", method = RequestMethod.PATCH)
    public ResponseEntity<Atleta> actualizarIdentificacion(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(atletaService.actualizarIdentificacion(id, body.get("identificacion")));
    }

    /**
     * Modifica únicamente la categoría de un atleta.
     * PATCH /atletas/{id}/categoria
     * Body: { "categoria": "Veterano 1" }
     * @param id    id del atleta
     * @param body  mapa con la nueva categoría
     * @return atleta actualizado
     */
    @RequestMapping(value = "/{id}/categoria", method = RequestMethod.PATCH)
    public ResponseEntity<Atleta> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(atletaService.actualizarCategoria(id, body.get("categoria")));
    }

    // =============================================
    // CONSULTAS POR GRUPO (Microservicios requeridos)
    // =============================================

    /**
     * Consulta atletas filtrados por género.
     * GET /atletas/grupo/genero/{genero}
     * @param genero M o F
     * @return lista de atletas con ese género
     */
    @RequestMapping(value = "/grupo/genero/{genero}", method = RequestMethod.GET)
    public ResponseEntity<List<Atleta>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(atletaService.buscarPorGenero(genero));
    }

    /**
     * Consulta atletas filtrados por categoría.
     * GET /atletas/grupo/categoria/{categoria}
     * @param categoria nombre de la categoría (ej: Absoluta, Juvenil)
     * @return lista de atletas en esa categoría
     */
    @RequestMapping(value = "/grupo/categoria/{categoria}", method = RequestMethod.GET)
    public ResponseEntity<List<Atleta>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(atletaService.buscarPorCategoria(categoria));
    }

    /**
     * Consulta atletas filtrados por especialidad de triatlón.
     * GET /atletas/grupo/especialidad/{especialidad}
     * @param especialidad nombre de la especialidad (ej: Sprint, Olímpica)
     * @return lista de atletas con esa especialidad
     */
    @RequestMapping(value = "/grupo/especialidad/{especialidad}", method = RequestMethod.GET)
    public ResponseEntity<List<Atleta>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(atletaService.buscarPorEspecialidad(especialidad));
    }

    /**
     * Consulta atletas filtrados por participación en modalidad cross.
     * GET /atletas/grupo/cross/{modalidadCross}
     * @param modalidadCross true o false
     * @return lista de atletas filtrados
     */
    @RequestMapping(value = "/grupo/cross/{modalidadCross}", method = RequestMethod.GET)
    public ResponseEntity<List<Atleta>> buscarPorModalidadCross(@PathVariable Boolean modalidadCross) {
        return ResponseEntity.ok(atletaService.buscarPorModalidadCross(modalidadCross));
    }

    /**
     * Calcula y retorna la categoría correspondiente a una edad.
     * GET /atletas/categoria/{edad}
     * Útil para el frontend al momento de registrar un atleta.
     * @param edad edad del atleta
     * @return nombre de la categoría
     */
    @RequestMapping(value = "/categoria/edad/{edad}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> calcularCategoria(@PathVariable int edad) {
        String categoria = atletaService.calcularCategoria(edad);
        return ResponseEntity.ok(Map.of("categoria", categoria));
    }
}
