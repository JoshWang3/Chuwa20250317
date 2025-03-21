import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Q3_diffHashMapHashtable {
    //They all store key value pairs. HashTable is thread safe and hashmap is not. Hashtable doesn't
    // allow null key or null value bu hashmap can. Due to the thread safety, hashtable performance
    // is lower than hashmap. hashtable is not a subclass of collections, and it out of dated.
    // we are not encouraged to use it.

    Map<Integer, Integer> map;
    Map<Integer, Integer> table;
    Q3_diffHashMapHashtable() {
        this.map = new HashMap<>();
        this.table = new Hashtable<>();
    }
}
