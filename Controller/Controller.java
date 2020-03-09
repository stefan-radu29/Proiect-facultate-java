package Controller;

import Domain.Collections.My_I_stack;
import Domain.MyException;
import Domain.Program_state;
import Domain.Statements.I_statement;
import Domain.Values.Reference_value;
import Domain.Values.Value;
import Repository.I_repository;
import Repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller
{
    I_repository repository;
    //boolean display_flag;
    ExecutorService executor;

    public Controller(I_repository repository)
    {
        this.repository = repository;
        //this.display_flag = display_flag;
    }
    public I_repository get_repository()
    {
        return this.repository;
    }

    public void one_step_for_all_programs(List<Program_state> programs) throws MyException
    {
        programs.forEach(program-> {
            try
            {
                repository.log_program_state_execution(program);
            } catch (MyException e)
            {
                System.out.println(e.getMessage());
            }
        });
        List<Callable<Program_state>> call_list = programs.stream()
                .map((Program_state state) -> (Callable<Program_state>)(()->{return state.one_step();}))
                .collect(Collectors.toList());
        try
        {
            List<Program_state> new_programs = executor.invokeAll(call_list).stream()
                    .map(future -> {
                        try
                        {
                            return future.get();
                        }
                        catch (InterruptedException | ExecutionException e)
                        {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(state -> state != null)
                    .collect(Collectors.toList());
            programs.addAll(new_programs);
        } catch (InterruptedException e)
        {
            throw new MyException(e.getMessage());
        }
        programs.forEach(program->
        {
            try
            {
                repository.log_program_state_execution(program);
            }
            catch (MyException e)
            {
                System.out.println(e.getMessage());
            }
        });
        repository.set_program_states(programs);

    }

    public void all_step() throws MyException
    {
        executor = Executors.newFixedThreadPool(2);
        List<Program_state> programs = remove_completed_programs(repository.get_program_states());
        if(programs.size() > 0) //changed while to if
        {
            programs.stream().findAny().get().get_heap().set_content(
                    garbage_collector(get_used_addresses(repository.get_program_states()), repository.get_program_states().stream().findAny().get().get_heap().get_content()));
            one_step_for_all_programs(programs);
            programs = remove_completed_programs(programs);
        }
        executor.shutdownNow();
        repository.set_program_states(programs);
    }

    private List<Integer> get_used_addresses(Collection<Program_state> states)
    {
        List<Integer> symbols_table_addresses = states.stream()
                .map(state->state.get_symbols_table().get_content().values().stream().collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .filter(v -> v instanceof Reference_value)
                .map(value->{Reference_value value2 = (Reference_value) value; return value2.get_address();})
                .collect(Collectors.toList());
        Collection<Value> heap = states.stream().findAny().get().get_heap().get_content().values();
        List<Integer> heap_table_addresses = heap.stream()
                .filter(v -> v instanceof Reference_value)
                .map(value->{Reference_value value2 = (Reference_value) value; return value2.get_address();})
                .collect(Collectors.toList());

        symbols_table_addresses.addAll(heap_table_addresses);
        return symbols_table_addresses;
    }

    private Map<Integer, Value> garbage_collector(List<Integer> used_addresses, Map<Integer, Value> heap)
    {
        return heap.entrySet().stream()
                .filter(e -> used_addresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Program_state> remove_completed_programs(List<Program_state> program_states)
    {
        return program_states.stream().filter(state->state.is_not_completed()).collect(Collectors.toList());
    }


}
