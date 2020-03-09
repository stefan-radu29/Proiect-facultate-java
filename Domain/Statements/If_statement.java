package Domain.Statements;

import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Collections.*;
import Domain.Types.Bool_type;
import Domain.Types.Type;
import Domain.Values.Bool_value;
import My_utilities.My_utilities;

import javax.swing.text.Utilities;

public class If_statement implements I_statement
{
    Expression expression;
    I_statement then_statement;
    I_statement else_statement;

    public If_statement(Expression expression, I_statement t, I_statement el)
    {
        this.expression = expression;
        this.then_statement = t;
        this.else_statement = el;
    }

    public String toString()
    {
        return "IF("+ expression.toString()+") THEN(" +then_statement.toString() +") ELSE("+else_statement.toString()+")";
    }

    public Program_state execute(Program_state state) throws MyException
    {
        if(this.expression.evaluate(state.get_symbols_table(), state.get_heap()).get_type().equals(new Bool_type()))
        {
            Bool_value bool_value = (Bool_value)this.expression.evaluate(state.get_symbols_table(), state.get_heap());
            if(bool_value.get_value())
            {
                this.then_statement.execute(state);
            }
            else
            {
                this.else_statement.execute(state);
            }
        }
        else
        {
            throw new MyException("Expression " + expression.toString() + "  is not boolean!");

        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type expression_type = expression.type_check(type_environment);
        if(expression_type.equals(new Bool_type()))
        {
            then_statement.type_check(My_utilities.clone_type_environment(type_environment));
            else_statement.type_check(My_utilities.clone_type_environment(type_environment));
            return type_environment;
        }
        else
        {
            throw new MyException("The condition of the IF statement " + this.toString() + " is not of boolean type!");
        }
    }
}
