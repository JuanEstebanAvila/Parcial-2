package co.edu.udistrital.backend.model;

/**
 * Clase DTO encargada de enviar los datos al cliente.
 * Evita exponer directamente la entidad Atleta.
 * @author Grupo Programación Avanzada
 * @version 1.0 S
 */
public class ResponseDTO {

    private Long id;
    private String nombre;
    private long identificacion;
    private int edad;
    private String genero;
    private String categoria;
    private String especialidad;
    private boolean cross;
    private String email;
    private String foto;

    public ResponseDTO() {}

    public ResponseDTO(Long id, String nombre, long identificacion, int edad,
                       String genero, String categoria, String especialidad,
                       boolean cross, String email, String foto) {
        this.id = id;
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public long getIdentificacion() { return identificacion; }
    public void setIdentificacion(long identificacion) { this.identificacion = identificacion; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public boolean isCross() { return cross; }
    public void setCross(boolean cross) { this.cross = cross; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}
