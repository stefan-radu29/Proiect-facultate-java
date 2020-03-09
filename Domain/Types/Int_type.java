package Domain.Types;

import Domain.Values.Int_value;
import Domain.Values.Value;

public class Int_type implements Type
{
    public boolean equals(Object another)
    {
        if (another instanceof Int_type)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public Value default_value()
    {
        return new Int_value(0);
    }

    @Override
    public Type deep_copy_type() {
        return new Int_type();
    }
}
