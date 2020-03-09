package Domain.Collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class My_dictionary<K, V> implements My_I_dictionary<K, V>
{
    HashMap<K, V> dictionary;

    public My_dictionary()
    {
        dictionary = new HashMap<K,V>();
    }


    @Override
    public synchronized V get_value(K key)
    {
        return dictionary.get(key);
    }

    @Override
    public synchronized V put(K key, V value)
    {
        return dictionary.put(key, value);
    }

    @Override
    public synchronized V remove(K key)
    {
        return dictionary.remove(key);
    }

    @Override
    public synchronized V update(K key, V new_value)
    {
        return dictionary.replace(key, new_value);
    }

    @Override
    public synchronized boolean contains_key(K key)
    {
        return dictionary.containsKey(key);
    }

    @Override
    public synchronized Set<K> get_keys_set() {
        return dictionary.keySet();
    }

    @Override
    public synchronized Set<Map.Entry<K, V>> get_pairs_set() {
        return dictionary.entrySet();
    }

    @Override
    public synchronized int size() {
        return dictionary.size();
    }

    @Override
    public synchronized Map<K, V> get_content()
    {
        return this.dictionary;
    }

    @Override
    public synchronized void set_content(Map<K, V> new_content)
    {
        this.dictionary = (HashMap<K,V>)new_content;
    }

    public synchronized String toString()
    {
        String dictionary_string = "";
        for(K element : dictionary.keySet())
        {
            dictionary_string += element.toString()+"-->"+this.get_value(element) + "\n";
        }
        return dictionary_string;
    }


}
