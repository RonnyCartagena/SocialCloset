package models.dao;

import jakarta.persistence.Query;
import models.entities.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    //metodo para buscar el correo
    public Usuario findByEmail(String email) {
        try{
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
            query.setParameter("email", email);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // metodo para buscar usuario por nombre de usuario
    public Usuario findByUsername(String nombre) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre");
            query.setParameter("nombre", nombre);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return null; // Si no se encuentra, devuelve null
        }
    }
}
