package Domain.Statements;


import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_stack;
import Domain.Expressions.Expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Bool_type;
import Domain.Types.Type;
import Domain.Values.Bool_value;
import Domain.Values.Value;
import My_utilities.My_utilities;

public class While_statement implements I_statement
{
    Expression expression;
    I_statement statement;

    public While_statement(Expression expression, I_statement statement)
    {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString()
    {
        return "(WHILE " + expression.toString() + " " + statement.toString() + ")";
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        Value expression_value = expression.evaluate(state.get_symbols_table(), state.get_heap());
        if(expression_value.get_type().equals(new Bool_type()))
        {
            Bool_value bool_expression_value = (Bool_value) expression_value;
            My_I_stack<I_statement> stack = state.get_execution_stack();
            if(bool_expression_value.get_value())
            {
                stack.push(new While_statement(expression, statement));
                stack.push(statement);
                state.set_execution_stack(stack);
            }
        }
        else
        {
            throw new MyException("Expression " + expression.toString() + "  is not bool type!");
        }
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type expression_type = expression.type_check(type_environment);
        if(expression_type.equals(new Bool_type()))
        {
            statement.type_check(My_utilities.clone_type_environment(type_environment));
            return type_environment;
        }
        else
        {
            throw new MyException("The condition of the WHILE statement " + this.toString() + " is not of boolean type!");
        }
    }
}
