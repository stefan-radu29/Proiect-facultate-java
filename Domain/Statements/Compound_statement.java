package Domain.Statements;

import Domain.Collections.*;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Type;

public class Compound_statement implements I_statement
{
    I_statement first;
    I_statement second;

    public Compound_statement(I_statement first, I_statement second)
    {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "{"+first.toString() + ";" + second.toString()+"}";
    }

    public Program_state execute(Program_state state) throws MyException
    {
        My_I_stack<I_statement> stack = state.get_execution_stack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        return second.type_check(first.type_check(type_environment));
    }
}
