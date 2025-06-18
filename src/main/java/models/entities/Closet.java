package models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Closet")
public class Closet {
    @Id //indica el campo de clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCloset")
    private Long idCloset;

    @Column(name = "publicado", nullable = false)
    private boolean publicado;

    @OneToOne
    @JoinColumn(name="idUsuario", nullable = false)
    private Usuario usuario;

    //Constructor vacio obligatorio para JPA
    public Closet() {
    }

    // Constructor opcional para facilitar creación

    public Closet(boolean publicado, Usuario usuario) {
        this.publicado = publicado;
        this.usuario = usuario;
    }


    //getters y setters de los atributos (excepto la PK)

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
