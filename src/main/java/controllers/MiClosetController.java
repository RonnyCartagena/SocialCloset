package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.service.PrendaService;
import models.entities.Prenda;
import java.io.IOException;
import java.util.List;

@WebServlet("/MiClosetController")
public class MiClosetController extends HttpServlet {

    private PrendaService prendaService = new PrendaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Verificar sesión válida
        if (session == null || session.getAttribute("idUsuario") == null) {
            System.out.println("Sesión inválida en MiClosetController");
            response.sendRedirect(request.getContextPath() + "/views/Login.jsp");
            return;
        }

        Long idUsuario = (Long) session.getAttribute("idUsuario");
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        System.out.println("Usuario en sesión: " + nombreUsuario + " (ID: " + idUsuario + ")");

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String tipoCategoria = request.getParameter("tipo_categoria");
        String talla = request.getParameter("talla");
        String color = request.getParameter("color");

        // Validar parámetros
        if (titulo == null || titulo.trim().isEmpty()) {
            request.setAttribute("errorMessage", "El título es obligatorio.");
            cargarPrendasYRedirigir(request, response, idUsuario);
            return;
        }

        // Llamar al servicio para agregar la prenda
        boolean success = prendaService.agregarPrenda(idUsuario, titulo, descripcion, tipoCategoria, talla, color);

        // Agregar mensaje de éxito o error
        if (success) {
            request.setAttribute("successMessage", "Prenda agregada exitosamente.");
            System.out.println("Prenda agregada por usuario: " + nombreUsuario);
        } else {
            request.setAttribute("errorMessage", "Hubo un error al agregar la prenda.");
        }

        cargarPrendasYRedirigir(request, response, idUsuario);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Verificar sesión válida
        if (session == null || session.getAttribute("idUsuario") == null) {
            System.out.println("Sesión inválida en MiClosetController GET");
            response.sendRedirect(request.getContextPath() + "/views/Login.jsp");
            return;
        }

        Long idUsuario = (Long) session.getAttribute("idUsuario");
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        // Verificar si es una acción de eliminar
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            String idPrendaStr = request.getParameter("id");
            if (idPrendaStr != null && !idPrendaStr.isEmpty()) {
                try {
                    Long idPrenda = Long.parseLong(idPrendaStr);
                    boolean eliminado = prendaService.eliminarPrenda(idPrenda, idUsuario);

                    if (eliminado) {
                        request.setAttribute("successMessage", "Prenda eliminada exitosamente.");
                        System.out.println("Prenda eliminada por usuario: " + nombreUsuario + " (ID Prenda: " + idPrenda + ")");
                    } else {
                        request.setAttribute("errorMessage", "No se pudo eliminar la prenda.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "ID de prenda inválido.");
                }
            } else {
                request.setAttribute("errorMessage", "ID de prenda no especificado.");
            }
        }

        System.out.println("Cargando closet para usuario: " + nombreUsuario + " (ID: " + idUsuario + ")");

        cargarPrendasYRedirigir(request, response, idUsuario);
    }

    /**
     * Método auxiliar para cargar las prendas del usuario y redirigir a la vista
     */
    private void cargarPrendasYRedirigir(HttpServletRequest request, HttpServletResponse response, Long idUsuario)
            throws ServletException, IOException {

        // Obtener todas las prendas del usuario y enviarlas a la vista
        List<Prenda> prendas = prendaService.obtenerPrendasDelUsuario(idUsuario);
        request.setAttribute("prendas", prendas);

        System.out.println("Prendas encontradas para el usuario: " + prendas.size());

        // Redirigir a la vista de "MiCloset.jsp"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/MiCloset.jsp");
        dispatcher.forward(request, response);
    }
}