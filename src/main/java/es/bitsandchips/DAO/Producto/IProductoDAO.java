package es.bitsandchips.DAO.Producto;

import es.bitsandchips.beans.Producto;

import java.util.List;

public interface IProductoDAO {

    /**
     * Recupera 8 productos aleatorios de la base de datos.
     *
     * @return una lista de 8 productos aleatorios
     */
    public List<Producto> get8Productos();

    /**
     * Recupera un producto por su ID.
     *
     * @param id el ID del producto
     * @return el producto con el ID especificado
     */
    public Producto getProductoById(short id);

    /**
     * Recupera productos que coinciden con la descripción dada.
     *
     * @param descripcion la descripción para buscar
     * @return una lista de productos que coinciden con la descripción
     */
    public List<Producto> getProductoByDescripcion(String descripcion);

    /**
     * Recupera todas las marcas distintas de la base de datos.
     *
     * @return una lista de todas las marcas distintas
     */
    public List<String> getAllMarcas();

    /**
     * Recupera productos que coinciden con los filtros dados.
     *
     * @param precio el precio máximo de los productos
     * @param categorias las categorías para filtrar
     * @param marcas las marcas para filtrar
     * @return una lista de productos que coinciden con los filtros
     */
    public List<Producto> getProductosByFiltros(String precio, String[] categorias, String[] marcas);

    /**
     * Recupera el precio de un producto por su ID.
     *
     * @param id el ID del producto
     * @return el precio del producto con el ID especificado
     */
    public short getPrecioById(short id);



    /**
     * Cierra la conexión a la base de datos.
     */
    public void closeConnection();
}
