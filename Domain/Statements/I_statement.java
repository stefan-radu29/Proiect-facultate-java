package Domain.Statements;

import Domain.MyException;
import Domain.Collections.*;
import Domain.Program_state;
import Domain.Types.Type;

public interface I_statement
{
    Program_state execute(Program_state state) throws MyException;
    My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException;
}
