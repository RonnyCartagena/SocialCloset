package models.service;

import models.dao.PrendaDAO;
import models.dao.ClosetDAO;
import models.dao.UsuarioDAO;
import models.entities.Prenda;
import models.entities.Closet;
import models.entities.Usuario;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PrendaService {

    private PrendaDAO prendaDAO;
    private ClosetDAO closetDAO;
    private UsuarioDAO usuarioDAO;

    public PrendaService() {
        this.prendaDAO = new PrendaDAO();
        this.closetDAO = new ClosetDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean agregarPrenda(Long usuarioId, String titulo, String descripcion, String tipoCategoria, String talla, String color) {
        try {
            // 1. Buscar el usuario
            Usuario usuario = usuarioDAO.findById(usuarioId);
            if (usuario == null) {
                System.out.println("Error: Usuario no encontrado con ID: " + usuarioId);
                return false;
            }

            // 2. Buscar o crear el closet del usuario
            Closet closet = closetDAO.findByUsuario(usuario);
            if (closet == null) {
                // Si no tiene closet, crear uno
                closet = new Closet(usuario);
                boolean closetCreated = closetDAO.create(closet);
                if (!closetCreated) {
                    System.out.println("Error: No se pudo crear el closet para el usuario");
                    return false;
                }
                System.out.println("Closet creado para el usuario: " + usuario.getNombre());
            }

            // 3. Crear la prenda
            Prenda nuevaPrenda = new Prenda(
                    closet,
                    titulo.trim(),
                    color.trim(),
                    descripcion.trim(),
                    Date.valueOf(LocalDate.now()),
                    talla.trim(),
                    tipoCategoria.trim(),
                    null // URL de imagen por ahora null
            );

            // 4. Guardar la prenda
            boolean success = prendaDAO.create(nuevaPrenda);

            if (success) {
                System.out.println("Prenda agregada exitosamente:");
                System.out.println("- Título: " + titulo);
                System.out.println("- Usuario: " + usuario.getNombre());
                System.out.println("- Closet ID: " + closet.getIdCloset());
            }

            return success;

        } catch (Exception e) {
            System.out.println("Error al agregar prenda: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Prenda> obtenerPrendasDelUsuario(Long usuarioId) {
        try {
            // 1. Buscar el usuario
            Usuario usuario = usuarioDAO.findById(usuarioId);
            if (usuario == null) {
                System.out.println("Error: Usuario no encontrado con ID: " + usuarioId);
                return List.of();
            }

            // 2. Buscar el closet del usuario
            Closet closet = closetDAO.findByUsuario(usuario);
            if (closet == null) {
                System.out.println("Usuario no tiene closet aún: " + usuario.getNombre());
                return List.of();
            }

            // 3. Obtener las prendas del closet
            return prendaDAO.findByCloset(closet);

        } catch (Exception e) {
            System.out.println("Error al obtener prendas del usuario: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean eliminarPrenda(Long prendaId, Long usuarioId) {
        try {
            // 1. Buscar la prenda
            Prenda prenda = prendaDAO.findById(prendaId);
            if (prenda == null) {
                System.out.println("Error: Prenda no encontrada con ID: " + prendaId);
                return false;
            }

            // 2. Verificar que la prenda pertenece al usuario
            if (!prenda.getCloset().getUsuario().getIdUsuario().equals(usuarioId)) {
                System.out.println("Error: La prenda no pertenece al usuario con ID: " + usuarioId);
                return false;
            }

            // 3. Eliminar la prenda
            boolean success = prendaDAO.remove(prendaId);

            if (success) {
                System.out.println("Prenda eliminada exitosamente: " + prenda.getTitulo());
            }

            return success;

        } catch (Exception e) {
            System.out.println("Error al eliminar prenda: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Prenda> obtenerPrendasPublicas() {
        try {
            return prendaDAO.findPrendasPublicas();
        } catch (Exception e) {
            System.out.println("Error al obtener prendas públicas: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Prenda> obtenerPrendasPorCategoria(String categoria) {
        try {
            return prendaDAO.findByCategoria(categoria);
        } catch (Exception e) {
            System.out.println("Error al obtener prendas por categoría: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}