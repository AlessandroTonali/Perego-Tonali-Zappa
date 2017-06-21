package it.polimi.ingsw.GC_23.FX;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Created by jesss on 21/06/17.
 */
public class GameboardController {

    @FXML
    private Circle territory1;

    @FXML
    private Circle territory2;

    @FXML
    private Circle territory3;

    @FXML
    private Circle territory4;

    public void initialize() {
        territory1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setOnMouseEntered(territory1);
            }
        });
        territory2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setOnMouseEntered(territory2);
            }
        });
        territory3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setOnMouseEntered(territory3);
            }
        });
        territory4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setOnMouseEntered(territory4);
            }
        });
    }

    @FXML
    public void setOnMouseEntered(Circle circle){
            System.out.println("Entrato!");
            circle.setFill(Color.LIGHTYELLOW);
            circle.requestFocus();
        }

}

