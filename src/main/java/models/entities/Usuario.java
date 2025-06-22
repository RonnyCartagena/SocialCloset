package models.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.sql.Date;

@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaRegistro", nullable = false)
    private Date fechaRegistro;

    // ✅ CORRECCIÓN: Mapear correctamente la relación OneToOne
    // Como Closet tiene la FK (idUsuario), Usuario es el lado "mappedBy"
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Closet closet;

    // Constructor vacío para JPA
    public Usuario() {
    }

    // Constructor para registro (sin crear closet automáticamente)
    public Usuario(String nombre, String password, String email) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.fechaRegistro = Date.valueOf(LocalDate.now());
        // ❌ NO crear closet aquí - se creará separadamente
    }

    // Constructor completo
    public Usuario(String nombre, String email, String password, Date fechaRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : Date.valueOf(LocalDate.now());
    }

    // Método para establecer la fecha de registro automáticamente antes de persistir
    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = Date.valueOf(LocalDate.now());
        }
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Closet getCloset() {
        return closet;
    }

    public void setCloset(Closet closet) {
        this.closet = closet;
    }
}