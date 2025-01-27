package es.bitsandchips.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilidadesBC {

    /**
     * Genera el hash MD5 de la cadena de entrada proporcionada.
     *
     * <p>Este método calcula el hash MD5 de la cadena de entrada y lo devuelve
     * como una cadena hexadecimal de 32 caracteres. Si el hash calculado tiene
     * menos de 32 caracteres, se añaden ceros a la izquierda para completarlo.
     *
     * @param input la cadena de entrada a la que se le calculará el hash
     * @return el hash MD5 de la cadena de entrada como una cadena hexadecimal de 32 caracteres
     * @throws RuntimeException si el algoritmo MD5 no está disponible en el entorno
     */
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
