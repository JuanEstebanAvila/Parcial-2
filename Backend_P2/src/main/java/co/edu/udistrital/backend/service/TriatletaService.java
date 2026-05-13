package co.edu.udistrital.backend.service;

import co.edu.udistrital.backend.model.Atleta;
import co.edu.udistrital.backend.model.RequestDTO;
import co.edu.udistrital.backend.model.ResponseDTO;
import co.edu.udistrital.backend.repository.AtletaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de contener la lógica de negocio.
 * Implementa todos los métodos del interfaz IMicroservicios.
 * @author Grupo Programación Avanzada
 * @version 2.0
 */
@Service
public class TriatletaService implements IMicroservicios {

    @Autowired
    private AtletaRepository repository;
    
    @Autowired
    private JavaMailSender mailSender;

    /** Convierte un Atleta en ResponseDTO para enviarlo al cliente */
   private ResponseDTO toResponse(Atleta a) {
    long identificacion = 0;
    if (a.getIdentificacion() != null) {
        identificacion = Long.parseLong(a.getIdentificacion());
    }
    return new ResponseDTO(
        a.getId(),
        a.getNombre(),
        identificacion,
        a.getEdad(),
        a.getGenero(),
        a.getCategoria(),
        a.getEspecialidad(),
        a.getModalidadCross() != null ? a.getModalidadCross() : false,
        a.getCorreo(),
        a.getFoto()
    );
}

    /** Calcula la categoría automáticamente según la edad */
    private String calcularCategoria(int edad) {
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

    @Override
    public ResponseDTO postAtleta(RequestDTO datos) {
        Atleta atleta = new Atleta();
        atleta.setNombre(datos.getNombre());
        atleta.setIdentificacion(String.valueOf(datos.getIdentificacion()));
        atleta.setEdad(datos.getEdad());
        atleta.setGenero(datos.getGenero());
        atleta.setCategoria(calcularCategoria(datos.getEdad()));
        atleta.setEspecialidad(datos.getEspecialidad());
        atleta.setModalidadCross(datos.isCross());
        atleta.setCorreo(datos.getEmail());
        return toResponse(repository.save(atleta));
    }

    @Override
    public List<ResponseDTO> getTriatletas() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO getAtletaIdentificacion(Long identificacion) {
        return repository.findById(identificacion)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado"));
    }

    @Override
    public ResponseDTO patchNombre(Long id, RequestDTO datos) {
        Atleta atleta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado"));
        atleta.setNombre(datos.getNombre());
        return toResponse(repository.save(atleta));
    }

    @Override
    public ResponseDTO patchIdentificacion(Long id, RequestDTO datos) {
        Atleta atleta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado"));
        atleta.setIdentificacion(String.valueOf(datos.getIdentificacion()));
        return toResponse(repository.save(atleta));
    }

    @Override
    public ResponseDTO patchCategoria(String categoria, RequestDTO datos) {
        List<Atleta> atletas = repository.findByCategoria(categoria);
        if (atletas.isEmpty()) throw new RuntimeException("No encontrado");
        Atleta atleta = atletas.get(0);
        atleta.setCategoria(datos.getCategoria());
        return toResponse(repository.save(atleta));
    }

    @Override
    public List<ResponseDTO> getTriatletasGenero(String genero) {
        return repository.findByGenero(genero).stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasEspecialidad(String especialidad) {
        return repository.findByEspecialidad(especialidad).stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ResponseDTO> getTriatletasCross(boolean modalidadCross) {
        return repository.findByModalidadCross(modalidadCross).stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO deleteTriatleta(Long id) {
        Atleta atleta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta no encontrado"));
        repository.deleteById(id);
        return toResponse(atleta);
    }
    
    @Override
    public void enviarCorreo(RequestDTO atleta){
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setTo(atleta.getEmail()); //Se envía a la dirección que haya ingresado el atleta 
        correo.setSubject("¡BIENVENID@ AL TRIATLÓN!"); //Asunto del correo 
        
        String contenido = "¡¡Hola " + atleta.getNombre() + ", gracias por incribirte "
                + "y bienevid@ al triatlón!!" ; //Mensaje que estará en el correo 
        correo.setText(contenido); //Contenido del correo 
        correo.setFrom("atjuan.vina7@gmail.com"); //Correo remitente
        
        mailSender.send(correo);
    }
}
