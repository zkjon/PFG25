package es.bitsandchips.DAO.Usuario;

import es.bitsandchips.DAO.ConnectionFactory;
import es.bitsandchips.beans.Usuario;
import es.bitsandchips.models.UtilidadesBC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements IUsuarioDAO{
    @Override
    public Usuario getUsuarioById(short idUsuario, Connection connection) {
        Usuario usuario = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "SELECT * FROM usuarios WHERE idusuario = ?";

        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setShort(1, idUsuario);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setEmail(resultado.getString(2));
                usuario.setContrasena(resultado.getString(3));
                usuario.setNombre(resultado.getString(4));
                usuario.setApellidos(resultado.getString(5));
                usuario.setNif(resultado.getString(6));
                usuario.setTelefono(resultado.getInt(7));
                usuario.setDireccion(resultado.getString(8));
                usuario.setCodigoPostal(resultado.getShort(9));
                usuario.setLocalidad(resultado.getString(10));
                usuario.setProvincia(resultado.getString(11));
                usuario.setUltimoAcceso(resultado.getTimestamp(12));
                usuario.setAvatar(resultado.getString(13));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuario;
    }


    @Override
    public Usuario getUsuarioByEmail(String email) {
        Usuario usuario = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, email);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setEmail(resultado.getString(2));
                usuario.setContrasena(resultado.getString(3));
                usuario.setNombre(resultado.getString(4));
                usuario.setApellidos(resultado.getString(5));
                usuario.setNif(resultado.getString(6));
                usuario.setTelefono(resultado.getInt(7));
                usuario.setDireccion(resultado.getString(8));
                usuario.setCodigoPostal(resultado.getShort(9));
                usuario.setLocalidad(resultado.getString(10));
                usuario.setProvincia(resultado.getString(11));
                usuario.setUltimoAcceso(new java.sql.Timestamp(new java.util.Date().getTime()));
                usuario.setAvatar(resultado.getString(13));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuario;
    }

    @Override
    public Usuario getUsuarioByEmailAndPassword(String email, String password) {
        Usuario usuario = null;
        ResultSet resultado = null;
        PreparedStatement sentencia = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

        try {
            Connection connection = ConnectionFactory.getConnection();
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, email);
            sentencia.setString(2, UtilidadesBC.md5(password));
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getShort(1));
                usuario.setEmail(resultado.getString(2));
                usuario.setContrasena(resultado.getString(3));
                usuario.setNombre(resultado.getString(4));
                usuario.setApellidos(resultado.getString(5));
                usuario.setNif(resultado.getString(6));
                usuario.setTelefono(resultado.getInt(7));
                usuario.setDireccion(resultado.getString(8));
                usuario.setCodigoPostal(resultado.getShort(9));
                usuario.setLocalidad(resultado.getString(10));
                usuario.setProvincia(resultado.getString(11));
                usuario.setUltimoAcceso(resultado.getTimestamp(12));
                usuario.setAvatar(resultado.getString(13));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuario;
    }

    @Override
    public void addUsuario(Usuario usuario) {
        PreparedStatement sentencia = null;
        String sql = "INSERT INTO usuarios (idusuario, email, password, nombre, apellidos, nif, telefono, direccion, codigopostal, localidad, provincia, ultimoacceso, avatar) VALUES ( not null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, usuario.getEmail());
            sentencia.setString(2, UtilidadesBC.md5(usuario.getContrasena()));
            sentencia.setString(3, usuario.getNombre());
            sentencia.setString(4, usuario.getApellidos());
            sentencia.setString(5, usuario.getNif());
            sentencia.setInt(6, usuario.getTelefono());
            sentencia.setString(7, usuario.getDireccion());
            sentencia.setShort(8, usuario.getCodigoPostal());
            sentencia.setString(9, usuario.getLocalidad());
            sentencia.setString(10, usuario.getProvincia());
            sentencia.setTimestamp(11, usuario.getUltimoAcceso());
            sentencia.setString(12, usuario.getAvatar());

            sentencia.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    this.closeConnection();
                } catch (SQLException e) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        PreparedStatement sentencia = null;
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, telefono = ?, direccion = ?, codigopostal = ?, localidad = ?, provincia = ? WHERE idusuario = ?";
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getApellidos());
            sentencia.setInt(3, usuario.getTelefono());
            sentencia.setString(4, usuario.getDireccion());
            sentencia.setShort(5, usuario.getCodigoPostal());
            sentencia.setString(6, usuario.getLocalidad());
            sentencia.setString(7, usuario.getProvincia());
            sentencia.setShort(8, usuario.getId());

            sentencia.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    this.closeConnection();
                } catch (SQLException e) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public void updatePassword(Usuario usuario) {
        PreparedStatement sentencia = null;
        String sql = "UPDATE usuarios SET password = ? WHERE idusuario = ?";
        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, usuario.getContrasena());
            sentencia.setShort(2, usuario.getId());

            sentencia.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    this.closeConnection();
                } catch (SQLException e) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }


    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }


}
