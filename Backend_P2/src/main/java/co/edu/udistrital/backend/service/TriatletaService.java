package co.edu.udistrital.backend.service;

import co.edu.udistrital.backend.model.Atleta;
import co.edu.udistrital.backend.model.RequestDTO;
import co.edu.udistrital.backend.model.ResponseDTO;
import co.edu.udistrital.backend.repository.AtletaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de contener la lógica de negocio.
 * Implementa todos los métodos del interfaz IMicroservicios.
 *
 * @author Grupo Programación Avanzada
 * @version 6.0
 */

@Service
public class TriatletaService implements IMicroservicios {

    //Auntoinyección del repository por parte de Spring
    @Autowired
    private AtletaRepository repository;

    //Uso del objeto JavaMailSender para el envio del correo al usuario 
    @Autowired
    private JavaMailSender mailSender;

    //Asunto del correo de registro, definido en application.properties. 
    @Value("${correo.asunto}")
    private String asuntoCorreo;

    //Plantilla del cuerpo del correo, definida en application.properties. 
    @Value("${correo.mensaje}")
    private String plantillaCorreo;

    //CREAR ATLETA - MÉTODO POST
    @Override
    public ResponseDTO postAtleta(RequestDTO datosNuevoAtleta) {
        if (repository.findByIdentificacion(String.valueOf(datosNuevoAtleta.getIdentificacion())).isPresent()) {
            throw new RuntimeException(
                    "Ya existe un atleta con la identificación " + datosNuevoAtleta.getIdentificacion());
        }
        Atleta atleta = aEntidad(datosNuevoAtleta);
        atleta.setCategoria(calcularCategoria(datosNuevoAtleta.getEdad()));
        Atleta guardado = repository.save(atleta);
        try {
            enviarCorreo(datosNuevoAtleta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aResponse(guardado);
    }

    //MOSTRAR - MÉTODO GET
    @Override
    public ResponseDTO getAtletaIdentificacion(Long identificacion) {
        Atleta atleta = repository.findByIdentificacion(String.valueOf(identificacion))
                .orElseThrow(() -> new RuntimeException(
                        "No existe un atleta con identificación " + identificacion));
        return aResponse(atleta);
    }

    @Override
    public List<ResponseDTO> getTriatletas() {
        return repository.findAll().stream().map(this::aResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasGenero(String genero) {
        return repository.findByGenero(genero).stream().map(this::aResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream().map(this::aResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasEspecialidad(String especialidad) {
        return repository.findByEspecialidad(especialidad).stream().map(this::aResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasCross(boolean modalidadCross) {
        return repository.findByModalidadCross(modalidadCross).stream().map(this::aResponse).collect(Collectors.toList());
    }

    //MODIFICAR - MÉTODO PATCH 
    @Override
    public ResponseDTO patchNombre(Long id, RequestDTO nombreactualizado) {
        String idStr = String.valueOf(id);
        int filas = repository.actualizarNombre(idStr, nombreactualizado.getNombre());
        if (filas == 0) {
            throw new RuntimeException("No existe un atleta con identificación " + id);
        }
        return getAtletaIdentificacion(id);
    }

    @Override
    public ResponseDTO patchIdentificacion(Long identificacion, RequestDTO identificacionactualizada) {
        String actual = String.valueOf(identificacion);
        String nueva  = identificacionactualizada.getIdentificacion();
        int filas = repository.actualizarIdentificacion(actual, nueva);
        if (filas == 0) {
            throw new RuntimeException("No existe un atleta con identificación " + identificacion);
        }
        return repository.findByIdentificacion(nueva).map(this::aResponse)
                .orElseThrow(() -> new RuntimeException("Error al recuperar el atleta actualizado"));
    }

    @Override
    public ResponseDTO patchCategoria(Long id, RequestDTO categoriaactualizada) {
        String idStr = String.valueOf(id);
        int filas = repository.actualizarCategoria(idStr, categoriaactualizada.getCategoria());
        if (filas == 0) {
            throw new RuntimeException("No existe un atleta con identificación " + id);
        }
        return getAtletaIdentificacion(id);
    }

    //BORRAR - MÉTODO DELETE
    @Override
    public ResponseDTO deleteTriatleta(Long id) {
        Atleta atleta = repository.findByIdentificacion(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException(
                        "No existe un atleta con identificación " + id));
        ResponseDTO eliminado = aResponse(atleta);
        repository.delete(atleta);
        return eliminado;
    }

    // CORREO
    /**
     * Código creado por : Daniel Españadero
     * Obtenido de : https://github.com/DanielEspanadero/java-mail.git
     */
    @Override
    public void enviarCorreo(RequestDTO atleta) {
        String contenido = plantillaCorreo.replace("{nombre}", atleta.getNombre()); //"Plantilla" definida en el properties
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setTo(atleta.getCorreo());//A quier se envía
        correo.setSubject(asuntoCorreo);//El asunto del correo 
        correo.setText(contenido);//El contenido del correo 
        mailSender.send(correo);
    }


    //Convierte un RequestDTO en una entidad Atleta. 
    private Atleta aEntidad(RequestDTO d) {
        Atleta a = new Atleta();
        a.setNombre(d.getNombre());
        a.setIdentificacion(d.getIdentificacion());
        a.setEdad(d.getEdad());
        a.setGenero(d.getGenero());
        a.setCorreo(d.getCorreo());
        a.setEspecialidad(d.getEspecialidad());
        a.setModalidadCross(d.getModalidadCross());
        a.setFoto(d.getFoto());
        return a;
    }

    //Convierte una entidad Atleta en un ResponseDTO.
    private ResponseDTO aResponse(Atleta a) {
        ResponseDTO r = new ResponseDTO();
        r.setId(a.getId());
        r.setNombre(a.getNombre());
        r.setIdentificacion(a.getIdentificacion());
        r.setEdad(a.getEdad());
        r.setGenero(a.getGenero());
        r.setCorreo(a.getCorreo());
        r.setCategoria(a.getCategoria());
        r.setEspecialidad(a.getEspecialidad());
        r.setModalidadCross(a.getModalidadCross());
        r.setFoto(a.getFoto());
        return r;
    }

    // Calcula la categoría del atleta según su edad. 
    private String calcularCategoria(int edad) {
        if (edad == 7)   return "Pre-benjamín";
        if (edad <= 9)   return "Benjamín";
        if (edad <= 11)  return "Alevín";
        if (edad <= 13)  return "Infantil";
        if (edad <= 15)  return "Cadetes";
        if (edad <= 17)  return "Juvenil";
        if (edad <= 19)  return "Júnior";
        if (edad <= 23)  return "Sub-23";
        if (edad <= 39)  return "Absoluta";
        if (edad <= 49)  return "Veterano 1";
        if (edad <= 59)  return "Veterano 2";
        return "Veterano 3";
    }
}
