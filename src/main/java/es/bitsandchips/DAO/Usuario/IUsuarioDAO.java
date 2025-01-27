package es.bitsandchips.DAO.Usuario;

import es.bitsandchips.beans.Usuario;

import java.sql.Connection;

public interface IUsuarioDAO {

    /**
     * Obtiene un usuario por su ID.
     * @param idUsuario el ID del usuario
     * @return un objeto Usuario
     */
    public Usuario getUsuarioById(short idUsuario, Connection connection);

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email el correo electrónico del usuario
     * @return un objeto Usuario
     */
    public Usuario getUsuarioByEmail(String email);

    /**
     * Obtiene un usuario por su correo electrónico y contraseña.
     *
     * @param email el correo electrónico del usuario
     * @param password la contraseña del usuario
     * @return un objeto Usuario
     */
    public Usuario getUsuarioByEmailAndPassword(String email, String password);

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuario el objeto Usuario a agregar
     */
    public void addUsuario(Usuario usuario);

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario el objeto Usuario a actualizar
     */
    public void updateUsuario(Usuario usuario);

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param usuario el objeto Usuario a actualizar
     */
    public void updatePassword(Usuario usuario);

    /**
     * Cierra la conexión a la base de datos.
     */
    public void closeConnection();

}
