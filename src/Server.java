import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080); //серверсокет прослушивает порт 8080
        HashMap<String, ArrayList<String>> list = new HashMap<>();  //экземпляр, в котором будет храниться словарь.
                                                                    //представляетс собой струкруту ключ-значение
        ArrayList<String> listVal = new ArrayList<String>();    //экземпляр динамического мамассива, в который будут записываться значения словаря
        String key; //переменная для доступа к словарю
        String mess;    //переменная для записи сообщения от клиента
        String anagram;     //переменная для анаграммы
        Cases cases = new Cases();     //экземпляр класса, в котором хранятся все методы обработки словаря
        System.out.println(cases.readFile(list));   //вызов метода, в котором идет заполнение переменной list
                                                    // и вывод успешного\ошибочного чтения из файла
        System.out.println(list + "\n__________________________________________________");
        System.out.println("Server started");
        Socket clientSocket = serverSocket.accept();    //сервер ждет подключения
        //буферизированные переменные для общения клиента с сервером
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //чтение
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); //запись

        while(true) {         //беспонечный цикл работы сервера
            mess = in.readLine();   //считываем сообщение от клиента
            System.out.println("__________________________________________________\n" + mess);

            switch (mess){      //обработка сообщения и выбор действия
                case "Connect":{    //начальное сообщение от клиента сигнализирующее, что клиент подключился и готов к общению
                    out.write("Введите сообщение:\n");
                    out.flush();    //выталкиваем все из буфера
                    break;
                }
                case "start":{
                    boolean bool;
                    boolean game = true;
                    mess = "";
                    while (game)
                    {
                        bool = false;
                        key = cases.randomWord(list);
                        out.write(mess + "Введите анаграмму к слову " + key + ":\n");
                        out.flush();
                        while(!bool) {
                            anagram = in.readLine();
                            if(anagram.equals("stop game")) {
                                game = false;
                                break;
                            }
                            else bool = cases.compare(list, key, anagram);
                            if(!bool){
                                out.write(cases.getMessage(bool));
                                out.flush();
                            }
                            else break;
                        }
                        mess = cases.getMessage(bool);
                    }
                    out.write("Введите сообщение:\n");
                    out.flush();
                    break;
                }
                case "watch key":{
                    out.write(cases.watchKey(list));
                    System.out.println(cases.watchKey(list));
                    out.flush();
                    break;
                }
                case "watch anagram":{
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();
                    out.write(cases.watchAnagaram(list,key));
                    out.flush();
                    break;
                }
                case "add":{
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();

                    out.write("Введите анаграмму:\n");
                    out.flush();
                    anagram = in.readLine();
                    out.write(cases.add(list,key,anagram));
                    out.flush();
                    break;
                }
                case "delete word":{
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();
                    out.write(cases.delWord(list,key));
                    out.flush();
                    break;
                }
                case "delete anagram":{
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();

                    out.write("Введите анаграмму:\n");
                    out.flush();
                    anagram = in.readLine();
                    out.write(cases.delAnagram(list,key,anagram));
                    out.flush();
                    break;
                }
                case "clear":{
                    out.write(cases.clear(list));
                    out.flush();
                    break;
                }
                case "exit":{
                    out.write("GoodBye!\n");
                    out.flush();
                    clientSocket.close();
                    in.close();
                    out.close();
                    serverSocket.close();
                    System.out.println(cases.writeFile(list));
                    System.out.println("Server stop!");
                    return;
                }
                default: {
                    out.write("Ваше сообщение:\"" + mess + "\". Введите сообщение:\n");
                    out.flush();
                }
            }
        }
    }
}