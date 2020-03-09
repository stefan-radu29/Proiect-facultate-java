package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Reference_type;
import Domain.Types.Type;
import Domain.Values.Reference_value;
import Domain.Values.Value;

public class Write_heap_statement implements I_statement
{
    String variable_name;
    Expression expression;

    public Write_heap_statement(String variable_name, Expression expression)
    {
        this.variable_name = variable_name;
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "write_heap(" + variable_name + ", " + expression.toString() + ")";
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        if(state.get_symbols_table().contains_key(variable_name) && state.get_symbols_table().get_value(variable_name).get_type() instanceof Reference_type)
        {
            Reference_value value = (Reference_value)state.get_symbols_table().get_value(variable_name);
            if(state.get_heap().contains_key(value.get_address()))
            {
                Value expression_value = expression.evaluate(state.get_symbols_table(), state.get_heap());
                if(expression_value.get_type().equals(value.get_location_type()))
                {
                    state.get_heap().update(value.get_address(), expression_value);
                }
                else
                {
                    throw new MyException("Expression " + expression.toString() + " type is not the same as the location_type of " + variable_name + "!");
                }
            }
            else
            {
                throw new MyException("Address " + value.get_address() + " is not a valid address in the heap!");
            }
        }
        else
        {
            throw new MyException("Variable " + variable_name + " was not declared or is not a Reference_type!");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException
    {
        Type variable_type = type_environment.get_value(variable_name);
        Type expression_type = expression.type_check(type_environment);
        if(variable_type instanceof Reference_type)
        {
            if(((Reference_type) variable_type).get_inner().equals(expression_type))
            {
                return type_environment;
            }
            else
            {
                throw new MyException("The types of the arguments of the statement " + this.toString() + " do not match!");
            }
        }
        else
        {
            throw new MyException("The first argument of the statement " + this.toString() + " is not a reference type!");
        }
    }
}
