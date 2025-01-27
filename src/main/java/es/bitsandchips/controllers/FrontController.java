package es.bitsandchips.controllers;

import es.bitsandchips.DAO.Producto.IProductoDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FrontController", value = "/FrontController")
public class FrontController extends HttpServlet {

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

        DAOFactory daof= DAOFactory.getDAOFactory();
        IProductoDAO productoDAO= daof.getProductoDAO();

        List<Producto> productos = productoDAO.get8Productos();
        request.setAttribute("productos", productos);

        request.getRequestDispatcher("/index.jsp").forward(request, response);

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

        if (accion == null){
            accion = "inicio";
        }
        switch (accion){
            case "usuario":
                url = "./JSP/usuario.jsp";
                break;
            case "carrito":
                if (request.getSession().getAttribute("pedido") == null){
                    request.getSession().setAttribute("error", "No hay productos en el carrito");
                    this.doGet(request, response);
                    return;
                } else{
                    url = "./JSP/carrito.jsp";
                }

                break;
            default:
                this.doGet(request, response);
                return;
        }
        request.getSession().removeAttribute("error");
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