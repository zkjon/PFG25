package es.bitsandchips.controllers.usuario;

import es.bitsandchips.DAO.Pedido.IPedidoDAO;
import es.bitsandchips.DAO.Usuario.IUsuarioDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.beans.Usuario;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "Acceso", value = "/Acceso")
public class Acceso extends HttpServlet {

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
        IPedidoDAO pedidoDAO = daof.getPedidoDAO();
        Usuario usuario = null;
        boolean registrarse = request.getParameter("registrarse") != null;

        if (registrarse) {
            if (usuarioDAO.getUsuarioByEmail(request.getParameter("email")) != null) {
                request.getSession().setAttribute("error", "El email ya está en uso");
            } else {
                request.getSession().removeAttribute("error");
                try {
                    usuario = new Usuario();
                    BeanUtils.populate(usuario, request.getParameterMap());
                    usuarioDAO.addUsuario(usuario);
                    usuario = usuarioDAO.getUsuarioByEmail(request.getParameter("email"));
                    if (request.getSession().getAttribute("pedido") != null) {
                        Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                        pedido.setUsuario(usuario);
                        pedido.setFecha(new java.sql.Date(new java.util.Date().getTime()));
                        pedidoDAO.addPedido(pedido);
                        request.getSession().setAttribute("pedido", pedidoDAO.getPedidoByUsuario(usuario.getId()));
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                request.getSession().setAttribute("usuario", usuario);
            }
        } else { // iniciar sesion

            usuario = usuarioDAO.getUsuarioByEmailAndPassword(request.getParameter("email"), request.getParameter("pass"));
            if(usuario != null) { // entró


                if (pedidoDAO.getPedidoByUsuario(usuario.getId()) != null) {
                    request.getSession().setAttribute("pedido", pedidoDAO.getPedidoByUsuario(usuario.getId()));
                } else {
                    if (request.getSession().getAttribute("pedido") != null) {
                        Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                        pedido.setUsuario(usuario);
                        pedidoDAO.addPedido(pedido);
                        request.getSession().setAttribute("pedido", pedido);
                    }
                }

                request.getSession().removeAttribute("error");
                usuario.setUltimoAcceso(new java.sql.Timestamp(new java.util.Date().getTime()));
                request.getSession().setAttribute("usuario", usuario);
            } else {
                request.getSession().setAttribute("error", "Usuario o contraseña incorrectos");
            }
        }
        request.getRequestDispatcher(".").forward(request, response);
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