package es.bitsandchips.DAOFactory;

import es.bitsandchips.DAO.Categoria.CategoriaDAO;
import es.bitsandchips.DAO.Categoria.ICategoriaDAO;
import es.bitsandchips.DAO.Pedido.IPedidoDAO;
import es.bitsandchips.DAO.Pedido.PedidoDAO;
import es.bitsandchips.DAO.Producto.IProductoDAO;
import es.bitsandchips.DAO.Producto.ProductoDAO;
import es.bitsandchips.DAO.Usuario.IUsuarioDAO;
import es.bitsandchips.DAO.Usuario.UsuarioDAO;

/**
 * Clase concreta de la fábrica de DAO para la base de datos MySQL.
 *
 * <p>Extiende la clase abstracta {@code DAOFactory} y proporciona implementaciones
 * específicas de los métodos para obtener objetos DAO, como {@code ICategoriaDAO},
 * {@code IProductoDAO} y {@code IUsuarioDAO}.
 */
public class SQLDAOFactory extends DAOFactory {

    /**
     * Devuelve una implementación concreta de {@code ICategoriaDAO} para MySQL.
     *
     * @return una instancia de {@code CategoriaDAO}.
     */
    @Override
    public ICategoriaDAO getCategoriaDAO() {
        return new CategoriaDAO();
    }

    /**
     * Devuelve una implementación concreta de {@code IProductoDAO} para MySQL.
     *
     * @return una instancia de {@code ProductoDAO}.
     */
    @Override
    public IProductoDAO getProductoDAO() {
        return new ProductoDAO();
    }

    /**
     * Devuelve una implementación concreta de {@code IPedidoDAO} para MySQL.
     *
     * @return una instancia de {@code PedidoDAO}.
     */
    @Override
    public IPedidoDAO getPedidoDAO() {
        return new PedidoDAO();
    }

    /**
     * Devuelve una implementación concreta de {@code IUsuarioDAO} para MySQL.
     *
     * @return una instancia de {@code UsuarioDAO}.
     */
    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }


}
