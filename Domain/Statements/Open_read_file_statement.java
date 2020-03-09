package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.String_type;
import Domain.Types.Type;
import Domain.Values.String_value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Open_read_file_statement implements I_statement
{
    Expression expression;

    public Open_read_file_statement(Expression expression)
    {
        this.expression = expression;
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        if(this.expression.evaluate(state.get_symbols_table(), state.get_heap()).get_type().equals(new String_type()))
        {
            if (!state.get_file_table().contains_key((String_value)this.expression.evaluate(state.get_symbols_table(), state.get_heap())))
            {
                try
                {
                    BufferedReader reader = new BufferedReader(new FileReader(((String_value)( this.expression.evaluate(state.get_symbols_table(), state.get_heap()))).get_value()));
                    My_I_dictionary<String_value, BufferedReader> file_table = state.get_file_table();
                    file_table.put((String_value)this.expression.evaluate(state.get_symbols_table(), state.get_heap()), reader);
                    state.set_file_table(file_table);
                }
                catch(Exception exception)
                {
                    throw new MyException(exception.toString());
                }
            }
            else
            {
                throw new MyException("File " + expression.toString() + "  already opened!");
            }
        }
        else
        {
            throw new MyException("Expression " + expression.toString() + "  is not a String_type!");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException
    {
        Type expression_type = expression.type_check(type_environment);
        if(expression_type.equals(new String_type()))
        {
            return type_environment;
        }
        else
        {
            throw new MyException("The argument of the statement " + this.toString() + " is not a string!");
        }
    }

    @Override
    public String toString()
    {
        return "open_read_file(" + this.expression.toString() + ")";
    }
}
