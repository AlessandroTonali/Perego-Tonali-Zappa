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

    public ColorSelection(Stage primaryStage, Login login)  throws RemoteException{
        this.primaryStage = primaryStage;
        this.userFX = login.getUserFX();
    }

    public void startColorSelection() throws RemoteException{
        primaryStage.setTitle("Color selection");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getClassLoader().getResource("colorchoice.fxml"));
        colorController = new ColorController(userFX);
        loader.setController(colorController);
        Parent content = null;
        try {
            content = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(content));
        primaryStage.show();
        fill();
        colorController.handle();
        //Gameboard gameboard = new Gameboard();
        //gameboard.startGameBoard(primaryStage);
    }

    public void fill() throws RemoteException{
        while (!userFX.getUser().isMatchStarted()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        colorController.setLabel();
    }
}
