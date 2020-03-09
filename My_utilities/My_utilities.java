package My_utilities;

import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_dictionary;
import Domain.Types.Type;

import java.util.Map;

public class My_utilities
{
    public static My_I_dictionary<String, Type> clone_type_environment(My_I_dictionary<String, Type> type_environment)
    {
        My_I_dictionary<String, Type> cloned_type_environment = new My_dictionary<>();
        for(Map.Entry<String, Type> entry : type_environment.get_pairs_set())
        {
            cloned_type_environment.put(entry.getKey(), entry.getValue().deep_copy_type());
        }
        return cloned_type_environment;
    }
}
