package Domain.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class My_stack<T>  implements My_I_stack<T>
{
    Stack<T> stack;

    public My_stack()
    {
        stack = new Stack<T>();
    }

    @Override
    public boolean empty()
    {
        return stack.empty();
    }

    @Override
    public T get_first_element()
    {
        return stack.peek();
    }

    @Override
    public T pop()
    {
        return stack.pop();
    }

    @Override
    public T push(T new_element)
    {
        return stack.push(new_element);
    }

    @Override
    public ArrayList<T> get_all_elements() {
        ArrayList all_elements = new ArrayList(Arrays.asList(stack.toArray()));
        Collections.reverse(all_elements);
        return all_elements;
    }

    public String toString()
    {
        String stack_string = "";
        for(Object element : stack.toArray())
        {
            stack_string = element.toString() + "\n" + stack_string;
        }
        return stack_string;
    }
}
