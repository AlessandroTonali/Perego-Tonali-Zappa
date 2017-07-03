package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.PlayerController;
import it.polimi.ingsw.GC_23.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by jesss on 03/07/17.
 */
public class ColorController implements Initializable {
    @FXML
    private TableView table;

    @FXML
    private TableColumn<String, String> Color;
    private TableColumn<String, String> User;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlayerController playerController = new PlayerController();

    }
}
