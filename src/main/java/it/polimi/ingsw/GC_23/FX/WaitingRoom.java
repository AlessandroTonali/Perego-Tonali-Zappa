package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 27/06/17.
 */
public class WaitingRoom {
    private Stage primaryStage;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WaitingRoom(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Waiting Room");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("waitingroom.fxml"));
        Parent content = null;
        try {
            content = loader.load();
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
        this.primaryStage.setScene(new Scene(content));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        this.primaryStage.setWidth(bounds.getWidth());
        this.primaryStage.setHeight(bounds.getHeight());
        this.primaryStage.show();
    }

    public void startGame(){
        new Gameboard().startGameBoard(primaryStage);

    }
}
