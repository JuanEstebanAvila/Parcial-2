package co.edu.udistrital.backend.model;

/**
 * Clase DTO encargada de enviar los datos al cliente.
 * Evita exponer directamente la entidad Atleta. Incluye la categoría calculada
 * automáticamente por el servicio según la edad.
 * 
 * @author Grupo Programación Avanzada
 * @version 1.4
 */

public class ResponseDTO {
    
    private Long id;
    private String nombre;
    private String identificacion;
    private Integer edad;
    private String genero;
    private String correo;
    private String categoria;
    private String especialidad;
    private Boolean modalidadCross;
    private String foto;
    
    //Constructor vacío 
    public ResponseDTO() {
    }
    
    //Constructor con todos los argumentos
    public ResponseDTO(Long id, String nombre, String identificacion, Integer edad, String genero, String correo, String categoria, String especialidad, Boolean modalidadCross, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
        this.categoria = categoria;
        this.especialidad = especialidad;
        this.modalidadCross = modalidadCross;
        this.foto = foto;
    }
    
    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Boolean getModalidadCross() {
        return modalidadCross;
    }

    public void setModalidadCross(Boolean modalidadCross) {
        this.modalidadCross = modalidadCross;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
}
