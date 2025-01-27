package es.bitsandchips.controllers.usuario;

import es.bitsandchips.DAO.Usuario.IUsuarioDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Usuario;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "Actualizar", value = "/Actualizar")
public class Actualizar extends HttpServlet {

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
        Usuario usuario = new Usuario();
        try {
            BeanUtils.populate(usuario, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        usuarioDAO.updateUsuario(usuario);
        request.setAttribute("exito", "Usuario actualizado correctamente");
        request.getSession().setAttribute("usuario", usuarioDAO.getUsuarioByEmail(usuario.getEmail()));
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