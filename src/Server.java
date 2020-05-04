import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started");
        Socket clientSocket = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        while(true) {
            String mess = in.readLine();
            System.out.println(mess);

            if(mess.equals("exit")) {
                out.write("GoodBye");
                out.flush();
                clientSocket.close();
                break;
            }

            out.write("Your message: " + mess + "\n");
            out.flush();

        }
        in.close();
        out.close();
        serverSocket.close();
        System.out.println("Server stop!");
    }
}
