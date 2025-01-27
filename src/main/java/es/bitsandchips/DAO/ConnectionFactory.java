package es.bitsandchips.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    static DataSource dataSource = null;
    static Connection conexion = null;
    static final String DATASOURCE_NAME_MY = "java:comp/env/jdbc/bits";

    public static Connection getConnection(){
        try {
            /*
             * Para buscar y acceder a un recurso definido en el Servidor de Aplicaciones
             *   - Creamos el contexto de búsqueda mediante la clase InitialContext.
             *   - Realizamos la búsqueda del recurso haciendo el casting correspondiente con la sentencia lookup
             */
            Context contextoInicial = new InitialContext();
            dataSource = (DataSource) contextoInicial.lookup(DATASOURCE_NAME_MY);
            conexion = dataSource.getConnection();
        } catch (NamingException | SQLException ex) {
            /*
             * Existe un error al intentar crear el pool de conexiones. Escribimos el logger y se visualiza error500.jsp
             */
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);

        }
        return conexion;
    }

    public static void closeConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
