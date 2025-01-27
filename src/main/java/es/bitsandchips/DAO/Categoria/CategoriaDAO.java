package es.bitsandchips.DAO.Categoria;

import es.bitsandchips.DAO.ConnectionFactory;
import es.bitsandchips.beans.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO implements ICategoriaDAO{
    @Override
    public List<Categoria> getAllCategoria() {
        Categoria categoria = null;
        List<Categoria> categorias = null;
        ResultSet resultado = null;
        Statement sentencia = null;
        String sql = "SELECT * FROM categorias ORDER BY Nombre";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.createStatement();

            resultado = sentencia.executeQuery(sql);
            categorias = new ArrayList<>();
            while (resultado.next()) {
                categoria = new Categoria();
                categoria.setId(resultado.getShort(1));
                categoria.setNombre(resultado.getString(2));
                categoria.setImagen(resultado.getString(3));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }

        return categorias;
    }

    @Override
    public Categoria getCategoriaById(short id, Connection connection) {
        Categoria categoria = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "SELECT * FROM categorias WHERE IdCategoria = ?";

        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setShort(1, id);

            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                categoria = new Categoria();
                categoria.setId(resultado.getShort(1));
                categoria.setNombre(resultado.getString(2));
                categoria.setImagen(resultado.getString(3));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return categoria;
    }


    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
