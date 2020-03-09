package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Reference_type;
import Domain.Types.Type;
import Domain.Values.Reference_value;
import Domain.Values.Value;

public class New_statement implements I_statement
{
    String variable_name;
    Expression expression;

    public New_statement(String variable_name, Expression expression)
    {
        this.variable_name = variable_name;
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "new(" + variable_name + ", " + expression.toString() + ")";
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        if(state.get_symbols_table().contains_key(variable_name) && state.get_symbols_table().get_value(variable_name).get_type() instanceof Reference_type)
        {
            Value expression_value = expression.evaluate(state.get_symbols_table(), state.get_heap());
            Reference_value variable_name_value = (Reference_value) state.get_symbols_table().get_value(variable_name);
            if(expression_value.get_type().equals(variable_name_value.get_location_type()))
            {
                state.get_heap().put(expression_value);
                int address = state.get_heap().get_free_address() - 1;
                state.get_symbols_table().update(variable_name, new Reference_value(address, variable_name_value.get_location_type()));
            }
            else
            {
                throw new MyException("Location type of variable " + variable_name + " is not the same as the type of the expression " + expression.toString() + "!");
            }
        }
        else
        {
            throw new MyException("Variable " + variable_name +  " was not declared or its type is not Reference_type!");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type variable_type = type_environment.get_value(variable_name);
        Type expression_type = expression.type_check(type_environment);
        if(variable_type.equals(new Reference_type(expression_type)))
        {
            return type_environment;
        }
        else
        {
            throw new MyException("Right hand side and left hand side of the statement " + this.toString() + " have different types!");
        }
    }
}
