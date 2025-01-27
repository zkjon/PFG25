package es.bitsandchips.controllers.carrito;

import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.beans.Usuario;
import es.bitsandchips.models.CarritoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CarritoUpdate", value = "/CarritoUpdate"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class CarritoUpdate extends HttpServlet {

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
        String accion = request.getParameter("accion");
        String url = ".";

        if (accion.startsWith("e")) {
            short idLinea = Short.parseShort(accion.split("-")[1]);
            if (request.getSession().getAttribute("usuario") != null) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                DAOFactory.getDAOFactory().getPedidoDAO().deleteLineaPedido(idLinea);
                Pedido pedido = DAOFactory.getDAOFactory().getPedidoDAO().getPedidoByUsuario(usuario.getId());

                if (pedido == null || pedido.getLineasPedidos() == null || pedido.getLineasPedidos().isEmpty()) {
                    if (pedido != null) {
                        DAOFactory.getDAOFactory().getPedidoDAO().deletePedido(pedido);
                    }
                    request.getSession().removeAttribute("pedido");
                } else {
                    url = "./JSP/carrito.jsp";
                    request.getSession().setAttribute("pedido", pedido);
                }
            } else {
                short idProducto = Short.parseShort(accion.split("-")[1]);
                Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                CarritoModel.deleteLineaPedido(idProducto, pedido);
                request.getSession().setAttribute("pedido", pedido);

                if (pedido.getLineasPedidos().isEmpty()) {
                    request.getSession().removeAttribute("pedido");
                } else {
                    url = "./JSP/carrito.jsp";
                }
            }
        } else if (accion.startsWith("s")) {
            short idProducto = Short.parseShort(accion.split("-")[1]);
            Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");

            CarritoModel.sumarCantidad(idProducto, pedido);
            if (request.getSession().getAttribute("usuario") != null) {
                DAOFactory.getDAOFactory().getPedidoDAO().actualizarCantidadProducto(idProducto, pedido, 1);
            }
            request.getSession().setAttribute("pedido", pedido);
            url = "./JSP/carrito.jsp";
        } else if (accion.startsWith("r")) {
            short idProducto = Short.parseShort(accion.split("-")[1]);
            Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");

            CarritoModel.restarCantidad(idProducto, pedido);
            if (request.getSession().getAttribute("usuario") != null) {
                DAOFactory.getDAOFactory().getPedidoDAO().actualizarCantidadProducto(idProducto, pedido, -1);
            }
            request.getSession().setAttribute("pedido", pedido);
            url = "./JSP/carrito.jsp";
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