package co.edu.udistrital.backend.model;

/**
 * Clase del DTO encargada de recibir los datos provenientes del cliente,
 * medida de seguridad extra al no exponer la entidad de Atleta 
 * aunque tenga los mismos atributos que la entidad
 * @author Grupo Programación Avanzada 
 * @version 1.0
 */

//No usamos las anotaciones de loombok
public class RequestDTO {
private String nombre;
private long identificacion;
private int edad;
private String genero;
private String categoria;
private String especialidad;
private boolean cross;
private String email;

//Constructor 

    public RequestDTO(String nombre, long identificacion, int edad, String genero, String categoria, String especialidad, boolean cross, String email) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.genero = genero;
        this.categoria = categoria;
        this.especialidad = especialidad;
        this.cross = cross;
        this.email = email;
    }

//Getters y Setters de los atributos 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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

    public boolean isCross() {
        return cross;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
}
