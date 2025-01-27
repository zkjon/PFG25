package es.bitsandchips.DAO.Categoria;

import es.bitsandchips.beans.Categoria;

import java.sql.Connection;
import java.util.List;

public interface ICategoriaDAO {
    /**
     * Obtiene una lista de todas las categorías.
     *
     * @return una lista de objetos Categoria
     */
    public List<Categoria> getAllCategoria();

    /**
     * Obtiene una categor&iacute;a por su ID.
     *
     * @param id el ID de la categoría
     * @return un objeto Categoria
     */
    public Categoria getCategoriaById(short i, Connection connection);


    /**
     * Cierra la conexión a la base de datos.
     */
    public void closeConnection();
}
