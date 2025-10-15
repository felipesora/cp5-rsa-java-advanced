package br.com.fiap;

import java.io.IOException;
import java.math.BigInteger;

public class Client {

    public static void main(String[] args) {
        try {
            // Valores RSA fornecidos
            BigInteger p = new BigInteger("271");
            BigInteger q = new BigInteger("397");
            BigInteger e = new BigInteger("65537");

            // Criar a chave RSA
            RSA rsa = new RSA(p, q, e);

            // Criar a conexão TCP com o servidor
            Connection connection = Connection.createClientConnection("localhost", 12345);
            System.out.println("Cliente conectado ao servidor.");

            // Mensagem a ser criptografada
            String message = "Essa e nossa mensagem utilizada como teste para o cp 5";
            System.out.println("Mensagem original: " + message);

            // Criptografar a mensagem
            String encryptedMessage = rsa.encrypt(message);
            System.out.println("Mensagem criptografada: " + encryptedMessage);

            // Enviar a mensagem criptografada ao servidor
            connection.sendMessage(encryptedMessage);

            // Receber resposta do servidor
            String response = connection.receiveMessage();
            System.out.println("Resposta do servidor: " + response);

            // Fechar a conexão
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
