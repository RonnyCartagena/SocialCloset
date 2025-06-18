package models.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Prenda")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprenda")
    private Long idprenda;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_categoria")
    private String tipo_categoria;

    @Column(name = "talla")
    private String talla;

    @Column(name = "color")
    private String color;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechapublicacion", nullable = false)
    private Date fechapublicacion;

    @Column(name = "urlimagen")
    private String urlimagen;

    @ManyToOne
    @JoinColumn(name = "idcloset")
    private Closet closet;

    public Prenda() {
    }

    public Prenda(Closet closet, String color, String descripcion, Date fechapublicacion, String talla, String tipo_categoria, String urlimagen) {
        this.closet = closet;
        this.color = color;
        this.descripcion = descripcion;
        this.fechapublicacion = fechapublicacion;
        this.talla = talla;
        this.tipo_categoria = tipo_categoria;
        this.urlimagen = urlimagen;
    }

    public Closet getCloset() {
        return closet;
    }

    public void setCloset(Closet closet) {
        this.closet = closet;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Long getIdprenda() {
        return idprenda;
    }

    public void setIdprenda(Long idprenda) {
        this.idprenda = idprenda;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTipo_categoria() {
        return tipo_categoria;
    }

    public void setTipo_categoria(String tipo_categoria) {
        this.tipo_categoria = tipo_categoria;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }
}
