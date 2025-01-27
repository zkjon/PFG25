package es.bitsandchips.controllers.carrito;

import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.beans.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VaciarCarrito", value = "/VaciarCarrito"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class VaciarCarrito extends HttpServlet {

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
        if (request.getSession().getAttribute("usuario") != null){
            DAOFactory daof = DAOFactory.getDAOFactory();
            Pedido pedido = daof.getPedidoDAO().getPedidoByUsuario(((Usuario) request.getSession().getAttribute("usuario")).getId());
            daof.getPedidoDAO().deletePedido(pedido);
        }
        request.getSession().removeAttribute("pedido");
        request.getSession().setAttribute("error", "Carrito vaciado");
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