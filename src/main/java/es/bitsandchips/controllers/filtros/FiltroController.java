package es.bitsandchips.controllers.filtros;

import es.bitsandchips.DAO.Producto.IProductoDAO;
import es.bitsandchips.DAOFactory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FiltroController", value = "/FiltroController"/*, initParams = {
        @WebInitParam(name = "Primero", value = "Hola"),
        @WebInitParam(name = "Segundo", value = "Mundo")}*/)
public class FiltroController extends HttpServlet {

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

        String precio = request.getParameter("precio");
        String[] categorias = request.getParameterValues("categoria");
        String[] marcas = request.getParameterValues("marca");

        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductoDAO productoDAO = daof.getProductoDAO();

        request.setAttribute("productos", productoDAO.getProductosByFiltros(precio, categorias, marcas));
        request.setAttribute("precio", precio+"€");
        request.getRequestDispatcher("./index.jsp").forward(request, response);

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