package View;

public class Exit_command extends Command
{

    public Exit_command(String key, String description)
    {
        super(key, description);
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
