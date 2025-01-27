package es.bitsandchips.controllers.carrito;

import es.bitsandchips.DAO.Pedido.IPedidoDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.LineasPedidos;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.models.CarritoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Carrito", value = "/Carrito"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class Carrito extends HttpServlet {

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

        String idProducto = request.getParameter("agregar");
        DAOFactory daof = DAOFactory.getDAOFactory();
        IPedidoDAO pedidoDAO = daof.getPedidoDAO();
        request.getSession().removeAttribute("error");

        if (request.getSession().getAttribute("pedido") == null) {
            LineasPedidos lineaPedido = CarritoModel.newLineaPedidos(Short.parseShort(idProducto));

            Pedido pedido = CarritoModel.newPedido(lineaPedido);

            if (request.getSession().getAttribute("usuario") != null) {
                pedido.setUsuario((es.bitsandchips.beans.Usuario) request.getSession().getAttribute("usuario"));
                pedidoDAO.addPedido(pedido);
                pedido = pedidoDAO.getPedidoByUsuario(pedido.getUsuario().getId());
            }

            request.getSession().setAttribute("pedido", pedido);
        } else {
            Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
            List<LineasPedidos> listaLineasPedidos = pedido.getLineasPedidos();

            CarritoModel.addLineaPedido(listaLineasPedidos, idProducto);

            CarritoModel.updatePedido(pedido, listaLineasPedidos);

            if (request.getSession().getAttribute("usuario") != null) {
                pedido.setUsuario((es.bitsandchips.beans.Usuario) request.getSession().getAttribute("usuario"));
                pedidoDAO.updatePedido(pedido);
            }

            request.getSession().setAttribute("pedido", pedido);
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