import java.io.*;
import java.net.*;


public class Test {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8080);
        System.out.println("Добро пожаловать в раздел тестирования!\n_______________________________________");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true) {
            connectTest(in,out);
            addTest(in,out);
            watchKeyTest(in,out);
            watchAnagramTest(in,out);
            startGameTest(in,out);
            deleteAnagramTest(in,out);
            deleteWordTest(in,out);
            clearTest(in,out);
            exitTest(in,out);
            break;
        }
        in.close();
        out.close();
        socket.close();
        return;

    }

    public static void connectTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Тестирование запущено\nЗапущена проверка функции Connect ...");
        out.write("Connect" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("Введите сообщение:"))
            System.out.println("Функция Connect прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции Connect!\n_______________________________________");
    }

    public static void addTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции add ...");
        out.write("add" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Пара слово-анаграмма успешно добавлена. Введите сообщение:"))
            System.out.println("Функция add прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции add!\n_______________________________________");
    }

    public static void watchKeyTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции watch key ...");
        out.write("watch key" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("[Стоп]. Введите сообщение:"))
            System.out.println("Функция watch key прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции watch key!\n_______________________________________");
    }

    public static void watchAnagramTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции watch anagram ...");
        out.write("watch anagram" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("[Пост]. Введите сообщение:"))
            System.out.println("Функция watch anagram прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции watch anagram!\n_______________________________________");
    }

    public static void startGameTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции start ...");
        out.write("start" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Правильно! Введите анаграмму к слову Стоп:"))
            System.out.println("Функция start прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции start!\n_______________________________________");
        out.write("stop game" + "\n");
        out.flush();
        str = in.readLine();
    }

    public static void deleteAnagramTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции delete anagram ...");
        out.write("delete anagram" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Анаграмма успешно удалена. Введите сообщение:"))
            System.out.println("Функция delete anagram прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции delete anagram!\n_______________________________________");
    }

    public static void deleteWordTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции delete word ...");
        out.write("delete word" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Слово успешно удалено. Введите сообщение:"))
            System.out.println("Функция delete word прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции delete word!\n_______________________________________");
    }

    public static void clearTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции clear ...");
        out.write("clear" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("Словарь уже пуст. Введите сообщение:"))
            System.out.println("Функция clear прошла проверку!\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
    }

    public static void exitTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции exit ...");
        out.write("exit" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("GoodBye!"))
            System.out.println("Функция exit прошла проверку!\nТестирование завершено\n_______________________________________");
        else
            System.out.println("Ошибка в работе функции exit!\nТестирование завершено\n_______________________________________");
    }
}
