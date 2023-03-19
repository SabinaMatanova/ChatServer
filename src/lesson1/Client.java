package lesson1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static lesson1.ChatServer.clientList;

class Client implements Runnable {
    Socket socket;
    Scanner in;
    PrintStream out;

    public Client(Socket socket) {

        this.socket = socket;
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            Scanner in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to mountains!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                //заменить методом, который будет обращаться к методу в классе echoServer, который будет рассылать другим клиентам сообщение
                ChatServer.sendAll(input, this);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void recieve (String message){
        out.println(message);

    }


}
