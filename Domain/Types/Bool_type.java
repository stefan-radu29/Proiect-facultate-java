package Domain.Types;

import Domain.Values.Bool_value;
import Domain.Values.Value;

public class Bool_type implements Type
{
    public boolean equals(Object another)
    {
        if (another instanceof Bool_type)
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
        return "boolean";
    }

    @Override
    public Value default_value()
    {
        return new Bool_value(false);
    }

    @Override
    public Type deep_copy_type() {
        return new Bool_type();
    }
}
