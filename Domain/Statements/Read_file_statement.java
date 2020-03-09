package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Int_type;
import Domain.Types.String_type;
import Domain.Types.Type;
import Domain.Values.Int_value;
import Domain.Values.String_value;

import java.io.BufferedReader;
import java.io.IOException;

public class Read_file_statement implements I_statement
{
    Expression expression;
    String variable_name;

    public Read_file_statement(Expression expression, String variable_name)
    {
        this.expression = expression;
        this.variable_name = variable_name;
    }

    @Override
    public String toString()
    {
        return "read_file(" + this.expression + ", " + this.variable_name + ")";
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        if(state.get_symbols_table().contains_key(variable_name) && state.get_symbols_table().get_value(variable_name).get_type().equals(new Int_type()))
        {
            if(expression.evaluate(state.get_symbols_table(), state.get_heap()).get_type().equals(new String_type()))
            {
                String_value file_name = (String_value)expression.evaluate(state.get_symbols_table(), state.get_heap());
                if(state.get_file_table().contains_key(file_name))
                {
                    BufferedReader reader = state.get_file_table().get_value(file_name);
                    try
                    {
                        if(!reader.ready())
                        {
                            Int_value value = new Int_value(0);
                            state.get_symbols_table().update(variable_name, value);
                        }
                        else
                        {
                            Int_value value = new Int_value(Integer.parseInt(reader.readLine()));
                            state.get_symbols_table().update(variable_name, value);
                        }
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
                throw new MyException("Expression " + expression.toString() + "  is not a String_type!");
            }
        }
        else
        {
            throw new MyException("Variable " + variable_name + " was not declared or is not an Int_type!");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException
    {
        Type variable_type = type_environment.get_value(variable_name);
        Type expression_type = expression.type_check(type_environment);
        if(expression_type.equals(new String_type()))
        {
            if(variable_type.equals(new Int_type()))
            {
                return type_environment;
            }
            else
            {
                throw new MyException("Second argument of the statement " + this.toString() + " is not an integer!");
            }
        }
        else
        {
            throw new MyException("First argument of the statement " + this.toString() + " is not a string!");
        }
    }

}
