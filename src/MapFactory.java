import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory<E,F> {

    public Map<E,F> getMap(String entry){

        if(entry.equals("HashMap"))
            return new HashMap<>();
        else if (entry.equals("TreeMap"))
            return new TreeMap<>();
        else if (entry.equals("LinkedHashMap"))
            return new LinkedHashMap<>();
        else
            return new HashMap<>();
    }
}
