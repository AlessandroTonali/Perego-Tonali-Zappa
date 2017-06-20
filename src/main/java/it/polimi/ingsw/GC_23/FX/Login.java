package it.polimi.ingsw.GC_23.FX;


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
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);

        borderPane = new BorderPane();
        borderPane.setPrefSize(600, 400);
        borderPane.setPadding(new Insets(20, 40, 20, 40));

        VBox vBoxTop = new VBox();
        vBoxTop.setAlignment(Pos.CENTER);
        Label title = new Label("Welcome to Lorenzo il Magnifico");
        title.setFont(Font.font(20));
        Image logo = new Image(("logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(200);
        logoView.setFitWidth(171);
        logoView.setPreserveRatio(true);
        FlowPane flowPane = new FlowPane();
        Label user = new Label("Username:");
        user.setFont(Font.font(15));
        TextField textField = new TextField();
        textField.setPrefWidth(200);
        textField.setPromptText("Insert your username here");
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(20);
        flowPane.getChildren().add(user);
        flowPane.getChildren().add(textField);
        vBoxTop.getChildren().add(title);
        vBoxTop.getChildren().add(logoView);
        vBoxTop.getChildren().add(flowPane);
        vBoxTop.setSpacing(30);
        borderPane.setTop(vBoxTop);

        VBox vBoxLeft = new VBox();
        vBoxLeft.setAlignment(Pos.BOTTOM_LEFT);
        Label connection = new Label("Select type of connection");
        connection.setFont(Font.font(15));
        RadioButton rmiButton = new RadioButton("RMI");
        RadioButton socketButton = new RadioButton("SOCKET");
        final ToggleGroup connectionGroup = new ToggleGroup();
        rmiButton.setToggleGroup(connectionGroup);
        socketButton.setToggleGroup(connectionGroup);
        socketButton.setSelected(true);
        vBoxLeft.getChildren().add(connection);
        vBoxLeft.getChildren().add(socketButton);
        vBoxLeft.getChildren().add(rmiButton);
        vBoxLeft.setSpacing(30);
        borderPane.setLeft(vBoxLeft);

        VBox vBoxRight = new VBox();
        vBoxRight.setAlignment(Pos.BOTTOM_LEFT);
        Label interf = new Label("Select type of interface");
        interf.setFont(Font.font(15));
        RadioButton cliButton = new RadioButton("CLI");
        RadioButton guiButton = new RadioButton("GUI");
        final ToggleGroup interfaceGroup = new ToggleGroup();
        cliButton.setToggleGroup(interfaceGroup);
        guiButton.setToggleGroup(interfaceGroup);
        cliButton.setSelected(true);
        vBoxRight.getChildren().add(interf);
        vBoxRight.getChildren().add(cliButton);
        vBoxRight.getChildren().add(guiButton);
        vBoxRight.setSpacing(30);
        borderPane.setRight(vBoxRight);

        VBox vBoxBottom = new VBox();
        Button button = new Button();
        button.setText("Submit");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textField.getText().equals("")){
                    /*Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid username");
                    alert.setContentText("Please insert a valid username");
                    alert.showAndWait();*/
                }
                else{
                    if(connectionGroup.getSelectedToggle().equals(rmiButton)){

                    }
                }
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