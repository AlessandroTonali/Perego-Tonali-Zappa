package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by jesss on 21/06/17.
 */
public class GameboardController implements Serializable {
    private UserFX userFX;

    public GameboardController(UserFX userFX) {
        this.userFX = userFX;
    }

    public void boardTranslator() throws RemoteException{
        try {
            String actualString = userFX.receive();
            towerSetter();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void dataTranslator() throws RemoteException{
        dataSetter();
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

    public void dataSetter() throws RemoteException{
        String actualString = userFX.receive();
        switch (actualString) {
            case "RED":
                circle1.setStyle("-fx-background-color: RED");
                break;
            case "BLUE":
                circle1.setStyle("-fx-background-color: BLUE");
                break;
            case "GREEN":
                circle1.setStyle("-fx-background-color: GREEN");
            case "YELLOW":
                circle1.setStyle("-fx-background-color: YELLOW");
        }
        actualString = userFX.receive();
        coin1.setText(actualString);
        actualString = userFX.receive();
        stone1.setText(actualString);
        actualString = userFX.receive();
        wood1.setText(actualString);
        actualString = userFX.receive();
        servant1.setText(actualString);
        actualString = userFX.receive();
        faith1.setText(actualString);
        actualString = userFX.receive();
        military1.setText(actualString);
        actualString = userFX.receive();
        victory1.setText(actualString);

        actualString = userFX.receive();
        switch (actualString) {
            case "RED":
                circle2.setStyle("-fx-background-color: RED");
                break;
            case "BLUE":
                circle2.setStyle("-fx-background-color: BLUE");
                break;
            case "YELLOW":
                circle2.setStyle("-fx-background-color: YELLOW");
            case "GREEN":
                circle2.setStyle("-fx-background-color: GREEN");
        }
        actualString = userFX.receive();
        coin2.setText(actualString);
        actualString = userFX.receive();
        stone2.setText(actualString);
        actualString = userFX.receive();
        wood2.setText(actualString);
        actualString = userFX.receive();
        servant2.setText(actualString);
        actualString = userFX.receive();
        faith2.setText(actualString);
        actualString = userFX.receive();
        military2.setText(actualString);
        System.out.println("prima");
        actualString = userFX.receive();
        System.out.println("dopo");
        victory2.setText(actualString);

        actualString = userFX.receive();
        if(!actualString.equals("endResources")) {
            switch (actualString) {
                case "RED":
                    circle3.setStyle("-fx-background-color: RED");
                    break;
                case "YELLOW":
                    circle3.setStyle("-fx-background-color: YELLOW");
                case "BLUE":
                    circle3.setStyle("-fx-background-color: BLUE");
                    break;
                case "GREEN":
                    circle3.setStyle("-fx-background-color: GREEN");
            }
            actualString = userFX.receive();
            coin3.setText(actualString);
            actualString = userFX.receive();
            stone3.setText(actualString);
            actualString = userFX.receive();
            wood3.setText(actualString);
            actualString = userFX.receive();
            servant3.setText(actualString);
            actualString = userFX.receive();
            faith3.setText(actualString);
            actualString = userFX.receive();
            military3.setText(actualString);
            actualString = userFX.receive();
            victory3.setText(actualString);

            actualString = userFX.receive();
            if(!actualString.equals("endResources")) {
                switch (actualString) {
                    case "GREEN":
                        circle4.setStyle("-fx-background-color: GREEN");
                    case "RED":
                        circle4.setStyle("-fx-background-color: RED");
                        break;
                    case "YELLOW":
                        circle4.setStyle("-fx-background-color: YELLOW");
                    case "BLUE":
                        circle4.setStyle("-fx-background-color: BLUE");
                        break;
                }
                actualString = userFX.receive();
                coin4.setText(actualString);
                actualString = userFX.receive();
                stone4.setText(actualString);
                actualString = userFX.receive();
                wood4.setText(actualString);
                actualString = userFX.receive();
                servant4.setText(actualString);
                actualString = userFX.receive();
                faith4.setText(actualString);
                actualString = userFX.receive();
                military4.setText(actualString);
                actualString = userFX.receive();
                victory4.setText(actualString);
            }
        }
        actualString = userFX.receive();
        orange.setText(actualString);
        actualString = userFX.receive();
        white.setText(actualString);
        actualString = userFX.receive();
        black.setText(actualString);
        actualString = userFX.receive();
        neutral.setText(actualString);
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



    @FXML
    private Label coin1;

    @FXML
    private Label stone1;

    @FXML
    private Label wood1;

    @FXML
    private Label servant1;

    @FXML
    private Label faith1;

    @FXML
    private Label military1;

    @FXML
    private Label victory1;

    @FXML
    private Label coin2;

    @FXML
    private Label stone2;

    @FXML
    private Label wood2;

    @FXML
    private Label servant2;

    @FXML
    private Label faith2;

    @FXML
    private Label military2;

    @FXML
    private Label victory2;

    @FXML
    private Label coin3;

    @FXML
    private Label stone3;

    @FXML
    private Label wood3;

    @FXML
    private Label servant3;

    @FXML
    private Label faith3;

    @FXML
    private Label military3;

    @FXML
    private Label victory3;

    @FXML
    private Label coin4;

    @FXML
    private Label stone4;

    @FXML
    private Label wood4;

    @FXML
    private Label servant4;

    @FXML
    private Label faith4;

    @FXML
    private Label military4;

    @FXML
    private Label victory4;

    @FXML
    private Label black;

    @FXML
    private Label white;

    @FXML
    private Label orange;

    @FXML
    private Label neutral;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Circle circle4;

    @FXML
    private Circle first;

    @FXML
    private Circle second;

    @FXML
    private Circle third;

    @FXML
    private Circle fourth;


}


