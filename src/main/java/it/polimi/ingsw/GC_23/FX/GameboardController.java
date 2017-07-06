package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by jesss on 21/06/17.
 */
public class GameboardController implements Serializable {
    private UserFX userFX;
    private String board;

    public GameboardController(UserFX userFX) {
        this.userFX = userFX;
    }

    @FXML
    private Button territory1;

    @FXML
    private Button territory2;

    @FXML
    private Button territory3;

    @FXML
    private Button territory4;

    @FXML
    private Button character1;

    @FXML
    private Button character2;

    @FXML
    private Button character3;

    @FXML
    private Button character4;

    @FXML
    private Button venture1;

    @FXML
    private Button venture2;

    @FXML
    private Button venture3;

    @FXML
    private Button venture4;

    @FXML
    private Button building1;

    @FXML
    private Button building2;

    @FXML
    private Button building3;

    @FXML
    private Button building4;



    @FXML
    private Pane territorycard1;

    @FXML
    private Pane territorycard2;

    @FXML
    private Pane territorycard3;

    @FXML
    private Pane territorycard4;

    @FXML
    private Pane charactercard1;

    @FXML
    private Pane charactercard2;

    @FXML
    private Pane charactercard3;

    @FXML
    private Pane charactercard4;

    @FXML
    private Pane buildingcard1;

    @FXML
    private Pane buildingcard2;

    @FXML
    private Pane buildingcard3;

    @FXML
    private Pane buildingcard4;

    @FXML
    private Pane venturecard1;

    @FXML
    private Pane venturecard2;

    @FXML
    private Pane venturecard3;

    @FXML
    private Pane venturecard4;

    public void boardTranslator() throws RemoteException{
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String actualString = userFX.receive();
            towerSetter();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void towerSetter() throws RemoteException{
        String inizio;
        String fine;
        String ind;
        String actualString;
        actualString = userFX.receive();
        inizio = "-fx-background-image: url";
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard4.setStyle(ind);
    }
}


