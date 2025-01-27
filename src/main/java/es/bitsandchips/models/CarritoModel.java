package es.bitsandchips.models;

import es.bitsandchips.DAO.Producto.IProductoDAO;
import es.bitsandchips.DAOFactory.DAOFactory;
import es.bitsandchips.beans.LineasPedidos;
import es.bitsandchips.beans.Pedido;

import java.util.ArrayList;
import java.util.List;

public class CarritoModel {
    /**
     *  Crea una nueva línea de pedido con un producto y una cantidad de 1
     *  @param idProducto Identificador del producto
     *  @return LineasPedidos
     */

    public static LineasPedidos newLineaPedidos(short idProducto) {
        LineasPedidos lineaPedido = new LineasPedidos();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductoDAO productoDAO = daof.getProductoDAO();

        lineaPedido.setProducto(productoDAO.getProductoById(idProducto));
        lineaPedido.setCantidad((short) 1);


        return lineaPedido;
    }

    /**
     *  Añade una línea de pedido a la lista de líneas de pedido
     *  @param listaLineasPedidos Lista de líneas de pedido
     *  @param idProducto Identificador del producto
     */
    public static void addLineaPedido(List<LineasPedidos> listaLineasPedidos, String idProducto) {
        boolean encontrado = false;
        for (LineasPedidos lineaPedido : listaLineasPedidos) {
            if (lineaPedido.getProducto().getId() == Short.parseShort(idProducto)) {
                lineaPedido.setCantidad((short) (lineaPedido.getCantidad() + 1));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            LineasPedidos lineaPedido = CarritoModel.newLineaPedidos(Short.parseShort(idProducto));
            listaLineasPedidos.add(lineaPedido);
        }
    }

    /**
     *  Crea un nuevo pedido con una línea de pedido
     *  @param lineasPedidos Línea de pedido
     *  @return Pedido
     */
    public static Pedido newPedido(LineasPedidos lineasPedidos){
       Pedido pedido = new Pedido();
       List<LineasPedidos> listaLineasPedidos = new ArrayList<>();
       pedido.setLineasPedidos(listaLineasPedidos);
       pedido.getLineasPedidos().add(lineasPedidos);
       pedido.setEstado(Pedido.Estado.c);
       pedido.setIva(0.21);
       pedido.setImporte(thisGetImporte(lineasPedidos.getProducto().getId()) * lineasPedidos.getCantidad());
       pedido.setFecha(new java.sql.Date(new java.util.Date().getTime()));
       return pedido;
    }

    /**
     *  Actualiza un pedido con una lista de líneas de pedido
     *  @param pedido Pedido
     *  @param listaLineasPedidos Lista de líneas de pedido
     */
    public static void updatePedido(Pedido pedido, List<LineasPedidos> listaLineasPedidos) {
        pedido.setLineasPedidos(listaLineasPedidos);
        double totalImporte = 0;

        for (LineasPedidos lineaPedido : listaLineasPedidos) {
            double precioProducto = lineaPedido.getProducto().getPrecio();
            int cantidad = lineaPedido.getCantidad();


            totalImporte += precioProducto * cantidad;
        }
        pedido.setImporte(totalImporte);
    }

    /**
     *  Elimina una línea de pedido a un pedido
     *  @param idProducto Identificador del producto
     *  @param pedido Pedido
     */
    public  static void deleteLineaPedido(short idProducto, Pedido pedido) {
        List<LineasPedidos> listaLineasPedidos = pedido.getLineasPedidos();
        for (LineasPedidos lineaPedido : listaLineasPedidos) {
            if (lineaPedido.getProducto().getId() == idProducto) {
                listaLineasPedidos.remove(lineaPedido);
                break;
            }
        }
        updatePedido(pedido, listaLineasPedidos);
    }

    /**
     * Incrementa la cantidad de un producto en un pedido
     * @param idProducto Identificador del producto
     * @param pedido Pedido
     */

    public static void sumarCantidad(short idProducto, Pedido pedido) {
        List<LineasPedidos> listaLineasPedidos = pedido.getLineasPedidos();
        for (LineasPedidos lineaPedido : listaLineasPedidos) {
            if (lineaPedido.getProducto().getId() == idProducto) {
                lineaPedido.setCantidad((short) (lineaPedido.getCantidad() + 1));
                break;
            }
        }
        updatePedido(pedido, listaLineasPedidos);
    }

    /**
     * Decrementa la cantidad de un producto en un pedido
     * @param idProducto Identificador del producto
     * @param pedido Pedido
     */
    public static void restarCantidad(short idProducto, Pedido pedido) {
        List<LineasPedidos> listaLineasPedidos = pedido.getLineasPedidos();
        for (LineasPedidos lineaPedido : listaLineasPedidos) {
            if (lineaPedido.getProducto().getId() == idProducto) {
                if (lineaPedido.getCantidad() > 1) {
                    lineaPedido.setCantidad((short) (lineaPedido.getCantidad() - 1));
                }
                break;
            }
        }
        updatePedido(pedido, listaLineasPedidos);
    }

    /**
     *  Obtiene el importe de un producto
     *  @param idProducto Identificador del producto
     *  @return double
     */
    private static double thisGetImporte(short idProducto) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductoDAO productoDAO = daof.getProductoDAO();
        return productoDAO.getPrecioById(idProducto);
    }
}
