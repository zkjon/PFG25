package es.bitsandchips.DAO.Pedido;

import es.bitsandchips.beans.Pedido;

import java.util.List;

public interface IPedidoDAO {

    /**
     * Obtiene el pedido de un usuario.
     * @param idUsuario id del usuario del que queremos obtener el pedido.
     */
    public Pedido getPedidoByUsuario(short idUsuario);

    public List<Pedido> getCompletadosByUsuario(short idUsuario);

    /**
     * Añade un pedido a la base de datos.
     * @param pedido pedido que queremos añadir.
     */
    public void addPedido(Pedido pedido);

    /**
     * Actualiza un pedido en la base de datos.
     * @param pedido pedido que queremos actualizar.
     */
    public void updatePedido(Pedido pedido);

    /**
     * Completa un pedido.
     * @param pedido pedido que queremos completar.
     */
    public void completePedido(Pedido pedido);

    /**
     * Elimina un pedido de la base de datos.
     * @param pedido pedido que queremos eliminar.
     */
    public void deletePedido(Pedido pedido);

    /**
     * Elimina una línea de pedido de la base de datos.
     * @param idLineaPedido id de la línea de pedido que queremos eliminar.
     */
    public void deleteLineaPedido(short idLineaPedido);

    /**
     * Actualiza la cantidad de un producto en un pedido.
     * @param idProducto id del producto que queremos actualizar.
     * @param pedido pedido en el que queremos actualizar la cantidad.
     * @param cantidad cantidad que queremos añadir al producto.
     */
    public void actualizarCantidadProducto(short idProducto, Pedido pedido, int cantidad);

    /**
     * Cierra la conexión a la base de datos.
     */
    public void closeConnection();

}
