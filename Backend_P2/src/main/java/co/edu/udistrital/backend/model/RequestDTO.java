package co.edu.udistrital.backend.model;

import lombok.Data;

/**
 * Clase del DTO encargada de recibir los datos provenientes del cliente,
 * medida de seguridad extra al no exponer la entidad "Atleta",  
 * aunque tenga los mismos atributos que la entidad.
 * @author Grupo Programación Avanzada 
 * @version 1.0
 */



public class RequestDTO {
    
private String nombre;
private Long identificacion;
private Integer edad;
private String genero;
private String categoria;
private String especialidad;
private Boolean cross; 
private String email;
private String foto;

    public RequestDTO(String nombre, Long identificacion, Integer edad, String genero, String categoria, String especialidad, Boolean cross, String email, String foto) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.genero = genero;
        this.categoria = categoria;
        this.especialidad = especialidad;
        this.cross = cross;
        this.email = email;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
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

    public Boolean isCross() {
        return cross;
    }

    public void setCross(Boolean cross) {
        this.cross = cross;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    
}

//Para el request aún falta agregar que reciba como parametro la foto del atleta 
