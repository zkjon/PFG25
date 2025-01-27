package es.bitsandchips.DAO.Producto;

import es.bitsandchips.DAO.Categoria.CategoriaDAO;
import es.bitsandchips.DAO.ConnectionFactory;
import es.bitsandchips.beans.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO implements IProductoDAO{


    @Override
    public List<Producto> get8Productos() {
        CategoriaDAO categoriaDAO = null;
        Producto producto = null;
        List<Producto> productos = null;
        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT * FROM productos ORDER BY RAND() limit 8";

        try {
            categoriaDAO = new CategoriaDAO();
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            productos = new ArrayList<>();
            while (resultado.next()) {
                producto = new Producto();
                producto.setId(resultado.getShort(1));
                producto.setCategoria(categoriaDAO.getCategoriaById(resultado.getShort(2), connection));
                producto.setNombre(resultado.getString(3));
                producto.setDescripcion(resultado.getString(4));
                producto.setPrecio(resultado.getDouble(5));
                producto.setMarca(resultado.getString(6));
                producto.setImagen((resultado.getString(7)));
                productos.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }

        return productos;
    }

    @Override
    public Producto getProductoById(short id) {
        CategoriaDAO categoriaDAO = null;
        Producto producto = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "select * from productos where IDProducto = ?";

        try {
            categoriaDAO = new CategoriaDAO();
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.prepareStatement(sql);
            sentencia.setShort(1, id);

            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                producto = new Producto();
                producto.setId(resultado.getShort(1));
                producto.setCategoria(categoriaDAO.getCategoriaById(resultado.getShort(2), connection));
                producto.setNombre(resultado.getString(3));
                producto.setDescripcion(resultado.getString(4));
                producto.setPrecio(resultado.getShort(5));
                producto.setMarca(resultado.getString(6));
                producto.setImagen(resultado.getString(7));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }

        return producto;
    }

    @Override
    public List<Producto> getProductoByDescripcion(String descripcion) {
        CategoriaDAO categoriaDAO = null;
        Producto producto = null;
        List<Producto> productos = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "select * from productos where Descripcion like ?";

        try {
            categoriaDAO = new CategoriaDAO();
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, "%" + descripcion + "%");

            resultado = sentencia.executeQuery();
            productos = new ArrayList<>();
            while (resultado.next()) {
                producto = new Producto();
                producto.setId(resultado.getShort(1));
                producto.setCategoria(categoriaDAO.getCategoriaById(resultado.getShort(2), connection));
                producto.setNombre(resultado.getString(3));
                producto.setDescripcion(resultado.getString(4));
                producto.setPrecio(resultado.getShort(5));
                producto.setMarca(resultado.getString(6));
                producto.setImagen(resultado.getString(7));
                productos.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return productos;
    }

    @Override
    public List<String> getAllMarcas() {
        List<String> marcas = null;
        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT distinct(Marca) FROM productos ORDER BY productos.Marca";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            marcas = new ArrayList<>();
            while (resultado.next()) {
                marcas.add(resultado.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return marcas;
    }

    @Override
    public List<Producto> getProductosByFiltros(String precio, String[] categorias, String[] marcas) {
        CategoriaDAO categoriaDAO = null;
        Producto producto = null;
        List<Producto> productos = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM productos WHERE 1=1");
        // 1=1 es un truco que utilizo para que no tengamos que comprobar si hay filtros o no

        try {
            categoriaDAO = new CategoriaDAO();
            Connection connection = ConnectionFactory.getConnection();

            if (precio != null && !precio.isEmpty()) {
                sql.append(" AND Precio < ?");
            }

            if (categorias != null && categorias.length > 0) {
                sql.append(" AND IDCategoria IN (");
                for (int i = 0; i < categorias.length; i++) {
                    sql.append("?");
                    if (i < categorias.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(")");
            }

            if (marcas != null && marcas.length > 0) {
                sql.append(" AND Marca IN (");
                for (int i = 0; i < marcas.length; i++) {
                    sql.append("?");
                    if (i < marcas.length - 1) {
                        sql.append(",");
                    }
                }
                sql.append(")");
            }

            sentencia = connection.prepareStatement(sql.toString());

            int indice = 1;

            if (precio != null && !precio.isEmpty()) {
                sentencia.setDouble(indice++, Double.parseDouble(precio));
            }

            if (categorias != null && categorias.length > 0) {
                for (String categoria : categorias) {
                    sentencia.setString(indice++, categoria);
                }
            }

            if (marcas != null && marcas.length > 0) {
                for (String marca : marcas) {
                    sentencia.setString(indice++, marca);
                }
            }

            resultado = sentencia.executeQuery();
            productos = new ArrayList<>();
            while (resultado.next()) {
                producto = new Producto();
                producto.setId(resultado.getShort(1));
                producto.setCategoria(categoriaDAO.getCategoriaById(resultado.getShort(2), connection));
                producto.setNombre(resultado.getString(3));
                producto.setDescripcion(resultado.getString(4));
                producto.setPrecio(resultado.getShort(5));
                producto.setMarca(resultado.getString(6));
                producto.setImagen(resultado.getString(7));
                productos.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return productos;
    }

    @Override
    public short getPrecioById(short id) {
        short precio = 0;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "SELECT Precio FROM productos WHERE IdProducto = ?";
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            sentencia = connection.prepareStatement(sql);
            sentencia.setShort(1, id);

            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                precio = resultado.getShort(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return precio;
    }
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
