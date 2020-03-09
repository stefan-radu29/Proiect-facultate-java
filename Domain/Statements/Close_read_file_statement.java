package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.String_type;
import Domain.Types.Type;
import Domain.Values.String_value;
import Domain.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class Close_read_file_statement implements I_statement
{
    Expression expression;

    public Close_read_file_statement(Expression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "close_read_file(" + this.expression + ")";
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        Value expression_value = expression.evaluate(state.get_symbols_table(), state.get_heap());
        if(expression_value.get_type().equals(new String_type()))
        {
            if(state.get_file_table().contains_key((String_value)expression_value))
            {
                BufferedReader reader = state.get_file_table().get_value((String_value)expression_value);
                try
                {
                    reader.close();
                    state.get_file_table().remove((String_value)expression_value);
                }
                catch(Exception exception)
                {
                    throw new MyException(exception.toString());
                }
            }
            else
            {
                throw new MyException("File " + expression.toString() + "  was not opened!");
            }
        }
        else
        {
            throw new MyException("Expression " + expression.toString() + " is not a String_type!");
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
}
