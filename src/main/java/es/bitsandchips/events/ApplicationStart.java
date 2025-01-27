package es.bitsandchips.events;

import es.bitsandchips.DAOFactory.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationStart implements ServletContextListener {

    /**
     * Método que se ejecuta al arrancar la aplicación
     * @param sce servlet context event
     *            Se encarga de cargar las categorías y marcas en el contexto de la aplicación
     */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        DAOFactory daof = DAOFactory.getDAOFactory();
        contexto.setAttribute("categorias", daof.getCategoriaDAO().getAllCategoria());
        contexto.setAttribute("marcas", daof.getProductoDAO().getAllMarcas());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
