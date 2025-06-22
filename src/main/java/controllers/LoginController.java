package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.service.UsuarioService;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        usuarioService = new UsuarioService();  // Crear una instancia de servicio de usuario
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");  // Ahora recibimos el correo electrónico
        String password = request.getParameter("password");

        if (usuarioService.validarUsuarioPorEmail(email, password)) {
            // Si las credenciales son correctas
            response.sendRedirect("views/Home.jsp");
        } else {
            // Si las credenciales son incorrectas
            request.setAttribute("errorMessage", "Credenciales inválidas");
            request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
        }
    }
}
