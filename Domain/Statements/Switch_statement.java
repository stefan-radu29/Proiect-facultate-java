package Domain.Statements;

import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_stack;
import Domain.Expressions.Expression;
import Domain.Expressions.Relational_expression;
import Domain.MyException;
import Domain.Program_state;
import Domain.Types.Type;
import My_utilities.My_utilities;

public class Switch_statement implements I_statement
{

    Expression switch_expression;
    Expression expression1;
    I_statement statement1;
    Expression expression2;
    I_statement statement2;
    I_statement default_statement;

    public Switch_statement(Expression switch_expression, Expression expression1, I_statement statement1, Expression expression2, I_statement statement2, I_statement default_statement)
    {
        this.switch_expression = switch_expression;
        this.expression1 = expression1;
        this.statement1 = statement1;
        this.expression2 = expression2;
        this.statement2 = statement2;
        this.default_statement = default_statement;
    }

    @Override
    public Program_state execute(Program_state state) throws MyException
    {
        I_statement statement_to_push = new If_statement(new Relational_expression("==", switch_expression, expression1), statement1,
                new If_statement(new Relational_expression("==", switch_expression, expression2), statement2, default_statement));
        My_I_stack<I_statement> stack = state.get_execution_stack();
        stack.push(statement_to_push);
        state.set_execution_stack(stack);
        return null;
    }

    @Override
    public My_I_dictionary<String, Type> type_check(My_I_dictionary<String, Type> type_environment) throws MyException
    {
        Type switch_expression_type = switch_expression.type_check(type_environment);
        Type expression1_type = expression1.type_check(type_environment);
        Type expression2_type = expression2.type_check(type_environment);
        if(switch_expression_type.equals(expression1_type) && switch_expression_type.equals(expression2_type))
        {
            statement1.type_check(My_utilities.clone_type_environment(type_environment));
            statement2.type_check(My_utilities.clone_type_environment(type_environment));
            default_statement.type_check(My_utilities.clone_type_environment(type_environment));
            return type_environment;
        }
        else
        {
            throw new MyException("The expressions used as conditions for the switch do not have the same type!");
        }
    }

    public String toString()
    {
        return "SWITCH("+ switch_expression.toString()+")" + "\n" + "case(" + expression1.toString() + "): " + statement1.toString() + "\n" +
            "case(" + expression2.toString() + "): " + statement2.toString() + "\n" + "default case : " + default_statement.toString() + "\n";
    }
}
