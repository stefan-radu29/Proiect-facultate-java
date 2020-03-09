package Repository;

import Domain.MyException;
import Domain.Program_state;

import java.util.List;

public interface I_repository
{
    List<Program_state> get_program_states();
    void set_program_states(List<Program_state> new_program_states);
    void log_program_state_execution(Program_state state) throws MyException;
}
