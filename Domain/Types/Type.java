package Domain.Types;

import Domain.Values.Value;

public interface Type
{
    Value default_value();
    Type deep_copy_type();
}
