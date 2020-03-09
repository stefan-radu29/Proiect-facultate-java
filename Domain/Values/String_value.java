package Domain.Values;

import Domain.Types.String_type;
import Domain.Types.Type;

public class String_value implements Value
{
    String value;

    public String_value(String s)
    {
        this.value = s;
    }

    public String get_value()
    {
        return value;
    }

    public String toString()
    {
        return "\"" + value + "\"";
    }

    @Override
    public Type get_type()
    {
        return new String_type();
    }

    @Override
    public Value deep_copy_value() {
        return new String_value(value);
    }

    public boolean equals(Object another)
    {
        if (another instanceof String_value && this.value.equals(((String_value) another).get_value()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
