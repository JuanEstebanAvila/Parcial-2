package co.edu.udistrital.backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Clase del DTO encargada de recibir los datos provenientes del cliente,
 * medida de seguridad extra al no exponer la entidad "Atleta", 
 * aplicando las respectivas validaciones a cada atributo.
 * 
 * @author Grupo Programación Avanzada 
 * @version 1.3
 */


public class RequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 7,   message = "La edad mínima permitida es 7 años")
    private Integer edad;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String correo;

    private String especialidad;

    private Boolean modalidadCross;
    
    private String categoria;

    private String foto;
    
    //Constructor vacío 
    public RequestDTO() {
    }
    
    //Contructor con todos los argumentos
    public RequestDTO(String nombre, String identificacion, Integer edad, String genero, String correo, String especialidad, Boolean modalidadCross, String categoria, String foto) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
        this.especialidad = especialidad;
        this.modalidadCross = modalidadCross;
        this.categoria = categoria;
        this.foto = foto;
    }

    
    
    //Getters y Setters

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}