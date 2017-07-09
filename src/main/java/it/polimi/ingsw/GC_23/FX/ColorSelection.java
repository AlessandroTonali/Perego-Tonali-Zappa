package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 04/07/17.
 */
public class ColorSelection implements Serializable {
    private Stage primaryStage;
    private UserFX userFX;
    private ColorController colorController;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ColorSelection(Stage primaryStage, Login login)  throws RemoteException{
        this.primaryStage = primaryStage;
        this.userFX = login.getUserFX();
    }

    public void startColorSelection() throws RemoteException{
        primaryStage.setTitle("Color selection");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("colorchoice.fxml"));
        colorController = new ColorController(userFX, primaryStage);
        loader.setController(colorController);
        Parent content = null;
        try {
            content = loader.load();
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
        primaryStage.setScene(new Scene(content));
        primaryStage.show();
        fill();
        colorController.handleColor();
    }

    public void fill() throws RemoteException{
        while (!userFX.getUser().isMatchStarted()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        colorController.setLabel();
    }

    public UserFX getUserFX() {
        return userFX;
    }
}
