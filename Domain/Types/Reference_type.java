package Domain.Types;

import Domain.Values.Reference_value;
import Domain.Values.Value;

public class Reference_type implements Type
{
    Type inner;

    public Reference_type(Type inner)
    {
        this.inner = inner;
    }

    public Type get_inner()
    {
        return this.inner;
    }

    public boolean equals(Object another)
    {
        if (another instanceof Reference_type)
            return inner.equals(((Reference_type) another).get_inner());
        else
            return false;
    }

    @Override
    public String toString()
    {
        return "ref(" + inner.toString() + ")";
    }

    @Override
    public Value default_value()
    {
        return new Reference_value(0, inner);
    }

    @Override
    public Type deep_copy_type() {
        return new Reference_type(inner.deep_copy_type());
    }
}
