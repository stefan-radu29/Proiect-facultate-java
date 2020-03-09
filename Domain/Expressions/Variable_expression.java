package Domain.Expressions;

import Domain.MyException;
import Domain.Types.Type;
import Domain.Values.Value;
import Domain.Collections.*;

public class Variable_expression implements Expression
{
    String variable_id;

    public Variable_expression(String variable_id)
    {
        this.variable_id = variable_id;
    }

    public String toString()
    {
        return variable_id;
    }

    @Override
    public Value evaluate(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException
    {
        return table.get_value(this.variable_id);
    }

    @Override
    public Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        return type_environment.get_value(variable_id);
    }
}
