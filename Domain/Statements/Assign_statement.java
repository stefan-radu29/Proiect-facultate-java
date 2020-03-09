package Domain.Statements;

import Domain.Collections.*;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Types.*;
import Domain.Values.Value;
import Domain.Program_state;

public class Assign_statement implements I_statement
{
    String id;
    Expression expression;

    public Assign_statement(String i, Expression e)
    {
        this.id = i;
        this.expression = e;
    }

    public String toString()
    {
        return id + "="+ expression.toString();
    }

    public Program_state execute(Program_state state) throws MyException
    {
        My_I_dictionary<String, Value> symbols_table = state.get_symbols_table();
        Value value = expression.evaluate(symbols_table, state.get_heap());
        if (symbols_table.contains_key(id))
        {
            Type type_id = (symbols_table.get_value(id)).get_type();
            if ((value.get_type()).equals(type_id))
            {
                symbols_table.update(id,value);
            }
            else
            {
                throw new MyException("declared type of variable "+id+" and type of the assigned expression do not match");
            }
        }
        else
        {
            throw new MyException("the used variable" +id + " was not declared before");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type variable_type = type_environment.get_value(id);
        Type expression_type = expression.type_check(type_environment);
        if(variable_type.equals(expression_type))
        {
            return type_environment;
        }
        else
        {
            throw new MyException("Right hand side and left hand side of the statement " + this.toString() + " have different types!");
        }
    }
}
