package sample;

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
import Repository.I_repository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Select_form_controller implements Initializable
{
    private List<I_statement> programs;
    private Run_form_controller run_window_controller;
    @FXML
    private ListView<String> select_programs_list_view;
    @FXML
    private Button select_execute_button;

    public void set_run_window_controller(Run_form_controller run_window_controller)
    {
        this.run_window_controller = run_window_controller;
    }

    private void create_programs_list()
    {
        this.programs = new ArrayList<>();

        My_I_dictionary<String, Type> type_environment1 = new My_dictionary<>();
        I_statement example1= new Compound_statement(new Variable_declaration_statement("v",new Int_type()),
                new Compound_statement(new Assign_statement("v",new Value_expression(new Int_value(2))),
                        new Print_statement(new Variable_expression("v"))));
        My_I_dictionary<String, Type> type_environment2 = new My_dictionary<>();
        I_statement example2 = new Compound_statement(new Variable_declaration_statement("a", new Int_type()),
                new Compound_statement(new Variable_declaration_statement("b", new Int_type()),
                        new Compound_statement(new Assign_statement("a", new Arithmetic_expression("+", new Value_expression(new Int_value(2)),
                                new Arithmetic_expression("*", new Value_expression(new Int_value(3)), new Value_expression(new Int_value(5))))),
                                new Compound_statement(new Assign_statement("b", new Arithmetic_expression("+", new Variable_expression("a"),
                                        new Value_expression(new Int_value(1)))), new Print_statement(new Variable_expression("b"))))));
        My_I_dictionary<String, Type> type_environment3 = new My_dictionary<>();
        I_statement example3 =  new Compound_statement(new Variable_declaration_statement("a",new Bool_type()),
                new Compound_statement(new Variable_declaration_statement("v", new Int_type()),
                        new Compound_statement(new Assign_statement("a", new Value_expression(new Bool_value(true))),
                                new Compound_statement(new If_statement(new Variable_expression("a"),new Assign_statement("v",
                                        new Value_expression(new Int_value(2))), new Assign_statement("v", new Value_expression(new Int_value(3)))),
                                        new Print_statement(new Variable_expression("v"))))));
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
        My_I_dictionary<String, Type> type_environment5 = new My_dictionary<>();
        I_statement example5 = new Compound_statement(new Variable_declaration_statement("a", new Int_type()),
                new Compound_statement(new Assign_statement("a", new Value_expression(new Int_value(10))),
                        new Compound_statement(new Variable_declaration_statement("b", new Int_type()),
                                new Compound_statement(new Assign_statement("b", new Value_expression(new Int_value(7))),
                                        new Compound_statement(new Variable_declaration_statement("s", new String_type()),
                                                new Compound_statement(new Assign_statement("s", new Value_expression(new String_value("else"))),
                                                        new If_statement(new Relational_expression("<", new Variable_expression("a"), new Variable_expression("b")),
                                                                new Print_statement(new Value_expression(new String_value("if"))), new Print_statement(new Variable_expression("s")))))))));
        My_I_dictionary<String, Type> type_environment6 = new My_dictionary<>();
        I_statement example6 = new Compound_statement(new Variable_declaration_statement("v", new Int_type()),
                new Compound_statement(new Assign_statement("v", new Value_expression(new Int_value(4))),
                        new Compound_statement(new While_statement(new Relational_expression(">", new Variable_expression("v"),
                                new Value_expression(new Int_value(0))), new Compound_statement(new Print_statement(new Variable_expression("v")),
                                new Assign_statement("v", new Arithmetic_expression("-", new Variable_expression("v"), new Value_expression(new Int_value(1)))))),
                                new Print_statement(new Variable_expression("v")))));
        My_I_dictionary<String, Type> type_environment7 = new My_dictionary<>();
        I_statement example7 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Variable_declaration_statement("a", new Reference_type(new Reference_type(new Int_type()))),
                                new Compound_statement(new New_statement("a", new Variable_expression("v")),
                                        new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                                                new Print_statement(new Arithmetic_expression("+", new Read_heap_expression(new Read_heap_expression(new Variable_expression("a"))),
                                                        new Value_expression(new Int_value(5)))))))));
        My_I_dictionary<String, Type> type_environment8 = new My_dictionary<>();
        I_statement example8 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                                new Compound_statement(new Write_heap_statement("v", new Value_expression(new Int_value(30))),
                                        new Print_statement(new Arithmetic_expression("+", new Read_heap_expression(new Variable_expression("v")), new Value_expression(new Int_value(5))))))));
        My_I_dictionary<String, Type> type_environment9 = new My_dictionary<>();
        I_statement example9 = new Compound_statement(new Variable_declaration_statement("v", new Reference_type(new Int_type())),
                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(20))),
                        new Compound_statement(new Variable_declaration_statement("a", new Reference_type(new Reference_type(new Int_type()))),
                                new Compound_statement(new New_statement("a", new Variable_expression("v")),
                                        new Compound_statement(new New_statement("v", new Value_expression(new Int_value(30))),
                                                new Compound_statement(new New_statement("v", new Value_expression(new Int_value(40))),
                                                        new Compound_statement(new Print_statement(new Read_heap_expression(new Variable_expression("v"))),
                                                                new Print_statement(new Read_heap_expression(new Read_heap_expression(new Variable_expression("a")))))))))));
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
        //type_check testing
        My_I_dictionary<String, Type> type_environment11 = new My_dictionary<>();
        I_statement example11 = new While_statement(new Value_expression(new String_value("true")), new No_operation_statement());

        My_I_dictionary<String, Type> type_environment12 = new My_dictionary<>();
        I_statement problem1 = new Compound_statement(new Variable_declaration_statement("a", new Int_type()),
                new Compound_statement(new Variable_declaration_statement("b", new Int_type()),
                        new Compound_statement(new Variable_declaration_statement("c", new Int_type()),
                                new Compound_statement(new Assign_statement("a", new Value_expression(new Int_value(1))),
                                        new Compound_statement(new Assign_statement("b", new Value_expression(new Int_value(2))),
                                                new Compound_statement(new Assign_statement("c", new Value_expression(new Int_value(5))),
                                                        new Compound_statement(new Switch_statement(
                                                                new Arithmetic_expression("*", new Variable_expression("a"), new Value_expression(new Int_value(10))),
                                                                new Arithmetic_expression("*", new Variable_expression("b"), new Variable_expression("c")),
                                                                new Compound_statement(new Print_statement(new Variable_expression("a")), new Print_statement(new Variable_expression("b"))),
                                                                new Value_expression(new Int_value(10)),
                                                                new Compound_statement(new Print_statement(new Value_expression(new Int_value(100))), new Print_statement(new Value_expression(new Int_value(200)))),
                                                                new Print_statement(new Value_expression(new Int_value(300)))
                                                                ),
                                                                new Print_statement(new Value_expression(new Int_value(300)))
                                                        )))))));

        try
        {
            example1.type_check(type_environment1);
            programs.add(example1);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example2.type_check(type_environment2);
            programs.add(example2);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example3.type_check(type_environment3);
            programs.add(example3);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example4.type_check(type_environment4);
            programs.add(example4);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example5.type_check(type_environment5);
            programs.add(example5);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example6.type_check(type_environment6);
            programs.add(example6);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example7.type_check(type_environment7);
            programs.add(example7);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example8.type_check(type_environment8);
            programs.add(example8);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example9.type_check(type_environment9);
            programs.add(example9);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example10.type_check(type_environment10);
            programs.add(example10);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            example11.type_check(type_environment11);
            programs.add(example11);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        try
        {
            problem1.type_check(type_environment12);
            programs.add(problem1);
        } catch (MyException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        create_programs_list();
        select_programs_list_view.setItems(FXCollections.observableArrayList(programs.stream().map(I_statement::toString).collect(Collectors.toList())));

        select_execute_button.setOnAction(action_event -> {
            int index = select_programs_list_view.getSelectionModel().getSelectedIndex();
            if(index < 0)
            {
                return;
            }

            Program_state program_state = new Program_state(new My_stack<>(), new My_dictionary<>(), new My_list<>(), new My_dictionary<>(), new My_heap<>(), programs.get(index));
            I_repository repository = new Repository(program_state, "log" + (index + 1) + ".txt");
            Controller controller = new Controller(repository);

            run_window_controller.set_controller(controller);

            programs.remove(index);
            select_programs_list_view.setItems(FXCollections.observableArrayList(programs.stream().map(I_statement::toString).collect(Collectors.toList())));
        });
    }
}
