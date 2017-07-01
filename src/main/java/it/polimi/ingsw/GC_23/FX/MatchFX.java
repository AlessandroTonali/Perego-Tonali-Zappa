package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.PlayerController;
import it.polimi.ingsw.GC_23.Connection.UserHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 28/06/17.
 */
public class MatchFX extends Application implements Runnable{
    public static ArrayList<UserHandler> userHandlers;
    private Gameboard gameboard;
    public static PlayerController playerController;
    private Stage primaryStage;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        //new WaitingRoom(primaryStage, playerController);
        //Gameboard gameboard = new Gameboard();
        //new Gameboard().startGameBoard(primaryStage);
    }

    @Override
    public void stop() throws Exception{
        System.out.println("MatchFX stopped");
    }

    public void setup(){
        try {
            replaceSceneContent("setup.fxml");
        }catch (Exception e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
        for(UserHandler u: userHandlers){

        }
    }

    public void addUserHanlder(UserHandler userHandler) {
        userHandlers.add(userHandler);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(MatchFX.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(page);
        }
        primaryStage.sizeToScene();
        return page;
    }
}
