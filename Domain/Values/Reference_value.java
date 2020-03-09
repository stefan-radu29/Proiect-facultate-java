package Domain.Values;

import Domain.Types.Reference_type;
import Domain.Types.Type;

public class Reference_value implements Value
{
    int address;
    Type location_type;

    public Reference_value(int address, Type location_type)
    {
        this.address = address;
        this.location_type = location_type;
    }

    public int get_address()
    {
        return this.address;
    }

    public Type get_location_type()
    {
        return this.location_type;
    }

    @Override
    public String toString()
    {
        return "(" + address + ", " + location_type.toString() + ")";
    }

    @Override
    public Type get_type()
    {
        return new Reference_type(location_type);
    }

    @Override
    public Value deep_copy_value()
    {
        return new Reference_value(address, location_type);
    }
}
