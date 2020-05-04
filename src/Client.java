import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8080);
        System.out.println("Hello!\n_____________________________________");
        Scanner sc = new Scanner(System.in);
        //BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true) {

            System.out.println("Enter message:");
            String mess = sc.nextLine();

            out.write(mess + "\n");
            out.flush();

            String str = in.readLine();
            //if(mess.equals("exit")) break;
            if(str.equals("GoodBye")) break;
            System.out.println(str);
        }
        in.close();
        out.close();
        socket.close();
        System.out.println("Goodbye!");
    }
}
