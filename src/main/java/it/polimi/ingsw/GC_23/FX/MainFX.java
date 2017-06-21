package it.polimi.ingsw.GC_23.FX;

import javafx.application.Application;
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
    }
}
