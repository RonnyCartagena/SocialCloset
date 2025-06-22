package models.dao;

import jakarta.persistence.Query;
import models.entities.Prenda;
import models.entities.Closet;
import java.util.List;

public class PrendaDAO extends GenericDAO<Prenda> {

    public PrendaDAO() {
        super(Prenda.class);
    }

    // Método para buscar todas las prendas de un closet específico
    public List<Prenda> findByCloset(Closet closet) {
        try {
            Query query = entityManager.createQuery("SELECT p FROM Prenda p WHERE p.closet = :closet ORDER BY p.fechapublicacion DESC");
            query.setParameter("closet", closet);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al buscar prendas del closet: " + e.getMessage());
            return List.of();
        }
    }

    // Método para buscar prendas por closet ID
    public List<Prenda> findByClosetId(Long closetId) {
        try {
            Query query = entityManager.createQuery("SELECT p FROM Prenda p WHERE p.closet.idCloset = :closetId ORDER BY p.fechapublicacion DESC");
            query.setParameter("closetId", closetId);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al buscar prendas por closet ID: " + e.getMessage());
            return List.of();
        }
    }

    // Método para buscar prendas por tipo de categoría
    public List<Prenda> findByCategoria(String categoria) {
        try {
            Query query = entityManager.createQuery("SELECT p FROM Prenda p WHERE p.tipo_categoria = :categoria");
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al buscar prendas por categoría: " + e.getMessage());
            return List.of();
        }
    }

    // Método para buscar prendas disponibles para trueque (closets públicos)
    public List<Prenda> findPrendasPublicas() {
        try {
            Query query = entityManager.createQuery("SELECT p FROM Prenda p WHERE p.closet.publicado = true ORDER BY p.fechapublicacion DESC");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al buscar prendas públicas: " + e.getMessage());
            return List.of();
        }
    }
}