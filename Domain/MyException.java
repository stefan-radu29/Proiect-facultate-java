package Domain;

public class MyException extends Exception
{
    public MyException(){}
    public MyException(String error_message)
    {
        super(error_message);
    }
}