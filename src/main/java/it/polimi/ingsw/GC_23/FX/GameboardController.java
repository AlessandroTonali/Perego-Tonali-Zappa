package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.UserImpl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane master;

    @FXML
    private SplitPane splitpane;

    @FXML
    private ScrollPane scroll;

    @FXML
    private AnchorPane insidescroll;

    @FXML
    private Pane gameboard;

    @FXML
    private Pane territorybuttons;

    @FXML
    private Button territory1;

    @FXML
    private Button territory2;

    @FXML
    private Button territory3;

    @FXML
    private Button territory4;

    @FXML
    private Pane characterbuttons;

    @FXML
    private Button character1;

    @FXML
    private Button character2;

    @FXML
    private Button character3;

    @FXML
    private Button character4;

    @FXML
    private Pane buildingbuttons;

    @FXML
    private Button building1;

    @FXML
    private Button building2;

    @FXML
    private Button buiding3;

    @FXML
    private Button building4;

    public void initialize() {
        System.out.println("iniziato");
        for(Node b : gameboard.getChildren()){
            b.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("ciao");
                }
            });
        }
    }
}

