import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);              //серверсокет прослушивает порт 8080
        HashMap<String, ArrayList<String>> list = new HashMap<>();            //экземпляр, в котором будет храниться словарь.
                                                                                //представляетс собой струкруту ключ-значение
        ArrayList<String> listVal = new ArrayList<String>();                        //экземпляр динамического мамассива, в который будут записываться значения словаря
        String key;                                                             //переменная для доступа к словарю
        String mess;                                                                //переменная для записи сообщения от клиента
        String anagram;                                                             //переменная для анаграммы
        Cases cases = new Cases();                                                  //экземпляр класса, в котором хранятся все методы обработки словаря
        System.out.println(cases.readFile(list));                                   //вызов метода, в котором идет заполнение переменной list
                                                                                     // и вывод успешного\ошибочного чтения из файла
        System.out.println(list + "\n__________________________________________________");
        System.out.println("Server started");
        Socket clientSocket = serverSocket.accept();                                    //сервер ждет подключения
        //буферизированные переменные для общения клиента с сервером
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //чтение
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); //запись

        while(true) {                                                                   //беспонечный цикл работы сервера
            mess = in.readLine();                                                       //считываем сообщение от клиента
            System.out.println("__________________________________________________\n" + mess);

            switch (mess){                                                              //обработка сообщения и выбор действия
                case "Connect":{                                                        //начальное сообщение от клиента сигнализирующее,
                                                                                        // что клиент подключился и готов к общению
                    out.write("Введите сообщение:\n");
                    out.flush();                                                        //выталкиваем все из буфера
                    break;
                }
                case "start":{                                                          //сообщение о начале игры
                    boolean bool;                                                       //проверяет правильность ответа
                    boolean game = true;                                                //переменная остановки игры
                    mess = "";
                    while (game)                                                        //пока клиент не ввел stop game
                    {
                        bool = false;
                        key = cases.randomWord(list);                                   //в ключ записывается случайное значение из словаря
                        out.write(mess + "Введите анаграмму к слову " + key + ":\n");
                        out.flush();
                        while(!bool) {                                                   //пока ответ неправильный
                            anagram = in.readLine();                                      //считываем ответ от клиента на выданное слово
                            if(anagram.equals("stop game")) {                              //если сообщение stop game, то идет остановка игры
                                game = false;
                                break;
                            }
                            else bool = cases.compare(list, key, anagram);                  //сравнение ответа клиента с верным значением
                            if(!bool){
                                out.write(cases.getMessage(bool));                          //возвращает сообщение в зависимости от корректности ответа клиента
                                out.flush();
                            }
                            else break;
                        }
                        mess = cases.getMessage(bool);                                      //возвращает сообщение в зависимости от корректности ответа клиента
                    }
                    out.write("Введите сообщение:\n");
                    out.flush();
                    break;
                }
                case "watch key":{                                                          //выводит полный список слов в словаре
                    out.write(cases.watchKey(list));
                    out.flush();
                    break;
                }
                case "watch anagram":{                                                      //выводит анаграммы к заданному слову
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();
                    out.write(cases.watchAnagaram(list,key));
                    out.flush();
                    break;
                }
                case "add":{                                                                 //добавление пары слово-анаграмма
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();

                    out.write("Введите анаграмму:\n");
                    out.flush();
                    anagram = in.readLine();
                    out.write(cases.add(list,key,anagram));                                 //добавляет слово и возвращает сообщение о результате операции
                    out.flush();
                    break;
                }
                case "delete word":{       //удаляет слово
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();
                    out.write(cases.delWord(list,key));                                     //удаляет слово и возвращает сообщение о результате операции
                    out.flush();
                    break;
                }
                case "delete anagram":{                                                     //удаляет анаграмму к заданному слову
                    out.write("Введите слово:\n");
                    out.flush();
                    key = in.readLine();

                    out.write("Введите анаграмму:\n");
                    out.flush();
                    anagram = in.readLine();
                    out.write(cases.delAnagram(list,key,anagram));                          //удаляет анаграмму к заданному слову и
                                                                                            // возвращает сообщение о результате операции
                    out.flush();
                    break;
                }
                case "clear":{        //полностью чистит словарь
                    out.write(cases.clear(list));
                    out.flush();
                    break;
                }
                case "exit":{         //завершение работы сервера
                    out.write("GoodBye!\n");                                           //прощается с клиентом
                    out.flush();
                    clientSocket.close();                                                   //закрытие клиентского сокета
                    in.close();                                                             //закрытие потока чтения
                    out.close();                                                            //закрытие потока записи
                    serverSocket.close();                                                   //закрытие сокета сервера
                    System.out.println(cases.writeFile(list));                              //запись словаря в документ
                    System.out.println("Server stop!");
                    return;
                }
                default: {                                                                   //если сообщение не подходит
                                                                                            // ни под один из вариантов логики case
                    out.write("Ваше сообщение:\"" + mess + "\". Введите сообщение:\n");
                    out.flush();
                }
            }
        }
    }
}