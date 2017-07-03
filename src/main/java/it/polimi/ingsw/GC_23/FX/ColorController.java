package it.polimi.ingsw.GC_23.FX;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.text.TabableView;

/**
 * Created by jesss on 03/07/17.
 */
public class ColorController {

    @FXML private TableView<String> table;
    @FXML private TableColumn<String, String> Color;
    @FXML private TableColumn<String , String> User;

    public void initialize() {
    }
}
