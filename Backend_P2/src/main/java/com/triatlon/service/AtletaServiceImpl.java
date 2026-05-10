package com.triatlon.service;

import com.triatlon.model.Atleta;
import com.triatlon.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Implementación del servicio de Atleta.
 * Maneja la lógica de negocio: CRUD, filtros, cálculo de categoría y guardado de foto.
 */
@Service
public class AtletaServiceImpl implements AtletaService {

    /** Repositorio JPA para persistencia de atletas. */
    @Autowired
    private AtletaRepository atletaRepository;

    /** Servicio de correo para notificaciones de registro. */
    @Autowired
    private EmailService emailService;

    /** Carpeta donde se guardan las fotos de atletas (configurable en application.properties). */
    @Value("${upload.dir:uploads/fotos}")
    private String uploadDir;

    /**
     * Registra un nuevo atleta: calcula su categoría, guarda su foto,
     * persiste en base de datos y envía correo de confirmación.
     */
    @Override
    public Atleta registrar(Atleta atleta, MultipartFile foto) {
        // Calcular categoría automáticamente
        atleta.setCategoria(calcularCategoria(atleta.getEdad()));

        // Guardar foto si viene en la solicitud
        if (foto != null && !foto.isEmpty()) {
            String nombreFoto = guardarFoto(foto, atleta.getIdentificacion());
            atleta.setFoto(nombreFoto);
        }

        Atleta guardado = atletaRepository.save(atleta);

        // Enviar correo de registro
        emailService.enviarCorreoRegistro(guardado.getCorreo(), guardado.getNombre());

        return guardado;
    }

    /** {@inheritDoc} */
    @Override
    public List<Atleta> listarTodos() {
        return atletaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException si el atleta no existe
     */
    @Override
    public Atleta buscarPorId(Long id) {
        return atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado con id: " + id));
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException si el atleta no existe
     */
    @Override
    public Atleta buscarPorIdentificacion(String identificacion) {
        return atletaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado con identificación: " + identificacion));
    }

    /** {@inheritDoc} */
    @Override
    public Atleta actualizarNombre(Long id, String nuevoNombre) {
        Atleta atleta = buscarPorId(id);
        atleta.setNombre(nuevoNombre);
        return atletaRepository.save(atleta);
    }

    /** {@inheritDoc} */
    @Override
    public Atleta actualizarIdentificacion(Long id, String nuevaIdentificacion) {
        Atleta atleta = buscarPorId(id);
        atleta.setIdentificacion(nuevaIdentificacion);
        return atletaRepository.save(atleta);
    }

    /** {@inheritDoc} */
    @Override
    public Atleta actualizarCategoria(Long id, String nuevaCategoria) {
        Atleta atleta = buscarPorId(id);
        atleta.setCategoria(nuevaCategoria);
        return atletaRepository.save(atleta);
    }

    /** {@inheritDoc} */
    @Override
    public List<Atleta> buscarPorGenero(String genero) {
        return atletaRepository.findByGenero(genero);
    }

    /** {@inheritDoc} */
    @Override
    public List<Atleta> buscarPorCategoria(String categoria) {
        return atletaRepository.findByCategoria(categoria);
    }

    /** {@inheritDoc} */
    @Override
    public List<Atleta> buscarPorEspecialidad(String especialidad) {
        return atletaRepository.findByEspecialidad(especialidad);
    }

    /** {@inheritDoc} */
    @Override
    public List<Atleta> buscarPorModalidadCross(Boolean modalidadCross) {
        return atletaRepository.findByModalidadCross(modalidadCross);
    }

    /**
     * {@inheritDoc}
     * @throws RuntimeException si el atleta no existe
     */
    @Override
    public void eliminar(Long id) {
        if (!atletaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: atleta no encontrado con id: " + id);
        }
        atletaRepository.deleteById(id);
    }

    /**
     * Calcula la categoría del atleta según su edad, siguiendo el reglamento oficial.
     * @param edad edad del atleta
     * @return nombre de la categoría
     */
    @Override
    public String calcularCategoria(int edad) {
        if (edad == 7) return "Pre-benjamín";
        if (edad <= 9) return "Benjamín";
        if (edad <= 11) return "Alevín";
        if (edad <= 13) return "Infantil";
        if (edad <= 15) return "Cadetes";
        if (edad <= 17) return "Juvenil";
        if (edad <= 19) return "Júnior";
        if (edad <= 23) return "Sub-23";
        if (edad <= 39) return "Absoluta";
        if (edad <= 49) return "Veterano 1";
        if (edad <= 59) return "Veterano 2";
        return "Veterano 3";
    }

    /**
     * Guarda la foto del atleta en el sistema de archivos.
     * @param foto archivo de imagen recibido
     * @param identificacion identificación del atleta (se usa como nombre del archivo)
     * @return nombre del archivo guardado
     */
    private String guardarFoto(MultipartFile foto, String identificacion) {
        try {
            Path dirPath = Paths.get(uploadDir);
            Files.createDirectories(dirPath);

            String extension = foto.getOriginalFilename() != null
                    ? foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf("."))
                    : ".jpg";
            String nombreArchivo = identificacion + extension;

            Path destino = dirPath.resolve(nombreArchivo);
            Files.copy(foto.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return nombreArchivo;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la foto: " + e.getMessage());
        }
    }
}
