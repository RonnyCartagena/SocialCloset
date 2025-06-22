package models.dao;

import jakarta.persistence.Query;
import models.entities.Closet;
import models.entities.Usuario;

public class ClosetDAO extends GenericDAO<Closet> {

    public ClosetDAO() {
        super(Closet.class);
    }

    // Método para buscar closet por ID de usuario
    public Closet findByUsuarioId(Long usuarioId) {
        try {
            Query query = entityManager.createQuery("SELECT c FROM Closet c WHERE c.usuario.idUsuario = :usuarioId");
            query.setParameter("usuarioId", usuarioId);
            return (Closet) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Closet no encontrado para usuario ID: " + usuarioId + " - " + e.getMessage());
            return null;
        }
    }

    // Método para buscar closet por usuario
    public Closet findByUsuario(Usuario usuario) {
        try {
            Query query = entityManager.createQuery("SELECT c FROM Closet c WHERE c.usuario = :usuario");
            query.setParameter("usuario", usuario);
            return (Closet) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Closet no encontrado para usuario: " + usuario.getNombre() + " - " + e.getMessage());
            return null;
        }
    }
}