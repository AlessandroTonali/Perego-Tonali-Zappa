package it.polimi.ingsw.GC_23.FX;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.prism.paint.Color;
import it.polimi.ingsw.GC_23.StringTyper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.*;

/**
 * Created by jesss on 21/06/17.
 */
public class GameboardController implements Serializable {
    private UserFX userFX;
    private String color;
    private transient final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GameboardController(UserFX userFX, String color) {
        this.userFX = userFX;
        this.color = color;

    }

    public void boardTranslator() throws RemoteException {
        try {
            String actualString;
            towerSetter();
            marketSetter();
            harvestSetter();
            productionSetter();
            councilSetter();
            excomSetter();
            actualString = userFX.receive();
        } catch (RemoteException e) {
            logger.setLevel(Level.SEVERE);
            logger.severe(String.valueOf(e));
        }
    }

    public void dataTranslator() throws RemoteException {
        dataSetter();
    }

    public void cardsTranslator() throws RemoteException {
        cardsSetter();
    }

    public void whoseTurnTranslator() throws RemoteException {
        turnsetter();
    }

    private void turnsetter() throws RemoteException {
        String string = userFX.receive();
        turn.setText(string);
    }

    public void towerSetter() throws RemoteException {
        String inizio;
        String fine;
        String fine2;
        String ind;
        String actualString;
        actualString = userFX.receive();
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

    public void marketSetter() throws RemoteException {
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
        actualString = userFX.receive();
        if (!"harvestend".equals(actualString)) {
            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            harvestb.setStyle(ind + "-fx-background-size: contain;");

            actualString = userFX.receive();
            actualString = userFX.receive();
            if (!"harvestend".equals(actualString)) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                harvest2.setStyle(ind + "-fx-background-size: contain;");

                actualString = userFX.receive();
                actualString = userFX.receive();
                if (!"harvestend".equals(actualString)) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    harvest3.setStyle(ind + "-fx-background-size: contain;");

                    actualString = userFX.receive();
                    actualString = userFX.receive();
                    if (!"harvestend".equals(actualString)) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        harvest4.setStyle(ind + "-fx-background-size: contain;");

                        actualString = userFX.receive();
                        actualString = userFX.receive();
                        if (!"harvestend".equals(actualString)) {
                            fine = "(stuff/" + actualString + ".png)" + ";";
                            ind = inizio + fine;
                            harvest5.setStyle(ind + "-fx-background-size: contain;");

                            actualString = userFX.receive();
                            actualString = userFX.receive();
                            if (!"harvestend".equals(actualString)) {
                                fine = "(stuff/" + actualString + ".png)" + ";";
                                ind = inizio + fine;
                                harvest6.setStyle(ind + "-fx-background-size: contain;");

                                actualString = userFX.receive();
                                actualString = userFX.receive();
                                if (!"harvestend".equals(actualString)) {
                                    fine = "(stuff/" + actualString + ".png)" + ";";
                                    ind = inizio + fine;
                                    harvest7.setStyle(ind + "-fx-background-size: contain;");

                                    actualString = userFX.receive();
                                    actualString = userFX.receive();
                                    if (!"harvestend".equals(actualString)) {
                                        fine = "(stuff/" + actualString + ".png)" + ";";
                                        ind = inizio + fine;
                                        harvest8.setStyle(ind + "-fx-background-size: contain;");
                                    } else {
                                        harvest8.setStyle("-fx-background-image: url(stuff/null.png);");
                                    }
                                } else {
                                    harvest7.setStyle("-fx-background-image: url(stuff/null.png);");
                                }
                            } else {
                                harvest6.setStyle("-fx-background-image: url(stuff/null.png);");
                            }
                        } else {
                            harvest5.setStyle("-fx-background-image: url(stuff/null.png);");
                        }
                    } else {
                        harvest4.setStyle("-fx-background-image: url(stuff/null.png);");
                    }
                } else {
                    harvest3.setStyle("-fx-background-image: url(stuff/null.png);");
                }
            } else {
                harvest2.setStyle("-fx-background-image: url(stuff/null.png);");
            }
        } else {
            harvestb.setStyle("-fx-background-image: url(stuff/null.png);");
        }
    }

    public void productionSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        actualString = userFX.receive();

        if (!"productionend".equals(actualString)) {
            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            productionb.setStyle(ind + "-fx-background-size: contain;");

            actualString = userFX.receive();
            actualString = userFX.receive();
            if (!"productionend".equals(actualString)) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                production2.setStyle(ind + "-fx-background-size: contain;");

                actualString = userFX.receive();
                actualString = userFX.receive();
                if (!"productionend".equals(actualString)) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    production3.setStyle(ind + "-fx-background-size: contain;");

                    actualString = userFX.receive();
                    actualString = userFX.receive();
                    if (!"productionend".equals(actualString)) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        production4.setStyle(ind + "-fx-background-size: contain;");

                        actualString = userFX.receive();
                        actualString = userFX.receive();
                        if (!"productionend".equals(actualString)) {
                            fine = "(stuff/" + actualString + ".png)" + ";";
                            ind = inizio + fine;
                            production5.setStyle(ind + "-fx-background-size: contain;");

                            actualString = userFX.receive();
                            actualString = userFX.receive();
                            if (!"productionend".equals(actualString)) {
                                fine = "(stuff/" + actualString + ".png)" + ";";
                                ind = inizio + fine;
                                production6.setStyle(ind + "-fx-background-size: contain;");

                                actualString = userFX.receive();
                                actualString = userFX.receive();
                                if (!"productionend".equals(actualString)) {
                                    fine = "(stuff/" + actualString + ".png)" + ";";
                                    ind = inizio + fine;
                                    production7.setStyle(ind + "-fx-background-size: contain;");

                                    actualString = userFX.receive();
                                    actualString = userFX.receive();
                                    if (!"productionend".equals(actualString)) {
                                        fine = "(stuff/" + actualString + ".png)" + ";";
                                        ind = inizio + fine;
                                        production8.setStyle(ind + "-fx-background-size: contain;");
                                    } else {
                                        production8.setStyle("-fx-background-image: url(stuff/null.png);");
                                    }
                                } else {
                                    production7.setStyle("-fx-background-image: url(stuff/null.png);");
                                }
                            } else {
                                production6.setStyle("-fx-background-image: url(stuff/null.png);");
                            }
                        } else {
                            production5.setStyle("-fx-background-image: url(stuff/null.png);");
                        }
                    } else {
                        production4.setStyle("-fx-background-image: url(stuff/null.png);");
                    }
                } else {
                    production3.setStyle("-fx-background-image: url(stuff/null.png);");
                }
            } else {
                production2.setStyle("-fx-background-image: url(stuff/null.png);");
            }
        } else {
            productionb.setStyle("-fx-background-image: url(stuff/null.png);");
        }
    }

    public void councilSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;
        String actualString = userFX.receive();
        actualString = userFX.receive();
        if (!"councilend".equals(actualString)) {

            fine = "(stuff/" + actualString + ".png)" + ";";
            ind = inizio + fine;
            council1.setStyle(ind + "-fx-background-size: contain;");

            actualString = userFX.receive();
            actualString = userFX.receive();
            if (!"councilend".equals(actualString)) {
                fine = "(stuff/" + actualString + ".png)" + ";";
                ind = inizio + fine;
                council2.setStyle(ind + "-fx-background-size: contain;");

                actualString = userFX.receive();
                actualString = userFX.receive();
                if (!"councilend".equals(actualString)) {
                    fine = "(stuff/" + actualString + ".png)" + ";";
                    ind = inizio + fine;
                    council3.setStyle(ind + "-fx-background-size: contain;");

                    actualString = userFX.receive();
                    actualString = userFX.receive();
                    if (!"councilend".equals(actualString)) {
                        fine = "(stuff/" + actualString + ".png)" + ";";
                        ind = inizio + fine;
                        council4.setStyle(ind + "-fx-background-size: contain;");
                    } else {
                        council4.setStyle("-fx-background-image: url(stuff/null.png);");
                    }
                } else {
                    council3.setStyle("-fx-background-image: url(stuff/null.png);");
                }
            } else {
                council2.setStyle("-fx-background-image: url(stuff/null.png);");
            }
        } else {
            council1.setStyle("-fx-background-image: url(stuff/null.png);");
        }
    }

    public void excomSetter() throws RemoteException {
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


    public void dataSetter() throws RemoteException {
        yourcolor.setText(color);
        String actualString = userFX.receive();
        String actualColor = actualString;

        switch (actualString) {
            case "RED":
                circle1.setFill(RED);
                first.setFill(RED);
                break;
            case "BLUE":
                circle1.setFill(BLUE);
                first.setFill(BLUE);
                break;
            case "YELLOW":
                circle1.setFill(YELLOW);
                first.setFill(YELLOW);
                break;
            case "GREEN":
                circle1.setFill(GREEN);
                first.setFill(GREEN);
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
        if (actualColor.equals(color)) orange.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) white.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) black.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) neutral.setText(actualString);
        actualString = userFX.receive();
        actualColor = actualString;
        switch (actualString) {
            case "BLUE":
                circle2.setFill(BLUE);
                second.setFill(BLUE);
                break;
            case "RED":
                circle2.setFill(RED);
                second.setFill(RED);
                break;
            case "GREEN":
                circle2.setFill(GREEN);
                second.setFill(GREEN);
                break;
            case "YELLOW":
                circle2.setFill(YELLOW);
                second.setFill(YELLOW);
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
        if (actualColor.equals(color)) orange.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) white.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) black.setText(actualString);
        actualString = userFX.receive();
        if (actualColor.equals(color)) neutral.setText(actualString);
        actualString = userFX.receive();


        if (!"dataended".equals(actualString)) {
            actualColor = actualString;
            switch (actualString) {
                case "RED":
                    circle3.setFill(RED);
                    third.setFill(RED);
                    break;
                case "BLUE":
                    circle3.setFill(BLUE);
                    third.setFill(BLUE);
                    break;
                case "GREEN":
                    circle3.setFill(GREEN);
                    third.setFill(GREEN);
                    break;
                case "YELLOW":
                    circle3.setFill(YELLOW);
                    third.setFill(YELLOW);
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
            if (actualColor.equals(color)) orange.setText(actualString);
            actualString = userFX.receive();
            if (actualColor.equals(color)) white.setText(actualString);
            actualString = userFX.receive();
            if (actualColor.equals(color)) black.setText(actualString);
            actualString = userFX.receive();
            if (actualColor.equals(color)) neutral.setText(actualString);
            actualString = userFX.receive();

            if (!"dataended".equals(actualString)) {
                actualColor = actualString;
                switch (actualString) {
                    case "RED":
                        circle4.setFill(RED);
                        fourth.setFill(RED);
                        break;
                    case "BLUE":
                        circle4.setFill(BLUE);
                        fourth.setFill(BLUE);
                        break;
                    case "GREEN":
                        circle4.setFill(GREEN);
                        fourth.setFill(GREEN);
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
                actualString = userFX.receive();
                if (actualColor.equals(color)) {
                    orange.setText(actualString);
                }

                actualString = userFX.receive();
                if (actualColor.equals(color)) white.setText(actualString);
                actualString = userFX.receive();
                if (actualColor.equals(color)) black.setText(actualString);
                actualString = userFX.receive();
                if (actualColor.equals(color)) neutral.setText(actualString);
                actualString = userFX.receive();
            }

        }

    }

    public void cardsSetter() throws RemoteException {
        String inizio = "-fx-background-image: url";
        String fine;
        String ind;

        String actualString = userFX.receive();
        while (!actualString.equals(color)) {
            actualString = userFX.receive();
        }
        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt1.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt2.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt3.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt4.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt5.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardt6.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc1.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc2.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc3.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc4.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc5.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardc6.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb1.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb2.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb3.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb4.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb5.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardb6.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv1.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv2.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv3.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv4.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv5.setStyle(ind);

        actualString = userFX.receive();
        fine = "(card/" + actualString + ".png)" + ";";
        ind = inizio + fine;
        cardv6.setStyle(ind);

        actualString = userFX.receive();
        fine = "(leader/" + actualString + ".jpg)" + ";";
        ind = inizio + fine;
        cardl1.setStyle(ind);

        actualString = userFX.receive();
        fine = "(leader/" + actualString + ".jpg)" + ";";
        ind = inizio + fine;
        cardl2.setStyle(ind);

        actualString = userFX.receive();
        fine = "(leader/" + actualString + ".jpg)" + ";";
        ind = inizio + fine;
        cardl3.setStyle(ind);

        actualString = userFX.receive();
        fine = "(leader/" + actualString + ".jpg)" + ";";
        ind = inizio + fine;
        cardl4.setStyle(ind);
        while (!"end".equals(actualString)) {
            actualString = userFX.receive();
        }
    }

    public String chooseFamilyMember() throws RemoteException {
        List<String> choices = new ArrayList<>();
        String actualString = userFX.receive();
        while (!"end".equals(actualString)) {
            choices.add(actualString);
            actualString = userFX.receive();
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("ORANGE", choices);
        dialog.setTitle("Family Member");
        dialog.setHeaderText("Set the family member for your action");
        dialog.setContentText("Choose your family member:");

        Optional<String> result = dialog.showAndWait();
        while (!result.isPresent()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        return result.get();
    }

    public String chooseCouncilPrivilege() throws RemoteException {
        int value = -1;
        List<String> choices = new ArrayList<>();
        String actualString = userFX.receive();
        while (!"end".equals(actualString)) {
            choices.add(actualString);
            actualString = userFX.receive();
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("faith points 0 military points 0 gold 0 servants 0 wood 1 stone 1 victory points 0", choices);
        dialog.setTitle("Council Privilege");
        dialog.setHeaderText("Select the council privilege you will receive");
        dialog.setContentText("Choose your council privilege:");

        Optional<String> result = dialog.showAndWait();
        while (!result.isPresent()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        switch (result.get()) {
            case "faith points 0 military points 0 gold 0 servants 0 wood 1 stone 1 victory points 0":
                value = 0;
                break;
            case "faith points 0 military points 0 gold 0 servants 2 wood 0 stone 0 victory points 0":
                value = 1;
                break;
            case "faith points 0 military points 0 gold 2 servants 0 wood 0 stone 0 victory points 0":
                value = 2;
                break;
            case "faith points 0 military points 2 gold 0 servants 0 wood 0 stone 0 victory points 0":
                value = 3;
                break;
            case "faith points 1 military points 0 gold 0 servants 0 wood 0 stone 0 victory points 0":
                value = 4;
                break;
        }
        return String.valueOf(value);
    }

    public String chooseIncreaseValue() throws RemoteException {
        TextInputDialog dialog = new TextInputDialog("value");
        dialog.setTitle("Increase value");
        dialog.setHeaderText("Enter the family value");
        dialog.setContentText("Enter the value here:");

        Optional<String> result = dialog.showAndWait();
        while (!result.isPresent()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.setLevel(Level.SEVERE);
                logger.severe(String.valueOf(e));
                Thread.currentThread().interrupt();
            }
        }
        return result.get();
    }

    public void busyAlert() throws RemoteException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Invalid move: space already occupied!");
        alert.setContentText("Please select a valid choice");
        alert.showAndWait();
    }

    public String identifyFamilyMember(String string) throws RemoteException {
        int value = -1;
        switch (string) {
            case "ORANGE":
                value = 0;
                break;
            case "WHITE":
                value = 1;
                break;
            case "BLACK":
                value = 2;
                break;
            case "NEUTRAL":
                value = 3;
                break;
        }
        return String.valueOf(value);
    }


    public void handle() throws RemoteException {
        territory1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!territory1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("5");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("3");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        territory1.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        territory2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!territory2.getStyle().contains(("-fx-background-image: url(stuff/null.png);"))) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("5");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("2");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        territory2.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        territory3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!territory3.getStyle().contains(("-fx-background-image: url(stuff/null.png);"))) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("5");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("1");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        territory3.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        territory4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!territory4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("5");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("0");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        territory4.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        character1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!character1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("6");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("3");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        character1.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        character2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!character2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("6");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("2");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        character2.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        character3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!character3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("6");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("1");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        character3.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        character4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!character4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("6");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("0");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        character4.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        building1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!building1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("7");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("3");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        building1.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        building2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!building2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("7");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("2");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        building2.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        building3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!building3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("7");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("1");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        building3.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        building4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!building4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("7");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("0");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        building4.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        venture1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!venture1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("8");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("3");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        venture1.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        venture2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!venture2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("8");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("2");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        venture2.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        venture3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!venture3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("8");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("1");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        venture3.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }

            }
        });

        venture4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!venture4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("8");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        userFX.send("0");
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        venture4.setStyle(ind);
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        skip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    userFX.send("11");
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        council.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String inizio = "-fx-background-image: url";
                    String fine;
                    userFX.send("0");
                    String familyMember = chooseFamilyMember();
                    userFX.send(identifyFamilyMember(familyMember));
                    String astring = userFX.receive();
                    while (!"effectended".equals(astring)) {
                        try {
                            userFX.send(effectswitcher());
                            astring = userFX.receive();
                        }catch (NullPointerException e ){
                            astring = userFX.receive();
                            continue;
                        }
                    }
                    //userFX.send(chooseCouncilPrivilege());
                    fine = "(stuff/" + color + familyMember + ".png)" + ";";
                    String ind = inizio + fine;
                    if (council1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        council1.setStyle(ind + "-fx-background-size: contain;");
                    } else {
                        if (council2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                            council2.setStyle(ind + "-fx-background-size: contain;");
                        } else {
                            if (council3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                council3.setStyle(ind + "-fx-background-size: contain;");
                            } else {
                                if (council4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                    council4.setStyle(ind + "-fx-background-size: contain;");
                                } else {
                                    busyAlert();
                                }
                            }
                        }
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        harvestb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!harvestb.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("1");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        harvestb.setStyle(ind + "-fx-background-size: contain;");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        harvestrectangle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (harvestb.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("1");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        if (harvest2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                            harvest2.setStyle(ind + "-fx-background-size: contain;");
                        } else {
                            if (harvest3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                harvest3.setStyle(ind + "-fx-background-size: contain;");
                            } else {
                                if (harvest4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                    harvest4.setStyle(ind + "-fx-background-size: contain;");
                                } else {
                                    if (harvest5.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                        harvest5.setStyle(ind + "-fx-background-size: contain;");
                                    } else {
                                        if (harvest6.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                            harvest6.setStyle(ind + "-fx-background-size: contain;");
                                        } else {
                                            if (harvest7.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                                harvest7.setStyle(ind + "-fx-background-size: contain;");
                                            } else {
                                                busyAlert();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        productionb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!productionb.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("2");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        productionb.setStyle(ind + "-fx-background-size: contain;");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        productionrectangle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (productionb.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("2");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        if (production2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                            production2.setStyle(ind + "-fx-background-size: contain;");
                        } else {
                            if (production3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                production3.setStyle(ind + "-fx-background-size: contain;");
                            } else {
                                if (production4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                    production4.setStyle(ind + "-fx-background-size: contain;");
                                } else {
                                    if (production5.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                        production5.setStyle(ind + "-fx-background-size: contain;");
                                    } else {
                                        if (production6.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                            production6.setStyle(ind + "-fx-background-size: contain;");
                                        } else {
                                            if (production7.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                                                production7.setStyle(ind + "-fx-background-size: contain;");
                                            } else {
                                                busyAlert();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        market1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!market1.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("4");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        market1.setStyle(ind + "-fx-background-size: contain;");
                        userFX.send("1");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        market2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!market2.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("4");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        market2.setStyle(ind + "-fx-background-size: contain;");
                        userFX.send("2");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        market3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!market3.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("4");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        market3.setStyle(ind + "-fx-background-size: contain;");
                        userFX.send("3");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        market4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!market4.getStyle().contains("-fx-background-image: url(stuff/null.png);")) {
                        busyAlert();
                    } else {
                        String inizio = "-fx-background-image: url";
                        String fine;
                        userFX.send("4");
                        String familyMember = chooseFamilyMember();
                        userFX.send(identifyFamilyMember(familyMember));
                        fine = "(stuff/" + color + familyMember + ".png)" + ";";
                        String ind = inizio + fine;
                        market4.setStyle(ind + "-fx-background-size: contain;");
                        userFX.send("4");
                    }
                } catch (RemoteException e) {
                    logger.setLevel(Level.SEVERE);
                    logger.severe(String.valueOf(e));
                }
            }
        });

        increase.setOnAction(new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle(ActionEvent event) {
                                     try {
                                         userFX.send(chooseIncreaseValue());
                                         userFX.send(identifyFamilyMember(chooseFamilyMember()));
                                     } catch (RemoteException e) {
                                         logger.setLevel(Level.SEVERE);
                                         logger.severe(String.valueOf(e));
                                     }
                                 }
                             }
        );
    }

    private String effectswitcher() throws RemoteException {
        String actualString = userFX.receive();
        switch (actualString) {
            case ("councilEffect"):
                return chooseCouncilPrivilege();
            case ("benefitsEffect"):
                return null;
        }
        return  null;
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

    @FXML
    private Label turn;

    @FXML
    private Label yourcolor;

    @FXML
    private Button council;

    @FXML
    private Button productionb;

    @FXML
    private Button productionrectangle;

    @FXML
    private Button harvestb;

    @FXML
    private Button harvestrectangle;

    @FXML
    private Button increase;

}


