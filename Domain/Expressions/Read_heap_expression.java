package Domain.Expressions;

import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_heap;
import Domain.MyException;
import Domain.Types.Reference_type;
import Domain.Types.Type;
import Domain.Values.Reference_value;
import Domain.Values.Value;

public class Read_heap_expression implements Expression
{
    Expression expression;

    public Read_heap_expression(Expression expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString()
    {
        return "read_heap(" + expression.toString() + ")";
    }

    @Override
    public Value evaluate(My_I_dictionary<String, Value> symbols_table, My_I_heap<Value> heap) throws MyException
    {
        if(expression.evaluate(symbols_table, heap) instanceof Reference_value)
        {
            Reference_value expression_value = (Reference_value)expression.evaluate(symbols_table, heap);
            if(heap.contains_key(expression_value.get_address()))
            {
                return heap.get_value(expression_value.get_address());
            }
            else
            {
                throw new MyException("Address " + expression_value.get_address() + " is not a valid address in the heap!");
            }
        }
        else
        {
            throw new MyException("Expression " + expression.toString() + " is not a Reference_type!");
        }
    }

    @Override
    public Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type type = expression.type_check(type_environment);
        if(type instanceof Reference_type)
        {
            Reference_type reference_type = (Reference_type) type;
            return reference_type.get_inner();
        }
        else
        {
            throw new MyException("The argument of the expression " + this.toString() + " is not a Reference_type!");
        }
    }
}
