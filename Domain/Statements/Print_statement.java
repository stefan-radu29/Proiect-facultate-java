package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_list;
import Domain.Collections.My_list;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Type;
import Domain.Values.Value;

public class Print_statement implements I_statement
{
    Expression expression;

    public Print_statement(Expression expression)
    {
        this.expression = expression;
    }

    public String toString()
    {
        return "print(" +expression.toString() + ")";
    }

    public Program_state execute(Program_state state) throws MyException
    {
        My_I_list<Value> output = state.get_output();
        output.add(expression.evaluate(state.get_symbols_table(), state.get_heap()));
        state.set_output(output);
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        expression.type_check(type_environment);
        return type_environment;
    }
}
