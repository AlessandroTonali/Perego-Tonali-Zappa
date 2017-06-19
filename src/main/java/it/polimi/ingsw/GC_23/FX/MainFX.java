package it.polimi.ingsw.GC_23.FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by jesss on 16/06/17.
 */
public class MainFX extends Application {
    public static void main(String args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Login login = new Login();
        login.startLogin();
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));
        //Parent content = loader.load();
        //Scene scene = new Scene(content, 600, 400);

        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
}
