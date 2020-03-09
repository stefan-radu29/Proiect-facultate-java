package Domain.Values;

import Domain.Types.*;

public interface Value
{
    Type get_type();
    Value deep_copy_value();
}
