package Domain.Types;

import Domain.Values.String_value;
import Domain.Values.Value;

public class String_type implements Type
{
    public boolean equals(Object another)
    {
        if (another instanceof String_type)
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
        return "string";
    }

    @Override
    public Value default_value()
    {
        return new String_value("");
    }

    @Override
    public Type deep_copy_type() {
        return new String_type();
    }
}
