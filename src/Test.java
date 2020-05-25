import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Test {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8080);                                                           // настройка сокет-связи
        System.out.println("Добро пожаловать в раздел тестирования!\n_______________________________________");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));                            // буфер для чтения данных
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));                         // буфер для записи данных
        HashMap<String, ArrayList<String>> list = new HashMap<>();                                                         // экземпляр, служащий для хранения данных словаря
        int counter = 0;                                                                                                   // счетчик, для количества успешно проведенных тестов
        readFile(list);                                                                                                    // считывание информации из словаря, для ее сохранения во время проведения тестирования
        while (true) {                                                                                                     // Начало работы с сервером
            if(connectTest(in,out))counter++;                                                                              // Тест функции Connect
            else break;                                                                                                    // В случае ошибки в этой функции заканчиваем сеанс работы с сервером
            if(clearTest(in,out)) counter++;                                                                               // Проверка функции clear
            if(addTest(in,out)) counter++;                                                                                 // Проверка функции add
            if(watchKeyTest(in,out)) counter++;                                                                            // Проверка функции watch key
            if(watchAnagramTest(in,out)) counter++;                                                                        // Проверка функции watch anagram
            if(startGameTest(in,out)) counter++;                                                                           // Проверка функции start game
            if(deleteAnagramTest(in,out)) counter++;                                                                       // Проверка функции delete anagram
            if(deleteWordTest(in,out)) counter++;                                                                          // Проверка функции delete word
            if(exitTest(in,out)) counter++;;                                                                               // Проверка функции exit
            System.out.println("Тестирование прошло " + counter + " из 9 функций");
            break;
        }
        writeFile(list);                                                                                                   // Запись данных в словарь после проведения тестирования
        in.close();                                                                                                        // закрытие буферов
        out.close();
        socket.close();                                                                                                    // окончание сокет-связи
        return;

    }

    public static boolean connectTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Тестирование запущено\nПодключение к серверу ...");
        out.write("Connect" + "\n");                                                                                    // передача команды на сервер
        out.flush();                                                                                                        // очистка буфера
        String str = in.readLine();                                                                                         // получаем ответ от сервера
        if(str.equals("Введите сообщение:")){                                                                               // обработка ответа от сервера
            System.out.println("Подключение выполнено успешно!\n_______________________________________");
            return true;                                                                                                    // тестирование прошло
        }
        else{
            System.out.println("Не удалось подключиться к серверу!\n_______________________________________");
            return false;                                                                                                   // тестирование не прошло
        }

    }

    public static boolean addTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции add ...\nПервый этап...");
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
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции add!\n_______________________________________");
            return false;
        }
        System.out.println("Второй этап...");
        out.write("add" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Такая анаграмма уже есть. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции add!\n_______________________________________");
            return false;
        }
        System.out.println("Третий этап...");
        out.write("add" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Остп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Анаграмма успешно добавлена. Введите сообщение:")){
            System.out.println("Успешно!\nФункция add прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции add!\n_______________________________________");
            return false;
        }
    }

    public static boolean watchKeyTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции watch key ...");
        out.write("watch key" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("[Стоп]. Введите сообщение:")){
            System.out.println("Функция watch key прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции watch key!\n_______________________________________");
            return false;
        }
    }

    public static boolean watchAnagramTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции watch anagram ...\nПервый этап...");
        out.write("watch anagram" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("[Пост, Остп]. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции watch anagram!\n_______________________________________");
            return false;
        }
        System.out.println("Второй этап...");
        out.write("watch anagram" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Ошибка" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Слово не найдено. Введите Сообщение:")){
            System.out.println("Успешно!\nФункция watch anagram прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции watch anagram!\n_______________________________________");
            return false;
        }
    }

    public static boolean startGameTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции start ...\nПервый этап...");
        out.write("start" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Ошибка" + "\n");
        out.flush();
        str = in.readLine();
        System.out.println(str);
        if(str.equals("Попробуйте снова: |Для остановки игры введите \"stop game\"|"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции start!\n_______________________________________");
            out.write("stop game" + "\n");
            out.flush();
            str = in.readLine();
            return false;
        }
        System.out.println("Второй этап");
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Правильно! Введите анаграмму к слову Стоп: |Для остановки игры введите \"stop game\"|")){
            System.out.println("Функция start прошла проверку!\n_______________________________________");
            out.write("stop game" + "\n");
            out.flush();
            str = in.readLine();
            return true;
        }

        else{
            System.out.println("Ошибка в работе функции start!\n_______________________________________");
            out.write("stop game" + "\n");
            out.flush();
            str = in.readLine();
            return false;
        }
    }

    public static boolean deleteAnagramTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции delete anagram ...\nПервый этап...");
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
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции delete anagram!\n_______________________________________");
            return false;
        }
        System.out.println("Второй этап...");
        out.write("delete anagram" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Анаграмма не найдена. Введите сообщение:")){
            System.out.println("Успешно!\nФункция delete anagram прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции delete anagram!\n_______________________________________");
            return false;
        }
    }

    public static boolean deleteWordTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции delete word ...\nПервый этап...");
        out.write("delete word" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Ошибка" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Слово не найдено. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции delete word!\n_______________________________________");
            return false;
        }
        System.out.println("Второй этап...");
        out.write("delete word" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Слово успешно удалено. Введите сообщение:")){
            System.out.println("Успешно!\nФункция delete word прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции delete word!\n_______________________________________");
            return false;
        }
    }

    public static boolean clearTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции clear ...\nПервый этап...");
        out.write("add" + "\n");
        out.flush();
        String str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Пост" + "\n");
        out.flush();
        str = in.readLine();
        out.write("clear" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Словарь успешно очищен. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
            return false;
        }
        System.out.println("Второй этап...");
        out.write("clear" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Словарь уже пуст. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
            return false;
        }
        System.out.println("Третий этап...");
        out.write("delete word" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Словарь пуст. Введите сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
            return false;
        }
        System.out.println("Четвертый этап...");
        out.write("watch anagram" + "\n");
        out.flush();
        str = in.readLine();
        out.write("Стоп" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Словарь пуст. Введите Сообщение:"))
            System.out.println("Успешно!");
        else{
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
            return false;
        }
        System.out.println("Пятый этап...");
        out.write("watch key" + "\n");
        out.flush();
        str = in.readLine();
        if(str.equals("Словарь пуст. Введите Сообщение:")){
            System.out.println("Успешно!\nФункция clear прошла проверку!\n_______________________________________");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции clear!\n_______________________________________");
            return false;
        }
    }

    public static boolean exitTest(BufferedReader in, BufferedWriter out) throws IOException {
        System.out.println("Запущена проверка функции exit ...");
        out.write("exit" + "\n");
        out.flush();
        String str = in.readLine();
        if(str.equals("GoodBye!")){
            System.out.println("Функция exit прошла проверку!\n_______________________________________\nТестирование завершено");
            return true;
        }
        else{
            System.out.println("Ошибка в работе функции exit!\n_______________________________________\nТестирование завершено");
            return false;
        }
    }

    public static String writeFile(HashMap<String, ArrayList<String>> list){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt", false));
            for (Map.Entry entry: list.entrySet()) {
                writer.write(entry.getKey().toString() + " - " + entry.getValue().toString().substring(1, entry.getValue().toString().length() - 1) + "\n");
                writer.flush();
            }
            return "Запись завершена.";
        } catch (IOException e) {
            e.getStackTrace();
            return "Ошибка записи.";
        }
    }

    public static String readFile(HashMap<String, ArrayList<String>> list){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] arrSplit = line.split(" - ");
                ArrayList<String> listValue = new ArrayList<String>(Arrays.asList(arrSplit[1].split(",")));
                for(int i = 1 ; i < arrSplit.length ; ++i)
                    list.put(arrSplit[0], listValue);
                line = reader.readLine();
            }
            return "Чтение завершено.";
        } catch (IOException e) {
            e.getStackTrace();
            return "Ошибка чтения.";
        }
    }
}