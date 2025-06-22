package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.service.UsuarioService;
import models.entities.Usuario;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        usuarioService = new UsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Obtener el usuario completo en lugar de solo validar
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email, password);

        if (usuario != null) {
            // Crear sesión y guardar información del usuario
            HttpSession session = request.getSession();
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            session.setAttribute("nombreUsuario", usuario.getNombre());
            session.setAttribute("emailUsuario", usuario.getEmail());

            // Configurar tiempo de vida de la sesión (30 minutos)
            session.setMaxInactiveInterval(30 * 60);

            System.out.println("Usuario logueado exitosamente: " + usuario.getNombre() + " (ID: " + usuario.getIdUsuario() + ")");

            response.sendRedirect("views/Home.jsp");
        } else {
            // Si las credenciales son incorrectas
            request.setAttribute("errorMessage", "Credenciales inválidas");
            request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Manejar logout si se envía el parámetro
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/views/Login.jsp");
        } else {
            // Redirigir al login si no hay acción específica
            response.sendRedirect("/views/Login.jsp");
        }
    }
}