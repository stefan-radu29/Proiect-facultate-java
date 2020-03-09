package Domain.Collections;

import java.util.ArrayList;

public interface My_I_stack<T>
{
    boolean empty();
    T get_first_element();
    T pop();
    T push(T new_element);
    ArrayList<T> get_all_elements();
}
