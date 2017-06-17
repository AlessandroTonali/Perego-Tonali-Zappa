package it.polimi.ingsw.GC_23.FX;

import com.sun.java.swing.action.AlignCenterAction;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jesss on 16/06/17.
 */
public class Login {
    private Stage primaryStage;
    private BorderPane borderPane;

    public Login() throws FileNotFoundException {
        primaryStage = new Stage();
        this.primaryStage.setTitle("Login");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);

        borderPane = new BorderPane();
        borderPane.setPrefSize(600, 400);
        borderPane.setPadding(new Insets(20, 40, 20, 40));

        VBox vBoxTop = new VBox();
        vBoxTop.setAlignment(Pos.CENTER);
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
        vBoxTop.getChildren().add(title);
        vBoxTop.getChildren().add(flowPane);
        vBoxTop.setSpacing(30);
        borderPane.setTop(vBoxTop);

        VBox vBoxLeft = new VBox();
        vBoxLeft.setAlignment(Pos.BOTTOM_LEFT);
        Label connection = new Label("Select type of connection");
        connection.setFont(Font.font(15));
        CheckBox rmiCheck = new CheckBox("RMI");
        CheckBox socketCheck = new CheckBox("SOCKET");
        vBoxLeft.getChildren().add(connection);
        vBoxLeft.getChildren().add(rmiCheck);
        vBoxLeft.getChildren().add(socketCheck);
        vBoxLeft.setSpacing(30);
        borderPane.setLeft(vBoxLeft);

        VBox vBoxRight = new VBox();
        vBoxRight.setAlignment(Pos.BOTTOM_LEFT);
        Label interf = new Label("Select type of interface");
        interf.setFont(Font.font(15));
        CheckBox cliCheck = new CheckBox("CLI");
        CheckBox guiCheck = new CheckBox("GUI");
        vBoxRight.getChildren().add(interf);
        vBoxRight.getChildren().add(cliCheck);
        vBoxRight.getChildren().add(guiCheck);
        vBoxRight.setSpacing(30);
        borderPane.setRight(vBoxRight);

        VBox vBoxBottom = new VBox();
        Button button = new Button();
        button.setText("Confirm");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("End Login");
            }
        });
        vBoxBottom.setPrefSize(100,100);
        vBoxBottom.setAlignment(Pos.CENTER);
        vBoxBottom.getChildren().add(button);
        borderPane.setBottom(vBoxBottom);
    }

    public void startLogin(){
        StackPane root = new StackPane();
        root.getChildren().add(borderPane);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
