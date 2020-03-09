package Domain.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface My_I_heap<V>
{
    V get_value(Integer key);
    V put(V value);
    V remove(Integer key);
    V update(Integer key, V new_value);
    boolean contains_key(Integer key);
    Set<Integer> get_keys_set();
    Set<Map.Entry<Integer, V>> get_pairs_set();
    int size();
    int get_free_address();
    Map<Integer,V> get_content();
    void set_content(Map<Integer,V> new_content);
}
