package Domain.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class My_list<T> implements My_I_list<T>
{
    ArrayList<T> list;

    public My_list()
    {
        list = new ArrayList<T>();
    }

    @Override
    public synchronized int size()
    {
        return list.size();
    }

    @Override
    public synchronized boolean is_empty()
    {
        return list.isEmpty();
    }

    @Override
    public synchronized boolean add(T new_element)
    {
        return list.add(new_element);
    }

    @Override
    public synchronized T get(int index)
    {
        return list.get(index);
    }

    @Override
    public synchronized void clear()
    {
        list.clear();
    }

    @Override
    public List<T> get_content_list() {
        return list;
    }

    public synchronized String toString()
    {
        String list_string = "";
        for(Object element : list.toArray())
        {
            list_string += element.toString()+"\n";
        }
        return list_string;
    }
}
