import java.lang.reflect.Array;
import java.util.*;

public class Cases {
    public static String watchKey(HashMap<String, ArrayList<String>> list) {
        if(!list.isEmpty()){
            Set keySet = list.keySet();
            return "" + keySet + ". Введите сообщение:\n";
        }
        else return "Словарь пуст. Введите Сообщение:\n";
    }

    public static String watchAnagaram(HashMap<String, ArrayList<String>> list, String key){
        String str = new String();
        if(!list.isEmpty()){
            if(list.containsKey(key)){
                ArrayList<String> listValue = new ArrayList<String>(list.get(key));
                return listValue + ". Введите сообщение:\n";
            }
            else return "Слово не найдено. Введите Сообщение:\n";
        }
        else return "Словарь пуст. Введите Сообщение:\n";
    }

    public static String add(HashMap<String, ArrayList<String>> list, String key, String anagram){
        if(list.containsKey(key)) {
            ArrayList<String> listValue = new ArrayList<String>(list.get(key));
            if(!listValue.contains(anagram)){                                               // проверка, что введенная анаграмма отсутствует
                listValue.add(anagram);
                list.put(key, listValue);
                return "Анаграмма успешно добавлена. Введите сообщение:\n";
            }
            else return "Такая анаграмма уже есть. Введите сообщение:\n";
        }
        else {
            ArrayList<String> listValue = new ArrayList<String>();
            listValue.add(anagram);
            list.put(key, listValue);
            return "Пара слово-анаграмма успешно добавлена. Введите сообщение:\n";
        }
    }


    public static String delWord(HashMap<String, ArrayList<String>> list, String key){
        if(!list.isEmpty())
        {
            if(list.containsKey(key)) {
                list.remove(key);
                if(!list.containsKey(key))
                    return "Слово успешно удалено. Введите сообщение:\n";
                else return "Слово удалить не удалось. Введите сообщение:\n";
            }
            else return "Слово не найдено. Введите сообщение:\n";
        }
        else return "Словарь пуст. Введите сообщение:\n";
    }


    public static String delAnagram(HashMap<String,ArrayList<String>> list, String key, String anagram){
        if(list.containsKey(key)){
            ArrayList<String> listValue = new ArrayList<String>(list.get(key));
            if(listValue.remove(anagram)){
                list.put(key,listValue);
                return "Анаграмма успешно удалена. Введите сообщение:\n";
            }
            else return "Анаграмма не найдена. Введите сообщение:\n";
        }
        else return "Слово не найдено. Введите сообщение:\n";
    }


    public static String clear(HashMap<String,ArrayList<String>> list){
        if(!list.isEmpty()) {
            list.clear();
            if(list.isEmpty())
                return "Словарь успешно очищен. Введите сообщение:\n";
            else return "Ошибка. Введите сообщение:\n";
        }
        else return "Словарь уже пуст. Введите сообщение:\n";
    }

    public static String randomWord(HashMap<String, ArrayList<String>> list){
        Random random = new Random();
        List<String> keys = new ArrayList<>(list.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    public static boolean compare(HashMap<String, ArrayList<String>> list, String key, String anagram){
        ArrayList<String> listValue = new ArrayList<String>(list.get(key));
        if(listValue.contains(anagram))
            return true;
        else return false;
    }

    public static String getMessage(boolean bool){
        if(bool)
            return "Правильно! ";
        else return "Попробуйте снова: \n";
    }
}