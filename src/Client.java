import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);
        System.out.println("Hello!\n");
        Scanner sc = new Scanner(System.in);
        //BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String mess = "Connect";
        String str;
        while (true) {

            out.write(mess + "\n");
            out.flush();


            str = in.readLine();

            System.out.println(str);

            if(str.length() > 17)
                if (str.substring(str.length() - 18, str.length()).equals("Введите сообщение:")) System.out.println(getMessage());

            if (str.equals("GoodBye!")) break;

            mess = sc.nextLine();

        }
        in.close();
        out.close();
        socket.close();
        return;
    }

    public static String getMessage() {
        return "____________________________________________________\n" +
                "Возможный набор команд:\n" +
                "\"start\" - начать игру\n" +
                "\"watch key\" - посмотреть слова в словаре\n" +
                "\"watch anagram\" - посмотреть анаграммы слова\n" +
                "\"add\" - добавить свою анаграмму слова\n" +
                "\"delete word\" - удалить слово из словаря\n" +
                "\"delete anagram\" - удалить анаграмму слова\n" +
                "\"clear\" - очистить словарь\n" +
                "\"exit\" - выход\n" +
                "____________________________________________________\n" +
                "Ввод:";
    }
}