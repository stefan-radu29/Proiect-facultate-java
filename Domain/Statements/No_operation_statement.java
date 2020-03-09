package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Type;

public class No_operation_statement implements I_statement
{
    public Program_state execute(Program_state state) throws MyException
    {
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        return type_environment;
    }

    public String toString()
    {
        return "No operation.";
    }
}
