package Domain.Expressions;

import Domain.Collections.*;
import Domain.MyException;
import Domain.Types.Type;
import Domain.Values.Value;

public class Value_expression implements Expression
{

    Value value;

    public Value_expression(Value v)
    {
        this.value = v;
    }

    public Value get_value()
    {
        return value;
    }

    public void set_value(Value new_value)
    {
        this.value = new_value;
    }

    @Override
    public Value evaluate(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException
    {
        return value;
    }

    @Override
    public Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        return value.get_type();
    }

    public String toString()
    {
        return value.toString();
    }

    public Type get_type(My_I_dictionary<String, Value> table) throws MyException
    {
        return value.get_type();
    }

}
