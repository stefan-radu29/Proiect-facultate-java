package Domain.Expressions;
import Domain.Collections.*;
import Domain.MyException;
import Domain.Types.Int_type;
import Domain.Types.Type;
import Domain.Values.Int_value;
import Domain.Values.Value;

public class Arithmetic_expression implements Expression
{
    Expression expression1;
    Expression expression2;
    String operation;

    public Arithmetic_expression(String op, Expression e1, Expression e2)
    {
        this.expression1 = e1;
        this.expression2 = e2;
        this.operation = op;
    }

    public Expression get_expression1()
    {
        return this.expression1;
    }

    public void set_expression1(Expression new_expression)
    {
        this.expression1 = new_expression;
    }

    public Expression get_expression2()
    {
        return this.expression2;
    }

    public void set_expression2(Expression new_expression)
    {
        this.expression2 = new_expression;
    }

    public String get_operation()
    {
        return this.operation;
    }

    public void set_operation(String new_operation)
    {
        this.operation = new_operation;
    }


    public Value evaluate(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException
    {
        Value value1,value2;
        value1= expression1.evaluate(table, heap);
        if (value1.get_type().equals(new Int_type()))
        {
            value2 = expression2.evaluate(table, heap);
            if (value2.get_type().equals(new Int_type())) {
                Int_value int_value1 = (Int_value)value1;
                Int_value int_value2 = (Int_value)value2;
                int n1,n2;
                n1 = int_value1.get_value();
                n2 = int_value2.get_value();
                if (operation.equals("+")) return new Int_value(n1+n2);
                if (operation.equals("-")) return new Int_value(n1-n2);
                if (operation.equals("*")) return new Int_value(n1*n2);
                if (operation.equals("/"))
                {
                    if (n2 == 0) throw new MyException("division by zero");
                    else
                        return new Int_value(n1 / n2);
                }
            }
            else
                throw new MyException("second operand is not an integer!");
        }
        else
            throw new MyException("first operand is not an integer!");

        return value1;
    }

    @Override
    public Type type_check(My_I_dictionary<String, Type> type_environment) throws MyException {
        Type type1, type2;
        type1 = expression1.type_check(type_environment);
        type2 = expression2.type_check(type_environment);
        if(type1.equals(new Int_type()))
        {
            if(type2.equals(new Int_type()))
            {
                return new Int_type();
            }
            else
            {
                throw new MyException("Second operand of the arithmetic expression " + this.toString() + " is not an integer!");
            }
        }
        else
        {
            throw new MyException("First operand of the arithmetic expression " + this.toString() + " is not an integer!");
        }
    }


    public String toString()
    {
        return "(" + expression1.toString() + operation + expression2.toString() + ")";
    }

    public Type get_type(My_I_dictionary<String, Value> table, My_I_heap<Value> heap) throws MyException
    {
        return this.evaluate(table, heap).get_type();
    }

}
