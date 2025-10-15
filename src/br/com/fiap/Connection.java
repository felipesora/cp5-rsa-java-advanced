package br.com.fiap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    // Método para configurar o servidor
    public static Connection createServerConnection(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        return new Connection(socket);
    }

    // Método para configurar o cliente
    public static Connection createClientConnection(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        return new Connection(socket);
    }

    private Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    // Enviar mensagem
    public void sendMessage(String message) throws IOException {
        output.writeUTF(message);
    }

    // Receber mensagem
    public String receiveMessage() throws IOException {
        return input.readUTF();
    }

    // Fechar a conexão
    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}

