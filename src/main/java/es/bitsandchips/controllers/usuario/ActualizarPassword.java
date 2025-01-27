package es.bitsandchips.controllers.usuario;

import es.bitsandchips.DAO.Usuario.IUsuarioDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Usuario;
import es.bitsandchips.models.UtilidadesBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActualizarPassword", value = "/ActualizarPassword"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class ActualizarPassword extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuarioDAO usuarioDAO = daof.getUsuarioDAO();

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String contrasena = request.getParameter("contrasena");
        String nuevaContrasena = request.getParameter("nuevaContrasena");
        String confirmarContrasena = request.getParameter("repetirContrasena");
        String email = usuario.getEmail();

        if (UtilidadesBC.md5(contrasena).equals(usuario.getContrasena())) {
            if (nuevaContrasena.equals(confirmarContrasena)){
                usuario.setContrasena(UtilidadesBC.md5(nuevaContrasena));
                usuarioDAO.updatePassword(usuario);
                request.setAttribute("exito", "Contraseña actualizada correctamente");
                request.getSession().setAttribute("usuario", usuarioDAO.getUsuarioByEmail(email));
            } else {
                request.setAttribute("error", "Las contraseñas no coinciden");
            }
        } else {
            request.setAttribute("error", "La contraseña actual no es correcta");
        }
        request.getRequestDispatcher("/JSP/usuario.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}