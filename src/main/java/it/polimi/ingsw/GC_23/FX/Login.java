package it.polimi.ingsw.GC_23.FX;

import com.sun.java.swing.action.AlignCenterAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by jesss on 16/06/17.
 */
public class Login {
    private Stage primaryStage;
    private Button button;
    private BorderPane borderPane;

    public Login() {
        primaryStage = new Stage();
        this.primaryStage.setTitle("Login");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);

        borderPane = new BorderPane();
        borderPane.setPrefSize(600, 400);
        borderPane.setLayoutX(171);
        borderPane.setLayoutY(83);

        Label label = new Label("Username:");
        borderPane.setTop(label);

        button = new Button();
        button.setText("Confirm");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("End Login");
            }
        });
    }

    public void startLogin(){
        StackPane root = new StackPane();
        root.getChildren().add(borderPane);
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
