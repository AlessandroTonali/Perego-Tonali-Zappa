package it.polimi.ingsw.GC_23.FX;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by jesss on 19/06/17.
 */
public class Gameboard {
    private Stage primaryStage;

    public void startLogin(){
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
