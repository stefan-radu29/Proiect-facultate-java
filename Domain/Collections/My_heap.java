package Domain.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class My_heap<V> implements My_I_heap<V>
{
    static int new_free_location = 1;
    HashMap<Integer, V> heap_dictionary;


    public My_heap()
    {
        heap_dictionary = new HashMap<Integer,V>();
    }

    public synchronized String toString()
    {
        String dictionary_string = "";
        for(Integer element : heap_dictionary.keySet())
        {
            dictionary_string += element.toString()+"-->"+this.get_value(element) + "\n";
        }
        return dictionary_string;
    }

    @Override
    public synchronized V get_value(Integer key)
    {
        return heap_dictionary.get(key);
    }

    @Override
    public synchronized V put(V value)
    {
        My_heap.new_free_location++;
        return heap_dictionary.put(My_heap.new_free_location - 1, value);
    }

    @Override
    public synchronized V remove(Integer key)
    {
        return heap_dictionary.remove(key);
    }

    @Override
    public synchronized V update(Integer key, V new_value)
    {
        return heap_dictionary.replace(key, new_value);
    }

    @Override
    public synchronized boolean contains_key(Integer key)
    {
        return heap_dictionary.containsKey(key);
    }

    @Override
    public synchronized Set<Integer> get_keys_set()
    {
        return heap_dictionary.keySet();
    }

    @Override
    public synchronized Set<Map.Entry<Integer, V>> get_pairs_set()
    {
        return heap_dictionary.entrySet();
    }

    @Override
    public synchronized int size() {
        return heap_dictionary.size();
    }

    @Override
    public synchronized int get_free_address() {
        return new_free_location;
    }

    @Override
    public synchronized Map<Integer, V> get_content()
    {
        return this.heap_dictionary;
    }

    @Override
    public synchronized void set_content(Map<Integer, V> new_content)
    {
        this.heap_dictionary = (HashMap<Integer, V>)new_content;
    }
}
