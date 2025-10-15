package br.com.fiap;

import java.io.IOException;
import java.math.BigInteger;

public class Server {

    public static void main(String[] args) {
        try {
            // Valores RSA fornecidos
            BigInteger p = new BigInteger("271");
            BigInteger q = new BigInteger("397");
            BigInteger e = new BigInteger("65537");

            // Criar a chave RSA
            RSA rsa = new RSA(p, q, e);

            // Criar a conexão TCP com o cliente
            Connection connection = Connection.createServerConnection(12345);
            System.out.println("Servidor conectado.");

            // Receber a mensagem criptografada do cliente
            String encryptedMessage = connection.receiveMessage();
            System.out.println("Mensagem criptografada recebida: " + encryptedMessage);

            // Descriptografar a mensagem
            String decryptedMessage = rsa.decrypt(encryptedMessage);
            System.out.println("Mensagem descriptografada: " + decryptedMessage);

            // Enviar a mensagem de volta ao cliente
            connection.sendMessage("Mensagem recebida e descriptografada com sucesso!");

            // Fechar a conexão
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
