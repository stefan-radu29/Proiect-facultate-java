package View;

import java.util.HashMap;
import java.util.Scanner;

public class Text_menu
{
    private HashMap<String, Command> commands;

    public Text_menu()
    {
        this.commands = new HashMap<String, Command>();
    }

    public void add_command(Command command)
    {
        commands.put(command.get_key(), command);
    }

    private void print_menu()
    {
        for(Command command : commands.values())
        {
            String line = String.format("%s.%s", command.get_key(), command.get_description());
            System.out.println(line);
        }
    }

    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            print_menu();
            System.out.println("\nInput the option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if(command == null)
            {
                System.out.println("Invalid option!\n");
                continue;
            }
            command.execute();
            this.commands.remove(key);
        }
    }
}
