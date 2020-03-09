package Domain.Collections;

import java.util.Map;
import java.util.Set;

public interface My_I_dictionary<K, V>
{
    V get_value(K key);
    V put(K key, V value);
    V remove(K key);
    V update(K key, V new_value);
    boolean contains_key(K key);
    Set<K> get_keys_set();
    Set<Map.Entry<K, V>> get_pairs_set();
    int size();
    Map<K,V> get_content();
    void set_content(Map<K,V> new_content);
}
