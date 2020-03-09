package Domain.Statements;
import Domain.Collections.My_I_dictionary;
import Domain.MyException;
import Domain.Types.*;
import Domain.Program_state;
import Domain.Values.Bool_value;
import Domain.Values.Int_value;
import Domain.Values.Value;

public class Variable_declaration_statement implements I_statement
{
    String name;
    Type type;

    public Variable_declaration_statement(String name, Type type)
    {
        this.name = name;
        this.type = type;
    }

    public Program_state execute(Program_state state) throws MyException
    {
        My_I_dictionary<String, Value> symbols_table = state.get_symbols_table();
        if(symbols_table.contains_key(this.name))
        {
            throw new MyException("Variable " + name + " already declared!");
        }
        else
        {
            symbols_table.put(name, type.default_value());
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        type_environment.put(name, type);
        return type_environment;
    }

    public String toString()
    {
        return type.toString() + " " + name;
    }
}
