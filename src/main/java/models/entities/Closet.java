package models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Closet")
public class Closet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCloset")
    private Long idCloset;

    @Column(name = "publicado", nullable = false)
    private boolean publicado = true; // Valor por defecto

    // ✅ CORRECCIÓN: Mapear correctamente la FK
    @OneToOne
    @JoinColumn(name="idUsuario", nullable = false, unique = true)
    private Usuario usuario;

    // Constructor vacío obligatorio para JPA
    public Closet() {
    }

    public Closet(Usuario usuario) {
        this.usuario = usuario;
        this.publicado = true; // Valor por defecto
    }

    // Constructor opcional para facilitar creación
    public Closet(boolean publicado, Usuario usuario) {
        this.publicado = publicado;
        this.usuario = usuario;
    }

    // Getters y setters
    public Long getIdCloset() {
        return idCloset;
    }

    public void setIdCloset(Long idCloset) {
        this.idCloset = idCloset;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}