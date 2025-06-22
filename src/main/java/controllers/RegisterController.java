package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.service.UsuarioService;

import java.io.IOException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private UsuarioService usuarioService;

    public void init() {
        usuarioService = new UsuarioService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Mostrar el formulario de registro
        request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String email = request.getParameter("email");

        // Validación básica
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("errorMessage", "El nombre de usuario no puede estar vacío.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "La contraseña no puede estar vacía.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            return;
        }

        if (confirmPassword == null || !password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "El correo electrónico no puede estar vacío.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            return;
        }

        // Validaciones adicionales
        if (password.length() < 6) {
            request.setAttribute("errorMessage", "La contraseña debe tener al menos 6 caracteres.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            return;
        }

        try {
            // Llamada al servicio para registrar el usuarioz
            boolean success = usuarioService.registrarUsuario(username, password, email);

            if (success) {
                // Redirige al login después de un registro exitoso
                request.setAttribute("successMessage", "Usuario registrado exitosamente. Inicia sesión.");
                request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
            } else {
                // Si el registro falla, redirige al formulario de registro con un mensaje de error
                request.setAttribute("errorMessage", "El nombre de usuario o correo electrónico ya está en uso, o ocurrió un error interno.");
                request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Manejo de errores inesperados
            System.err.println("Error en el registro: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Ocurrió un error interno. Inténtalo de nuevo.");
            request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
        }
    }
}