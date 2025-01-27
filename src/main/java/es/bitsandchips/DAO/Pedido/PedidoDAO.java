package es.bitsandchips.DAO.Pedido;

import es.bitsandchips.DAO.ConnectionFactory;
import es.bitsandchips.DAO.Usuario.UsuarioDAO;
import es.bitsandchips.beans.LineasPedidos;
import es.bitsandchips.beans.Pedido;
import es.bitsandchips.beans.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO implements IPedidoDAO {

    @Override
    public Pedido getPedidoByUsuario(short idUsuario) {
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        Pedido pedido = null;
        UsuarioDAO usuarioDAO = null;
        try{
            Connection connection = ConnectionFactory.getConnection();
            preparada = connection.prepareStatement("select * from pedidos\n" +
                    "join lineaspedidos on pedidos.idpedido = lineaspedidos.idpedido\n" +
                    "join productos on lineaspedidos.idproducto = productos.idproducto\n" +
                    "where pedidos.estado = 'c' and pedidos.idusuario = ?");

            preparada.setInt(1, idUsuario);


            resultado = preparada.executeQuery();
            List<LineasPedidos> lineasPedidos = new ArrayList<>();

            while (resultado.next()){
               if (pedido == null){
                   pedido = new Pedido();
                   usuarioDAO = new UsuarioDAO();
                   pedido.setId(resultado.getShort("idpedido"));
                   pedido.setFecha(resultado.getDate("fecha"));
                   pedido.setEstado(Pedido.Estado.c);
                   pedido.setUsuario(usuarioDAO.getUsuarioById(idUsuario, connection));
                   pedido.setIva(resultado.getDouble("iva"));
                   pedido.setImporte(resultado.getDouble("importe"));
               }

                LineasPedidos lineaPedido = new LineasPedidos();

                lineaPedido.setId(resultado.getShort("idlinea"));
                lineaPedido.setCantidad(resultado.getShort("cantidad"));

                Producto producto = new Producto();

                producto.setId(resultado.getShort("idproducto"));
                producto.setImagen(resultado.getString("imagen"));
                producto.setMarca(resultado.getString("marca"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getFloat("precio"));
                producto.setDescripcion(resultado.getString("descripcion"));

                lineaPedido.setProducto(producto);
                lineasPedidos.add(lineaPedido);

            }
            if (pedido != null){
                pedido.setLineasPedidos(lineasPedidos);
            }


        } catch (SQLException e){
            Logger.getLogger(PedidoDAO.class.getName()).log(null, "Error al obtener el pedido de un usuario", e);
        } finally {
            this.closeConnection();
        }
        return pedido;

    }

    @Override
    public List<Pedido> getCompletadosByUsuario(short idUsuario) {
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        List<Pedido> pedidos = new ArrayList<>();
        UsuarioDAO usuarioDAO = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparada = connection.prepareStatement("select * from pedidos\n" +
                    "join lineaspedidos on pedidos.idpedido = lineaspedidos.idpedido\n" +
                    "join productos on lineaspedidos.idproducto = productos.IdProducto\n" +
                    "where pedidos.estado = 'f' and pedidos.idusuario = ?");

            preparada.setInt(1, idUsuario);

            resultado = preparada.executeQuery();

            Pedido pedido = null;
            List<LineasPedidos> lineasPedidos = null;
            short lastPedidoId = -1;

            while (resultado.next()) {
                short currentPedidoId = resultado.getShort("idpedido");

                if (currentPedidoId != lastPedidoId) {
                    if (pedido != null) {
                        pedido.setLineasPedidos(lineasPedidos);
                        pedidos.add(pedido);
                    }

                    pedido = new Pedido();
                    usuarioDAO = new UsuarioDAO();
                    pedido.setId(currentPedidoId);
                    pedido.setFecha(resultado.getDate("fecha"));
                    pedido.setEstado(Pedido.Estado.f);
                    pedido.setUsuario(usuarioDAO.getUsuarioById(idUsuario, connection));
                    pedido.setIva(resultado.getDouble("iva"));
                    pedido.setImporte(resultado.getDouble("importe"));

                    lineasPedidos = new ArrayList<>();
                }

                LineasPedidos lineaPedido = new LineasPedidos();
                lineaPedido.setId(resultado.getShort("idlinea"));
                lineaPedido.setCantidad(resultado.getShort("cantidad"));

                Producto producto = new Producto();
                producto.setId(resultado.getShort("IdProducto"));
                producto.setImagen(resultado.getString("Imagen"));
                producto.setMarca(resultado.getString("Marca"));
                producto.setNombre(resultado.getString("Nombre"));
                producto.setPrecio(resultado.getFloat("Precio"));
                producto.setDescripcion(resultado.getString("Descripcion"));

                lineaPedido.setProducto(producto);

                if (lineasPedidos != null) {
                    lineasPedidos.add(lineaPedido);
                }

                lastPedidoId = currentPedidoId;
            }

            if (pedido != null) {
                pedido.setLineasPedidos(lineasPedidos);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al obtener los pedidos completados de un usuario", e);
        } finally {
            this.closeConnection();
        }

        return pedidos;
    }

    @Override
    public void addPedido(Pedido pedido) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("insert into pedidos (fecha, estado, idusuario, importe, iva) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparada.setDate(1, new Date(pedido.getFecha().getTime()));
            preparada.setString(2, String.valueOf(pedido.getEstado()));
            preparada.setShort(3, pedido.getUsuario().getId());
            preparada.setDouble(4, pedido.getImporte());
            preparada.setDouble(5, pedido.getIva());
            preparada.executeUpdate();

            ResultSet generatedKeys = preparada.getGeneratedKeys();
            if (generatedKeys.next()) {
                pedido.setId(generatedKeys.getShort(1));
            }

            for (LineasPedidos lineaPedido : pedido.getLineasPedidos()) {
                preparada = connection.prepareStatement("insert into lineaspedidos (idpedido, idproducto, cantidad) values (?, ?, ?)");
                preparada.setShort(1, pedido.getId());
                preparada.setShort(2, lineaPedido.getProducto().getId());
                preparada.setShort(3, lineaPedido.getCantidad());
                preparada.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            } finally {
                this.closeConnection();
            }
        }

    }

    @Override
    public void updatePedido(Pedido pedido) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("update pedidos set importe = ?, iva = ? where idpedido = ?");
            preparada.setDouble(1, pedido.getImporte());
            preparada.setDouble(2, pedido.getIva());
            preparada.setShort(3, pedido.getId());
            preparada.executeUpdate();

            preparada = connection.prepareStatement("delete from lineaspedidos where idpedido = ?");
            preparada.setShort(1, pedido.getId());
            preparada.executeUpdate();
            for (LineasPedidos lineaPedido : pedido.getLineasPedidos()) {
                preparada = connection.prepareStatement("insert into lineaspedidos (cantidad, idpedido, idproducto) values (?, ?, ?)");
                preparada.setShort(1, lineaPedido.getCantidad());
                preparada.setShort(2, pedido.getId());
                preparada.setShort(3, lineaPedido.getProducto().getId());
                preparada.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            } finally {
                this.closeConnection();
            }
        }
    }

    @Override
    public void completePedido(Pedido pedido) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("update pedidos set estado = 'f' where idpedido = ?");
            preparada.setShort(1, pedido.getId());
            preparada.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            } finally {
                this.closeConnection();
            }
        }
    }

    @Override
    public void deletePedido(Pedido pedido) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("delete from lineaspedidos where idpedido = ?");
            preparada.setShort(1, pedido.getId());
            preparada.executeUpdate();

            preparada = connection.prepareStatement("delete from pedidos where idpedido = ?");
            preparada.setShort(1, pedido.getId());
            preparada.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            } finally {
                this.closeConnection();
            }
        }
    }

    @Override
    public void deleteLineaPedido(short idLineaPedido) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("delete from lineaspedidos where idlinea = ?");
            preparada.setShort(1, idLineaPedido);
            preparada.executeUpdate();

            connection.commit();



        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            }
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al eliminar la línea de pedido", e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void actualizarCantidadProducto(short idProducto, Pedido pedido, int cantidad) {
        PreparedStatement preparada = null;
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparada = connection.prepareStatement("update lineaspedidos set cantidad = cantidad + ? where idpedido = ? and idproducto = ?");
            preparada.setInt(1, cantidad);
            preparada.setShort(2, pedido.getId());
            preparada.setShort(3, idProducto);
            preparada.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback", rollbackEx);
            }
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, "Error al actualizar la cantidad de un producto en un pedido", e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
