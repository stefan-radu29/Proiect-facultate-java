package Domain;

import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_heap;
import Domain.Collections.My_I_list;
import Domain.Collections.My_I_stack;
import Domain.Statements.I_statement;
import Domain.Values.String_value;
import Domain.Values.Value;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Program_state
{
    My_I_stack<I_statement> execution_stack;
    My_I_dictionary<String, Value> symbols_table;
    My_I_list<Value> output;
    My_I_dictionary<String_value, BufferedReader> file_table;
    My_I_heap<Value> heap;
    static AtomicInteger id = new AtomicInteger(1);
    int current_state_id;

    private static int assign_id()
    {
        return Program_state.id.getAndIncrement();
    }

    public int get_current_state_id()
    {
        return current_state_id;
    }

    public My_I_stack<I_statement> get_execution_stack() {
        return execution_stack;
    }

    public void set_execution_stack(My_I_stack<I_statement> execution_stack) {
        this.execution_stack = execution_stack;
    }

    public My_I_dictionary<String, Value> get_symbols_table() {
        return symbols_table;
    }

    public void set_symbols_table(My_I_dictionary<String, Value> symbol_table) {
        this.symbols_table = symbol_table;
    }

    public My_I_list<Value> get_output() {
        return output;
    }

    public void set_output(My_I_list<Value> output) {
        this.output = output;
    }

    public My_I_dictionary<String_value, BufferedReader> get_file_table()
    {
        return this.file_table;
    }

    public void set_file_table(My_I_dictionary<String_value, BufferedReader> file_table)
    {
        this.file_table = file_table;
    }

    public My_I_heap<Value> get_heap()
    {
        return heap;
    }

    public void set_heap(My_I_heap<Value> heap)
    {
        this.heap = heap;
    }

    public Program_state(My_I_stack<I_statement> execution_stack, My_I_dictionary<String, Value> symbols_table, My_I_list<Value> output, My_I_dictionary<String_value, BufferedReader> file_table, My_I_heap<Value> heap, I_statement program)
    {
        this.execution_stack = execution_stack;
        this.symbols_table = symbols_table;
        this.output = output;
        this.file_table = file_table;
        this.heap = heap;
        this.execution_stack.push(program);
        this.current_state_id = Program_state.assign_id();
    }

    public String toString()
    {
        return "Program state ID: " + this.current_state_id +"\n" + "Execution stack:\n" + this.execution_stack.toString() +
                "\n" + "Symbol table:\n" + this.symbols_table.toString() + "\n" + "Output:\n" + this.output.toString() + "\n" + "File table:\n" + this.file_table.toString() +
                "\n" + "Heap:\n" + this.heap.toString() + "\n******************\n";
    }

    public boolean is_not_completed()
    {
        return !execution_stack.empty();
    }

    public Program_state one_step() throws MyException
    {
        if(execution_stack.empty())
        {
            throw new MyException("Program state execution stack is empty!");
        }
        I_statement current_statement = execution_stack.pop();
        return current_statement.execute(this);
    }
}
