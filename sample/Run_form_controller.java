package sample;

import Controller.Controller;
import Domain.Collections.My_I_dictionary;
import Domain.Collections.My_I_heap;
import Domain.Collections.My_I_list;
import Domain.Collections.My_I_stack;
import Domain.MyException;
import Domain.Program_state;
import Domain.Statements.I_statement;
import Domain.Values.String_value;
import Domain.Values.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Run_form_controller implements Initializable
{
    private Controller controller;

    @FXML
    private TextField main_text_field_nr_program_states;

    @FXML
    private TableView<Map.Entry<Integer, String>> main_table_view_heap_table;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> main_heap_table_column_address;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> main_heap_table_column_value;

    @FXML
    private ListView<String> main_list_view_output;

    @FXML
    private ListView<String> main_list_view_file_table;

    @FXML
    private ListView<String> main_list_view_program_states_ids;

    @FXML
    private ListView<String> main_list_view_execution_stack;

    @FXML
    private TableView<Map.Entry<String, String>> main_table_view_symbols_table;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> main_symbols_table_column_variable_name;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> main_symbols_table_column_value;

    @FXML
    private Button main_button_run_one_step;

    public void set_controller(Controller controller)
    {
        this.controller = controller;
        populate_program_states_IDs();
    }

    private void populate_program_states_IDs()
    {
        List<Program_state> program_states = controller.get_repository().get_program_states();
        main_list_view_program_states_ids.setItems(FXCollections.observableList(program_states.stream().map(Program_state::get_current_state_id).map(String::valueOf).collect(Collectors.toList())));
        main_text_field_nr_program_states.setText(String.valueOf(program_states.size()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        main_heap_table_column_address.setCellValueFactory(x -> new SimpleIntegerProperty(x.getValue().getKey()).asObject());
        main_heap_table_column_value.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getValue()));

        main_symbols_table_column_value.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getValue()));
        main_symbols_table_column_variable_name.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getKey()));

        main_list_view_program_states_ids.setOnMouseClicked(mouse_event -> {
            change_program_state(get_current_program_state());
        });

        main_button_run_one_step.setOnAction(action_event -> {
            execute_one_step();
        });
    }

    private void execute_one_step()
    {
        Program_state current_program_state = get_current_program_state();
        if(controller == null)
        {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setContentText("No program was selected!");
            error_alert.showAndWait();
        }

        if(controller.get_repository().get_program_states().isEmpty())
        {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setContentText("The program has finished!");
            error_alert.showAndWait();
        }

        try
        {
            controller.all_step();
        }
        catch(MyException e)
        {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setContentText(e.getMessage());
            error_alert.showAndWait();
        }

        change_program_state(current_program_state);
        populate_program_states_IDs();
    }

    private void change_program_state(Program_state program_state)
    {
        if(program_state == null)
            return;
        populate_execution_stack(program_state.get_execution_stack());
        populate_symbols_table(program_state.get_symbols_table());
        populate_heap_table(program_state.get_heap());
        populate_output(program_state.get_output());
        populate_file_table(program_state.get_file_table());
    }

    private void populate_file_table(My_I_dictionary<String_value, BufferedReader> file_table)
    {
        main_list_view_file_table.setItems(FXCollections.observableList(file_table.get_pairs_set().stream().map(Map.Entry::toString).collect(Collectors.toList())));
        main_list_view_file_table.refresh();
    }

    private void populate_output(My_I_list<Value> output)
    {
        main_list_view_output.setItems(FXCollections.observableList(output.get_content_list().stream().map(Value::toString).collect(Collectors.toList())));
        main_list_view_output.refresh();
    }

    private void populate_heap_table(My_I_heap<Value> heap)
    {
        main_table_view_heap_table.setItems(FXCollections.observableList(heap.get_pairs_set().stream().map(pair -> new AbstractMap.SimpleEntry<Integer, String>(pair.getKey(), pair.getValue().toString())).collect(Collectors.toList())));
        main_table_view_heap_table.refresh();
    }

    private void populate_symbols_table(My_I_dictionary<String, Value> symbols_table)
    {
        main_table_view_symbols_table.setItems(FXCollections.observableList(symbols_table.get_pairs_set().stream().map(pair -> new AbstractMap.SimpleEntry<String, String>(pair.getKey(), pair.getValue().toString())).collect(Collectors.toList())));
        main_table_view_symbols_table.refresh();
    }

    private void populate_execution_stack(My_I_stack<I_statement> execution_stack)
    {
        main_list_view_execution_stack.setItems(FXCollections.observableList(execution_stack.get_all_elements().stream().map(I_statement::toString).collect(Collectors.toList())));
        main_list_view_execution_stack.refresh();

    }

    private Program_state get_current_program_state()
    {
        int index = main_list_view_program_states_ids.getSelectionModel().getSelectedIndex();
        if(index < 0)
            return null;

        return controller.get_repository().get_program_states().get(index);
    }
}
