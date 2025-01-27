package es.bitsandchips.DAOFactory;

import es.bitsandchips.DAO.Categoria.ICategoriaDAO;
import es.bitsandchips.DAO.Pedido.IPedidoDAO;
import es.bitsandchips.DAO.Producto.IProductoDAO;
import es.bitsandchips.DAO.Usuario.IUsuarioDAO;

public abstract class DAOFactory {

    /**
     * Obtiene una implementaci&oacute;n de la interfaz {@code ICategoriaDAO}.
     *
     * @return una instancia de {@code ICategoriaDAO}.
     */
    public abstract ICategoriaDAO getCategoriaDAO();

    /**
     * Obtiene una implementaci&oacute;n de la interfaz {@code IProductoDAO}.
     *
     * @return una instancia de {@code IProductoDAO}.
     */
    public abstract IProductoDAO getProductoDAO();

    /**
     * Obtiene una implementación de la interfaz {@code IUsuarioDAO}.
     *
     * @return una instancia de {@code IUsuarioDAO}.
     */
    public abstract IUsuarioDAO getUsuarioDAO();

    /**
     * Obtiene una implementación de la interfaz {@code IPedidoDAO}.
     *
     * @return una instancia de {@code IPedidoDAO}.
     */
    public abstract IPedidoDAO getPedidoDAO();

    /**
     * Devuelve una instancia de la fábrica concreta de DAO.
     *
     * <p>Este método utiliza una implementación específica, como {@code MySQLDAOFactory},
     * para devolver una instancia de la fábrica concreta.
     *
     * @return un objeto de la clase concreta que extiende {@code DAOFactory}.
     */
    public static DAOFactory getDAOFactory() {
        DAOFactory daof = null;

        daof = new SQLDAOFactory();

        return daof;
    }
}

