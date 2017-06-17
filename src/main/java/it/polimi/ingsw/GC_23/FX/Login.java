package it.polimi.ingsw.GC_23.FX;

import com.sun.java.swing.action.AlignCenterAction;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
        borderPane.setPadding(new Insets(10, 20, 10, 20));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        Label title = new Label("Welcome to Lorenzo il Magnifico");
        title.setFont(Font.font(20));
        FlowPane flowPane = new FlowPane();
        Label user = new Label("Username:");
        user.setFont(Font.font(15));
        TextField textField = new TextField();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(20);
        flowPane.getChildren().add(user);
        flowPane.getChildren().add(textField);
        vBox.getChildren().add(title);
        vBox.getChildren().add(flowPane);
        vBox.setSpacing(30);
        borderPane.setTop(vBox);

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
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
