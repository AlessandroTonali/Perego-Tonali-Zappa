package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.UserImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by jesss on 16/06/17.
 */
public class LoginFX extends Application{
    private boolean socket;
    private boolean gui;
    private Login login;
    public static UserImpl user;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.login = new Login();
        this.login.startLogin();
    }

    @Override
    public void stop() throws Exception{
        setSocketConnection(login.isSocketConnection());
        setGui(login.isGuiConnection());
        user.setSocketConnection(isSocketConnection());
        user.setGuiInterface(isGui());
        user.setUsername(login.getUsername());
        System.out.println("LoginFX stopped");
    }

    public boolean isSocketConnection() {
        return socket;
    }

    public void setSocketConnection(boolean socketConnection) {
        this.socket = socketConnection;
    }

    public boolean isGui() {
        return gui;
    }

    public void setGui(boolean gui) {
        this.gui = gui;
    }

    public static void setUser(UserImpl user) {
        LoginFX.user = user;
    }
}
