package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader main_loader = new FXMLLoader();
        main_loader.setLocation(getClass().getResource("main_window_form.fxml"));
        Parent main_window = main_loader.load();

        Run_form_controller run_window_controller = main_loader.getController();

        primaryStage.setTitle("Run Window");
        primaryStage.setScene(new Scene(main_window, 1000, 750));
        primaryStage.show();

        FXMLLoader secondary_loader = new FXMLLoader();
        secondary_loader.setLocation(getClass().getResource("select_window_form.fxml"));
        Parent secondary_window = secondary_loader.load();
        Select_form_controller select_window_controller = secondary_loader.getController();
        select_window_controller.set_run_window_controller(run_window_controller);

        Stage secondary_stage = new Stage();
        secondary_stage.setTitle("Select Window");
        secondary_stage.setScene(new Scene(secondary_window, 750, 400));
        secondary_stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
