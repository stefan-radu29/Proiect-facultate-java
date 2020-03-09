package Domain.Collections;

import java.util.List;

public interface My_I_list<T>
{
    int size();
    boolean is_empty();
    boolean add(T new_element);
    T get(int index);
    void clear();
    List<T> get_content_list();
}
