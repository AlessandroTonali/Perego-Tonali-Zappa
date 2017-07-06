package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.User;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import it.polimi.ingsw.GC_23.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 19/06/17.
 */
public class Gameboard implements Serializable {
    private Stage primaryStage;
    private UserFX userFX;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Gameboard(Stage primaryStage, UserFX userFX) {
        this.primaryStage = primaryStage;
        this.userFX = userFX;
    }

    public void startGameBoard(Stage primaryStage) throws RemoteException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lorenzo Il Magnifico");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("gameboard.fxml"));
        GameboardController gameboardController =new GameboardController(userFX);
        loader.setController(gameboardController);
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
        gameboardController.boardTranslator();
        //gameboardController.dataTranslator();
    }
}
