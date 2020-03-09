package Domain.Expressions;
import Domain.Collections.*;
import Domain.MyException;
import Domain.Types.Type;
import Domain.Values.Value;

public interface Expression
{
    Value evaluate(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException;
    Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException;
}
