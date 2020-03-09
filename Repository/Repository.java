package Repository;

import Domain.MyException;
import Domain.Program_state;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository  implements  I_repository
{
    ArrayList<Program_state> program_states;
    String log_file_path;

    public Repository(Program_state program_state, String log_file_path) {
        this.program_states = new ArrayList<>();
        this.program_states.add(program_state);
        this.log_file_path = log_file_path;
        try (PrintWriter writer = new PrintWriter(this.log_file_path))
        {
            writer.print("");
        }
        catch (IOException exception) {}
    }

    @Override
    public List<Program_state> get_program_states()
    {
        return this.program_states;
    }

    @Override
    public void set_program_states(List<Program_state> new_program_states)
    {
        this.program_states = (ArrayList<Program_state>) new_program_states;
    }

    @Override
    public void log_program_state_execution(Program_state state) throws MyException
    {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.log_file_path, true))))
        {
            writer.print(state.toString());
        }
        catch(IOException io_error)
        {
            throw new MyException("Cannot create log file!");
        }
    }
}
