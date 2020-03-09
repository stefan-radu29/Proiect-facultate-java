package Domain.Statements;

import Domain.Collections.*;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Type;
import Domain.Values.String_value;
import Domain.Values.Value;
import My_utilities.My_utilities;

import java.io.BufferedReader;
import java.util.HashMap;

public class Fork_statement implements I_statement
{
    I_statement statement;

    public Fork_statement(I_statement statement)
    {
        this.statement = statement;
    }

    @Override
    public Program_state execute(Program_state state) throws MyException  //how to copy symbols_table????
    {
        My_I_stack<I_statement> new_execution_stack = new My_stack<>();

        My_I_dictionary<String, Value> new_symbols_table = new My_dictionary<>();
        for(HashMap.Entry<String, Value> entry: state.get_symbols_table().get_content().entrySet())
        {
            new_symbols_table.put(entry.getKey(), entry.getValue().deep_copy_value());
        }

        My_I_heap<Value> new_heap = state.get_heap();
        My_I_dictionary<String_value, BufferedReader> new_file_table = state.get_file_table();
        My_I_list<Value> new_output = state.get_output();
        return new Program_state(new_execution_stack, new_symbols_table, new_output, new_file_table, new_heap, statement);
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        statement.type_check(My_utilities.clone_type_environment(type_environment));
        return type_environment;
    }

    @Override
    public String toString()
    {
        return "fork(" + statement.toString() + ")";
    }
}
