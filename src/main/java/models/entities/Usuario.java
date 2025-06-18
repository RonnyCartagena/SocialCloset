package models.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nombreUsuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE) //guarda solo el dia
    @Column(name = "fechaRegistro", nullable = false)
    private Date fechaRegistro;

    // Relación ManyToOne hacia Closet (FK)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCloset", nullable = false)
    private Closet closet;

    // Constructor vacío para JPA
    public Usuario() {
    }

    // Constructor opcional
    public Usuario(String nombreUsuario, String correo, String password, Date fechaRegistro, Closet closet) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.closet = closet;
    }

    public Closet getCloset() {
        return closet;
    }

    public void setCloset(Closet closet) {
        this.closet = closet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
