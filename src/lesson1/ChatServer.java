package lesson1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
    static List<Client> clientList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // создаем серверный сокет на порту 1234
        ServerSocket server = new ServerSocket(1234);
        while (true) {
            System.out.println("Waiting...");
            // ждем клиента из сети
            Socket socket = server.accept();
            System.out.println("Client connected!");
            // запускаем поток
            Client client = new Client(socket);
            clientList.add(client);
            new Thread(client).start();
        }
    }
    public static void sendAll(String message, Client messageSender) {
        for (Client client : clientList) {
            if (!client.equals(messageSender)) {
                client.recieve(message);
            }


        }
    }
}
