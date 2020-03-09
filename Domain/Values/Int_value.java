package Domain.Values;

import Domain.Types.*;

public class Int_value implements Value
{
    int value;

    public Int_value(){}

    public Int_value(int v)
    {
        this.value = v;
    }

    public int get_value()
    {
        return value;
    }

    public String toString()
    {
        return Integer.toString(value);
    }

    public Type get_type()
    {
        return new Int_type();
    }

    @Override
    public Value deep_copy_value()
    {
        return new Int_value(value);
    }

    public boolean equals(Object another)
    {
        if (another instanceof Int_value && this.value == ((Int_value) another).get_value())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
