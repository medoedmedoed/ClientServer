import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started");
        Socket clientSocket = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        Cases cases = new Cases();

        ArrayList<String> listVal = new ArrayList<String>();
        HashMap<String, ArrayList<String>> list = new HashMap<>();
        String key;
        String mess;
        String anagram;

        while(true) {

            System.out.println(list + "\n__________________________________________________");
            mess = in.readLine();
            System.out.println(mess);

            switch (mess){
                case "Connect":{
                    out.write("Введите сообщение:\n");
                    out.flush();
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
                    System.out.println("Server stop!");
                    return;
                }
                default: {
                    out.write("Ваше сообщение:" + mess + "\n");
                    out.flush();
                }
            }
        }
    }
}
