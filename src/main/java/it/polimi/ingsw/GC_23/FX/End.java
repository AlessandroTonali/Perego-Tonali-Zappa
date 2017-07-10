package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 10/07/17.
 */
public class End {
    private Stage primaryStage;
    private UserFX userFX;
    private transient final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public End(Stage primaryStage, UserFX userFX) {
        this.primaryStage = primaryStage;
        this.userFX = userFX;
    }

    public void startEnd(Stage primaryStage) throws RemoteException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The end");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("end.fxml"));
        Parent content = null;
        try {
            content = loader.load();
        } catch (IOException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
        primaryStage.setScene(new Scene(content));
        primaryStage.show();
    }
}
