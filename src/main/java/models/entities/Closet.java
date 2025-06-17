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

    //Constructor vacio obligatorio para JPA
    public Closet() {
    }

    // Constructor opcional para facilitar creación
    public Closet(boolean publicado) {
        this.publicado = publicado;
    }

    //getters y setters de los atributos (excepto la PK)
    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }
}
