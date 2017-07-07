package it.polimi.ingsw.GC_23.FX;

import com.sun.prism.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.rmi.RemoteException;

import static javafx.scene.paint.Color.*;

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
            marketSetter();
            harvestSetter();
            productionSetter();
            councilSetter();
            excomSetter();
            actualString = userFX.receive();
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
        String fine2;
        String ind;
        String actualString;
        actualString = userFX.receive();
        inizio = "-fx-background-image: url";
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        territory1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        territory2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        territory3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        territorycard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        territory4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        character1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        character2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        character3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        charactercard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        character4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        building1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        building2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        building3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        buildingcard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        building4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        venture1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        venture2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        venture3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        venturecard4.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine2 = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine2;
        venture4.setStyle(ind);
    }

    public void marketSetter() throws RemoteException{
        String actualString;
        String inizio;
        String fine;
        String ind;
        inizio = "-fx-background-image: url";
        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        market1.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        market2.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        market3.setStyle(ind);

        actualString = userFX.receive();
        actualString = userFX.receive();
        fine = "(stuff/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        market4.setStyle(ind);
    }

    public void harvestSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        if (!actualString.equals("harvestend")) {
            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            harvest1.setStyle(ind);

            actualString = userFX.receive();
            if (!actualString.equals("harvestend")) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                harvest2.setStyle(ind);

                actualString = userFX.receive();
                if (!actualString.equals("harvestend")) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    harvest3.setStyle(ind);

                    actualString = userFX.receive();
                    if (!actualString.equals("harvestend")) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        harvest4.setStyle(ind);

                        actualString = userFX.receive();
                        if (!actualString.equals("harvestend")) {
                            fine = "(stuff/" + actualString + ".png)" + ";";
                            ind = inizio + fine;
                            harvest5.setStyle(ind);

                            actualString = userFX.receive();
                            if (!actualString.equals("harvestend")) {
                                fine = "(stuff/" + actualString + ".png)" + ";";
                                ind = inizio + fine;
                                harvest6.setStyle(ind);

                                actualString = userFX.receive();
                                if (!actualString.equals("harvestend")) {
                                    fine = "(stuff/" + actualString + ".png)" + ";";
                                    ind = inizio + fine;
                                    harvest7.setStyle(ind);

                                    actualString = userFX.receive();
                                    if(!actualString.equals("harvestend")) {
                                        fine = "(stuff/" + actualString + ".png)" + ";";
                                        ind = inizio + fine;
                                        harvest8.setStyle(ind);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void productionSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        if (!actualString.equals("productionend")) {
            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            production1.setStyle(ind);

            actualString = userFX.receive();
            if (!actualString.equals("productionend")) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                production2.setStyle(ind);

                actualString = userFX.receive();
                if (!actualString.equals("productionend")) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    production3.setStyle(ind);

                    actualString = userFX.receive();
                    if (!actualString.equals("productionend")) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        production4.setStyle(ind);

                        actualString = userFX.receive();
                        if (!actualString.equals("productionend")) {
                            fine = "(stuff/" + actualString + ".png)" + ";";
                            ind = inizio + fine;
                            production5.setStyle(ind);

                            actualString = userFX.receive();
                            if (!actualString.equals("productionend")) {
                                fine = "(stuff/" + actualString + ".png)" + ";";
                                ind = inizio + fine;
                                production6.setStyle(ind);

                                actualString = userFX.receive();
                                if (!actualString.equals("productionend")) {
                                    fine = "(stuff/" + actualString + ".png)" + ";";
                                    ind = inizio + fine;
                                    production7.setStyle(ind);

                                    actualString = userFX.receive();
                                    if(!actualString.equals("productionend")) {
                                        fine = "(stuff/" + actualString + ".png)" + ";";
                                        ind = inizio + fine;
                                        production8.setStyle(ind);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public  void councilSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        if (!actualString.equals("councilend")) {
            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            council1.setStyle(ind);

            actualString = userFX.receive();
            if (!actualString.equals("councilend")) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                council2.setStyle(ind);

                actualString = userFX.receive();
                if (!actualString.equals("councilend")) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    council3.setStyle(ind);

                    actualString = userFX.receive();
                    if (!actualString.equals("councilend")) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        council4.setStyle(ind);
                    }
                }

            }
        }
    }

    public void excomSetter() throws RemoteException{
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        fine = "(exc/" + actualString + ".png)" + ";";
        ind = inizio + fine;

        excom1.setStyle(ind);
        actualString = userFX.receive();
        fine = "(exc/" + actualString + ".png)" + ";";
        ind = inizio + fine;

        excom2.setStyle(ind);
        actualString = userFX.receive();
        fine = "(exc/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        excom3.setStyle(ind);
    }


    public void dataSetter() throws RemoteException{
        String actualString = userFX.receive();
        actualString = userFX.receive();
        switch (actualString) {
            case "RED":
                circle1.setFill(RED);
                break;
            case "BLUE":
                circle1.setFill(BLUE);
                break;
            case "GREEN":
                circle1.setFill(GREEN);
                break;
            case "YELLOW":
                circle1.setFill(YELLOW);
                break;
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

        actualString = userFX.receive();//endresources
        actualString = userFX.receive();
        orange.setText(actualString);
        actualString = userFX.receive();
        white.setText(actualString);
        actualString = userFX.receive();
        black.setText(actualString);
        actualString = userFX.receive();
        neutral.setText(actualString);
        actualString = userFX.receive();
        Image image;
        switch (actualString) {
            case "RED":
                circle2.setFill(RED);
                break;
            case "BLUE":
                circle2.setFill(BLUE);
                break;
            case "GREEN":
                circle2.setFill(GREEN);
                break;
            case "YELLOW":
                circle2.setFill(YELLOW);
                break;
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
        actualString = userFX.receive();
        victory2.setText(actualString);

        actualString = userFX.receive();
        actualString = userFX.receive();
        orange.setText(actualString);
        actualString = userFX.receive();
        white.setText(actualString);
        actualString = userFX.receive();
        black.setText(actualString);
        actualString = userFX.receive();
        neutral.setText(actualString);
        actualString = userFX.receive();


        if(!actualString.equals("dataended")) {
            switch (actualString) {
                case "RED":
                    circle3.setFill(RED);
                    break;
                case "BLUE":
                    circle3.setFill(BLUE);
                    break;
                case "GREEN":
                    circle3.setFill(GREEN);
                    break;
                case "YELLOW":
                    circle3.setFill(YELLOW);
                    break;
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
            actualString = userFX.receive();
            orange.setText(actualString);
            actualString = userFX.receive();
            white.setText(actualString);
            actualString = userFX.receive();
            black.setText(actualString);
            actualString = userFX.receive();
            neutral.setText(actualString);
            actualString = userFX.receive();
            if(!actualString.equals("dataended")) {
                switch (actualString) {
                    case "RED":
                        circle4.setFill(RED);
                        break;
                    case "BLUE":
                        circle4.setFill(BLUE);
                        break;
                    case "GREEN":
                        circle4.setFill(GREEN);
                        break;
                    case "YELLOW":
                        circle4.setFill(YELLOW);
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
                actualString = userFX.receive();
                orange.setText(actualString);
                actualString = userFX.receive();
                white.setText(actualString);
                actualString = userFX.receive();
                black.setText(actualString);
                actualString = userFX.receive();
                neutral.setText(actualString);
                actualString = userFX.receive();
            }

        }

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

    @FXML
    private Button insert;

    @FXML
    private Button market1;

    @FXML
    private Button market2;

    @FXML
    private Button market3;

    @FXML
    private Button market4;

    @FXML
    private Pane council1;

    @FXML
    private Pane council2;

    @FXML
    private Pane council3;

    @FXML
    private Pane council4;

    @FXML
    private Button production1;

    @FXML
    private Pane production2;

    @FXML
    private Pane production3;

    @FXML
    private Pane production4;

    @FXML
    private Pane production5;

    @FXML
    private Pane production6;

    @FXML
    private Pane production7;

    @FXML
    private Pane production8;

    @FXML
    private Button harvest1;

    @FXML
    private Pane harvest2;

    @FXML
    private Pane harvest3;

    @FXML
    private Pane harvest4;

    @FXML
    private Pane harvest5;

    @FXML
    private Pane harvest6;

    @FXML
    private Pane harvest7;

    @FXML
    private Pane harvest8;

    @FXML
    private Pane excom1;

    @FXML
    private Pane excom2;

    @FXML
    private Pane excom3;

    @FXML
    private Pane cardt1;

    @FXML
    private Pane cardt2;

    @FXML
    private Pane cardt3;

    @FXML
    private Pane cardt4;

    @FXML
    private Pane cardt5;

    @FXML
    private Pane cardt6;

    @FXML
    private Pane cardc1;

    @FXML
    private Pane cardc2;

    @FXML
    private Pane cardc3;

    @FXML
    private Pane cardc4;

    @FXML
    private Pane cardc5;

    @FXML
    private Pane cardc6;

    @FXML
    private Pane cardb1;

    @FXML
    private Pane cardb2;

    @FXML
    private Pane cardb3;

    @FXML
    private Pane cardb4;

    @FXML
    private Pane cardb5;

    @FXML
    private Pane cardb6;

    @FXML
    private Pane cardv1;

    @FXML
    private Pane cardv2;

    @FXML
    private Pane cardv3;

    @FXML
    private Pane cardv4;

    @FXML
    private Pane cardv5;

    @FXML
    private Pane cardv6;

    @FXML
    private Pane cardl1;

    @FXML
    private Pane cardl2;

    @FXML
    private Pane cardl3;

    @FXML
    private Pane cardl4;

    @FXML
    private Button active;

    @FXML
    private Button discard;

    @FXML
    private Button skip;

}


