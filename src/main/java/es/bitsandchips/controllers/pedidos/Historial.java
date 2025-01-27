package es.bitsandchips.controllers.pedidos;

import es.bitsandchips.DAO.Pedido.IPedidoDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.beans.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Historial", value = "/Historial"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class Historial extends HttpServlet {

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
        IPedidoDAO pedidoDAO = daof.getPedidoDAO();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        List<Pedido> pedidos = pedidoDAO.getCompletadosByUsuario(usuario.getId());

        String url= ".";
        if (pedidos.isEmpty()) {
            request.getSession().setAttribute("error", "No hay pedidos completados");
        } else {
            request.setAttribute("pedidos", pedidos);
            url = "./JSP/pedidos.jsp";
        }



        request.getRequestDispatcher(url).forward(request, response);



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