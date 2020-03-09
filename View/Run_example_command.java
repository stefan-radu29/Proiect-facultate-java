package View;

import Controller.Controller;
import Domain.MyException;

public class Run_example_command extends Command
{
    private Controller controller;

    public Run_example_command(String key, String description, Controller controller)
    {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        try
        {
            controller.all_step();
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage() + "\n");
        }
    }
}
