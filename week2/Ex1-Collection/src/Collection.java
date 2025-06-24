import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collection {

    public static void printListName() {
        List<String> listName = new ArrayList<>();
        listName.add("Tran Van A");
        listName.add("Tran Van B");
        listName.add("Tran Van C");

        Collections.sort(listName);
        Collections.sort(listName,Collections.reverseOrder());
        listName.stream()
                .sorted(Comparator.reverseOrder());


        for (String name : listName) {
            System.out.println(name);
        }


    }

    public static void printText() {
        String text = "Java là ngôn ngữ lập trình. Java rất phổ biến. Java mạnh mẽ";

        text = text.replaceAll("[^a-zA-ZÀ-ỹ\\s]", "").toLowerCase();
        String[] words = text.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
//        for(Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
        for(String key: map.keySet()) {
            System.out.println(key+" : "+map.get(key));
        }
    }

    public static void main(String[] args) {
        printListName();
        printText();
    }
}