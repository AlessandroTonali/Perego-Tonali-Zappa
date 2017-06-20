package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by jesss on 19/06/17.
 */
public class Gameboard {
    private Stage primaryStage;

    public void startGameBoard(Stage primaryStage){
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gameboard.fxml"));
        Parent content = null;
        try {
            content = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(content, 600, 800));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }
}
