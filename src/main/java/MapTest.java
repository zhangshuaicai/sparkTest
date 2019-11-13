import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<String, Integer>(){};
        map1.put("a",1);
        map1.put("b",1);
        map1.put("c",1);
        Map<String, Integer> map2 = new HashMap<String, Integer>(){};
        map2.put("a",1);
        map2.put("d",1);
        map2.put("c",1);

//        map1.merge(map2);

        for (String key :map1.keySet()){
            if (map2.containsKey(key)){
                map2.put(key,map1.get(key)+map2.get(key));
            }else {
                map2.put(key,map1.get(key));
            }
        }
        for (String key:map2.keySet()){
            System.out.println(key+":"+map2.get(key));
        }
    }
}
