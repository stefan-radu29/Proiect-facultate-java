package Domain.Values;

import Domain.Types.*;

public class Bool_value implements Value
{
    boolean value;

    public Bool_value(){}

    public Bool_value(boolean v)
    {
        this.value = v;
    }

    public boolean get_value()
    {
        return value;
    }

    public String toString()
    {
        return Boolean.toString(value);
    }

    public Type get_type()
    {
        return new Bool_type();
    }

    @Override
    public Value deep_copy_value()
    {
        return new Bool_value(value);
    }

    public boolean equals(Object another)
    {
        if (another instanceof Bool_value && this.value == ((Bool_value) another).get_value())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
