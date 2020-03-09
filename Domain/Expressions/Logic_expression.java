package Domain.Expressions;

import Domain.Collections.*;
import Domain.MyException;
import Domain.Types.Bool_type;
import Domain.Types.Int_type;
import Domain.Types.Type;
import Domain.Values.Bool_value;
import Domain.Values.Value;

public class Logic_expression implements Expression
{
    Expression expression1;
    Expression expression2;
    String operation;

    public Logic_expression(Expression e1, Expression e2, String operation)
    {
        this.expression1 = e1;
        this.expression2 = e2;
        this.operation = operation;
    }

    public Expression get_expression1() {
        return expression1;
    }

    public void set_expression1(Expression new_expression1) {
        this.expression1 = new_expression1;
    }

    public Expression get_expression2() {
        return expression2;
    }

    public void set_expression2(Expression new_expression2) {
        this.expression2 = new_expression2;
    }

    public String get_operation() {
        return operation;
    }

    public void set_operation(String new_operation) {
        this.operation = new_operation;
    }

    public String toString()
    {
        return "(" + expression1.toString() + operation + expression2.toString() + ")";
    }

    public Value evaluate(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException
    {
        Value value1, value2;
        value1 = this.expression1.evaluate(table, heap);
        if(value1.get_type().equals(new Bool_type()))
        {
            value2 = this.expression2.evaluate(table, heap);
            if(value2.get_type().equals(new Bool_type()))
            {
                Bool_value bool_value1 = (Bool_value)value1;
                Bool_value bool_value2 = (Bool_value)value2;
                boolean b1,b2;
                b1 = bool_value1.get_value();
                b2 = bool_value2.get_value();
                if(this.operation.equals("&&"))
                {
                    return new Bool_value(b1 && b2);
                }
                if(this.operation.equals("||"))
                {
                    return new Bool_value(b1 || b2);
                }
            }
            else
                throw new MyException("Second operand is not a boolean value!");
        }
        else
            throw new MyException("First operand is not a boolean value!");
        return value1;
    }

    @Override
    public Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type type1, type2;
        type1 = expression1.type_check(type_environment);
        type2 = expression2.type_check(type_environment);
        if(type1.equals(new Bool_type()))
        {
            if(type2.equals(new Bool_type()))
            {
                return new Bool_type();
            }
            else
            {
                throw new MyException("Second operand of the logic expression " + this.toString() + " is not boolean!");
            }
        }
        else
        {
            throw new MyException("First operand of the logic expression " + this.toString() + " is not boolean!");
        }
    }

}
