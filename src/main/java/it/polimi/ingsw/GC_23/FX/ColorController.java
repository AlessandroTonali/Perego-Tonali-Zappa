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

import javax.swing.text.TabableView;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by jesss on 03/07/17.
 */
public class ColorController implements Serializable{
    private UserFX userFX;
    private int votation = 0;

    public ColorController(UserFX userFX) {
        this.userFX = userFX;
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
            e.printStackTrace();
        }
    }

    public void handle() throws RemoteException{
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
                if(selectedToggle.equals(red) && redlabel.getText().equals("null")) {
                    try {
                        userFX.send("RED");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else if(selectedToggle.equals(blue) && bluelabel.getText().equals("null")) {
                    try {
                        userFX.send("BLUE");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else if(selectedToggle.equals(green) && greenlabel.getText().equals("null")) {
                    try {
                        userFX.send("GREEN");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else if(selectedToggle.equals(yellow) && yellowlabel.getText().equals("null")){
                    try {
                        userFX.send("YELLOW");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid choice: color already chosen!");
                    alert.setContentText("Please select a valid choice");
                    alert.showAndWait();
                }
                try {
                    userFX.send(Integer.toString(votation));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
