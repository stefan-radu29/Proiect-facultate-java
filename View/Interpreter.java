package View;

import Controller.Controller;
import Domain.Collections.*;
import Domain.Expressions.*;
import Domain.MyException;
import Domain.Program_state;
import Domain.Statements.*;
import Domain.Types.*;
import Domain.Values.Bool_value;
import Domain.Values.Int_value;
import Domain.Values.String_value;
import Domain.Values.Value;
import Repository.I_repository;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter
{
    public static void main(String args[])
    {
        Text_menu menu = new Text_menu();
        menu.add_command(new Exit_command("0", "Exit."));



        My_I_dictionary<String, Type> type_environment1 = new My_dictionary<>();
        I_statement example1= new Compound_statement(new Variable_declaration_statement("v",new Int_type()),
                new Compound_statement(new Assign_statement("v",new Value_expression(new Int_value(2))),
                        new Print_statement(new Variable_expression("v"))));
        try
        {
            example1.type_check(type_environment1);
            My_I_stack<I_statement> execution_stack1 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table1 = new My_dictionary<String, Value>();
            My_I_list<Value> output1 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table1 = new My_dictionary<>();
            My_I_heap<Value> heap1 = new My_heap<>();
            Program_state program1 = new Program_state(execution_stack1, symbols_table1, output1, file_table1, heap1, example1);
            I_repository repository1 = new Repository(program1, "log1.txt");
            Controller controller1 = new Controller(repository1);
            menu.add_command(new Run_example_command("1", example1.toString(), controller1));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment2 = new My_dictionary<>();
        I_statement example2 = new Compound_statement(new Variable_declaration_statement("a", new Int_type()),
                new Compound_statement(new Variable_declaration_statement("b", new Int_type()),
                        new Compound_statement(new Assign_statement("a", new Arithmetic_expression("+", new Value_expression(new Int_value(2)),
                                new Arithmetic_expression("*", new Value_expression(new Int_value(3)), new Value_expression(new Int_value(5))))),
                                new Compound_statement(new Assign_statement("b", new Arithmetic_expression("+", new Variable_expression("a"),
                                        new Value_expression(new Int_value(1)))), new Print_statement(new Variable_expression("b"))))));
        try
        {
            example2.type_check(type_environment2);
            My_I_stack<I_statement> execution_stack2 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table2 = new My_dictionary<String, Value>();
            My_I_list<Value> output2 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table2 = new My_dictionary<>();
            My_I_heap<Value> heap2 = new My_heap<>();
            Program_state program2 = new Program_state(execution_stack2, symbols_table2, output2, file_table2, heap2, example2);
            I_repository repository2 = new Repository(program2, "log2.txt");
            Controller controller2 = new Controller(repository2);
            menu.add_command(new Run_example_command("2", example2.toString(), controller2));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment3 = new My_dictionary<>();
        I_statement example3 =  new Compound_statement(new Variable_declaration_statement("a",new Bool_type()),
                new Compound_statement(new Variable_declaration_statement("v", new Int_type()),
                        new Compound_statement(new Assign_statement("a", new Value_expression(new Bool_value(true))),
                                new Compound_statement(new If_statement(new Variable_expression("a"),new Assign_statement("v",
                                        new Value_expression(new Int_value(2))), new Assign_statement("v", new Value_expression(new Int_value(3)))),
                                        new Print_statement(new Variable_expression("v"))))));
        try
        {
            example3.type_check(type_environment3);
            My_I_stack<I_statement> execution_stack3 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table3 = new My_dictionary<String, Value>();
            My_I_list<Value> output3 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table3 = new My_dictionary<>();
            My_I_heap<Value> heap3 = new My_heap<>();
            Program_state program3 = new Program_state(execution_stack3, symbols_table3, output3, file_table3, heap3, example3);
            I_repository repository3 = new Repository(program3, "log3.txt");
            Controller controller3 = new Controller(repository3);
            menu.add_command(new Run_example_command("3", example3.toString(), controller3));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment4 = new My_dictionary<>();
        I_statement example4 = new Compound_statement(new Variable_declaration_statement("varf", new String_type()),
                new Compound_statement(new Assign_statement("varf", new Value_expression(new String_value("test.in"))),
                        new Compound_statement(new Open_read_file_statement(new Variable_expression("varf")),
                                new Compound_statement(new Variable_declaration_statement("varc", new Int_type()),
                                        new Compound_statement(new Read_file_statement(new Variable_expression("varf"), "varc"),
                                                new Compound_statement(new Print_statement(new Variable_expression("varc")),
                                                        new Compound_statement(new Read_file_statement(new Variable_expression("varf"), "varc"),
                                                            new Compound_statement(new Print_statement(new Variable_expression("varc")),
                                                                    new Close_read_file_statement(new Variable_expression("varf"))))))))));
        try
        {
            example4.type_check(type_environment4);
            My_I_stack<I_statement> execution_stack4 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table4 = new My_dictionary<String, Value>();
            My_I_list<Value> output4 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table4 = new My_dictionary<>();
            My_I_heap<Value> heap4 = new My_heap<>();
            Program_state program4 = new Program_state(execution_stack4, symbols_table4, output4, file_table4, heap4, example4);
            I_repository repository4 = new Repository(program4, "log4.txt");
            Controller controller4 = new Controller(repository4);
            menu.add_command(new Run_example_command("4", example4.toString(), controller4));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment5 = new My_dictionary<>();
        I_statement example5 = new Compound_statement(new Variable_declaration_statement("a", new Int_type()),
                new Compound_statement(new Assign_statement("a", new Value_expression(new Int_value(10))),
                        new Compound_statement(new Variable_declaration_statement("b", new Int_type()),
                                new Compound_statement(new Assign_statement("b", new Value_expression(new Int_value(7))),
                                        new Compound_statement(new Variable_declaration_statement("s", new String_type()),
                                                new Compound_statement(new Assign_statement("s", new Value_expression(new String_value("else"))),
                                                        new If_statement(new Relational_expression("<", new Variable_expression("a"), new Variable_expression("b")),
                                                                new Print_statement(new Value_expression(new String_value("if"))), new Print_statement(new Variable_expression("s")))))))));
        try
        {
            example5.type_check(type_environment5);
            My_I_stack<I_statement> execution_stack5 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table5 = new My_dictionary<String, Value>();
            My_I_list<Value> output5 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table5 = new My_dictionary<>();
            My_I_heap<Value> heap5 = new My_heap<>();
            Program_state program5 = new Program_state(execution_stack5, symbols_table5, output5, file_table5, heap5, example5);
            I_repository repository5 = new Repository(program5, "log5.txt");
            Controller controller5 = new Controller(repository5);
            menu.add_command(new Run_example_command("5", example5.toString(), controller5));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment6 = new My_dictionary<>();
        I_statement example6 = new Compound_statement(new Variable_declaration_statement("v", new Int_type()),
                new Compound_statement(new Assign_statement("v", new Value_expression(new Int_value(4))),
                        new Compound_statement(new While_statement(new Relational_expression(">", new Variable_expression("v"),
                                new Value_expression(new Int_value(0))), new Compound_statement(new Print_statement(new Variable_expression("v")),
                                new Assign_statement("v", new Arithmetic_expression("-", new Variable_expression("v"), new Value_expression(new Int_value(1)))))),
                                new Print_statement(new Variable_expression("v")))));
        try
        {
            example6.type_check(type_environment6);
            My_I_stack<I_statement> execution_stack6 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table6 = new My_dictionary<String, Value>();
            My_I_list<Value> output6 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table6 = new My_dictionary<>();
            My_I_heap<Value> heap6 = new My_heap<>();
            Program_state program6 = new Program_state(execution_stack6, symbols_table6, output6, file_table6, heap6, example6);
            I_repository repository6 = new Repository(program6, "log6.txt");
            Controller controller6 = new Controller(repository6);
            menu.add_command(new Run_example_command("6", example6.toString(), controller6));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment7 = new My_dictionary<>();
        I_statement example7 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Variable_declaration_statement("a", new Reference_type(new Reference_type(new Int_type()))),
                                new Compound_statement(new New_statement("a", new Variable_expression("v")),
                                        new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                                                new Print_statement(new Arithmetic_expression("+", new Read_heap_expression(new Read_heap_expression(new Variable_expression("a"))),
                                                        new Value_expression(new Int_value(5)))))))));
        try
        {
            example7.type_check(type_environment7);
            My_I_stack<I_statement> execution_stack7 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table7 = new My_dictionary<String, Value>();
            My_I_list<Value> output7 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table7 = new My_dictionary<>();
            My_I_heap<Value> heap7 = new My_heap<>();
            Program_state program7 = new Program_state(execution_stack7, symbols_table7, output7, file_table7, heap7, example7);
            I_repository repository7 = new Repository(program7, "log7.txt");
            Controller controller7 = new Controller(repository7);
            menu.add_command(new Run_example_command("7", example7.toString(), controller7));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment8 = new My_dictionary<>();
        I_statement example8 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                            new Compound_statement(new Write_heap_statement("v", new Value_expression(new Int_value(30))),
                                    new Print_statement(new Arithmetic_expression("+", new Read_heap_expression(new Variable_expression("v")), new Value_expression(new Int_value(5))))))));
        try
        {
            example8.type_check(type_environment8);
            My_I_stack<I_statement> execution_stack8 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table8 = new My_dictionary<String, Value>();
            My_I_list<Value> output8 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table8 = new My_dictionary<>();
            My_I_heap<Value> heap8 = new My_heap<>();
            Program_state program8 = new Program_state(execution_stack8, symbols_table8, output8, file_table8, heap8, example8);
            I_repository repository8 = new Repository(program8, "log8.txt");
            Controller controller8 = new Controller(repository8);
            menu.add_command(new Run_example_command("8", example8.toString(), controller8));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment9 = new My_dictionary<>();
        I_statement example9 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Variable_declaration_statement("a", new Reference_type(new Reference_type(new Int_type()))),
                                new Compound_statement(new New_statement("a", new Variable_expression("v")),
                                        new Compound_statement(new New_statement("v", new Value_expression(new Int_value(30))),
                                               new Compound_statement(new New_statement("v", new Value_expression(new Int_value(40))),
                                                       new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                                                               new Print_statement(new Read_heap_expression(new Read_heap_expression(new Variable_expression("a")))))))))));
        try
        {
            example9.type_check(type_environment9);
            My_I_stack<I_statement> execution_stack9 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table9 = new My_dictionary<String, Value>();
            My_I_list<Value> output9 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table9 = new My_dictionary<>();
            My_I_heap<Value> heap9 = new My_heap<>();
            Program_state program9 = new Program_state(execution_stack9, symbols_table9, output9, file_table9, heap9, example9);
            I_repository repository9 = new Repository(program9, "log9.txt");
            Controller controller9 = new Controller(repository9);
            menu.add_command(new Run_example_command("9", example9.toString(), controller9));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }



        My_I_dictionary<String, Type> type_environment10 = new My_dictionary<>();
        I_statement example10 = new Compound_statement(new Variable_declaration_statement("v", new Int_type()),
                new Compound_statement(new Variable_declaration_statement("a", new Reference_type(new Int_type())),
                        new Compound_statement(new Assign_statement("v", new Value_expression(new Int_value(10))),
                                new Compound_statement(new New_statement("a", new Value_expression(new Int_value(22))),
                                        new Compound_statement(
                                                new Fork_statement(new Compound_statement(new Write_heap_statement("a", new Value_expression(new Int_value(30))),
                                                        new Compound_statement(new Assign_statement("v", new Value_expression(new Int_value(32))),
                                                                new Compound_statement(new Print_statement(new Variable_expression("v")),
                                                                        new Print_statement(new Read_heap_expression(new Variable_expression("a"))))))),
                                                new Compound_statement(new Print_statement(new Variable_expression("v")),
                                                        new Print_statement(new Read_heap_expression(new Variable_expression("a")))))))));
        try
        {
            example10.type_check(type_environment10);
            My_I_stack<I_statement> execution_stack10 = new My_stack<I_statement>();
            My_I_dictionary<String, Value> symbols_table10 = new My_dictionary<String, Value>();
            My_I_list<Value> output10 = new My_list<Value>();
            My_I_dictionary<String_value, BufferedReader> file_table10 = new My_dictionary<>();
            My_I_heap<Value> heap10 = new My_heap<>();
            Program_state program10 = new Program_state(execution_stack10, symbols_table10, output10, file_table10, heap10, example10);
            I_repository repository10 = new Repository(program10, "log10.txt");
            Controller controller10 = new Controller(repository10);
            menu.add_command(new Run_example_command("10", example10.toString(), controller10));
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }

        //type_check testing
        My_I_dictionary<String, Type> type_environment11 = new My_dictionary<>();
        I_statement example11 = new While_statement(new Value_expression(new String_value("true")), new No_operation_statement());
        try
        {
            example11.type_check(type_environment11);
        }
        catch(MyException exception)
        {
            System.out.println(exception.getMessage());
        }

        menu.show();
    }
}
