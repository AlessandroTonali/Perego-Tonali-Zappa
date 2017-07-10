package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.PlayerController;
import it.polimi.ingsw.GC_23.Enumerations.PlayerColor;
import it.polimi.ingsw.GC_23.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jesss on 03/07/17.
 */
public class ColorController implements Serializable{
    private UserFX userFX;
    private Stage primaryStage;
    private int votation = 0;
    private String color;
    private transient final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ColorController(UserFX userFX, Stage primaryStage) {
        this.userFX = userFX;
        this.primaryStage = primaryStage;
    }

    @FXML
    private Label yellowlabel;

    @FXML
    private Label redlabel;

    @FXML
    private Label greenlabel;

    @FXML
    private Label bluelabel;

    @FXML
    private Button button;

    @FXML
    private ToggleGroup toggles;

    @FXML
    private RadioButton green;

    @FXML
    private RadioButton red;

    @FXML
    private RadioButton blue;

    @FXML
    private RadioButton yellow;

    @FXML
    private RadioButton basic;

    @FXML
    private RadioButton advanced;

    @FXML
    private ToggleGroup rules;

    /**
     * Set the usernames labels according to the map used in the server, in order to avoid players with the same color
     */
    public void setLabel() throws RemoteException{
        try{
            int i = 1;
            while(i<5){
                String player = userFX.receive();
                switch (player){
                    case "RED": {
                        redlabel.setText(userFX.receive());
                        break;
                    }
                    case "BLUE":{
                        bluelabel.setText(userFX.receive());
                        break;
                    }
                    case "YELLOW":{
                        yellowlabel.setText(userFX.receive());
                        break;
                    }
                    case "GREEN":{
                        greenlabel.setText(userFX.receive());
                        break;
                    }
                }
                i++;
            }
        }catch(RemoteException e){
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    /**
     * Handle the actions after pressing the button "confirm"
     * Send the chosen color and the chosen kind of rules
     */
    public void handleColor() throws RemoteException{
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Toggle selectedRules = rules.getSelectedToggle();
                if(selectedRules.equals(basic)){
                    votation--;
                }
                else{
                    votation++;
                }
                Toggle selectedToggle = toggles.getSelectedToggle();
                if(selectedToggle.equals(red) && "null".equals(redlabel.getText())) {
                    try {
                        color = "RED";
                        userFX.send("RED");
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                }
                else if(selectedToggle.equals(blue) && "null".equals(bluelabel.getText())) {
                    try {
                        color = "BLUE";
                        userFX.send("BLUE");
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                }
                else if(selectedToggle.equals(green) && "null".equals(greenlabel.getText())) {
                    try {
                        color = "GREEN";
                        userFX.send("GREEN");
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                }
                else if(selectedToggle.equals(yellow) && "null".equals(yellowlabel.getText())){
                    try {
                        color = "YELLOW";
                        userFX.send("YELLOW");
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid choice: color already chosen!");
                    alert.setContentText("Please select a valid choice");
                    alert.showAndWait();
                    try {
                        handleColor();
                    } catch (RemoteException e) {
                        logger.setLevel(Level.SEVERE);
                        logger.severe(String.valueOf(e));
                    }
                    return;
                }
                try {
                    userFX.send(Integer.toString(votation));
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
                try {
                    startGameboard();
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });
    }

    /**
     * Starts the gameboard
     */
    public void startGameboard() throws RemoteException{
        Gameboard gameboard = new Gameboard(primaryStage, userFX, color);
        gameboard.startGameBoard(primaryStage);
    }
}
