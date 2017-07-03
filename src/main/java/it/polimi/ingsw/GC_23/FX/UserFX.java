package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.UserImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 16/06/17.
 */
public class UserFX extends Application implements Runnable{
    private boolean socket;
    private boolean gui;
    private static ArrayList<UserImpl> users= new ArrayList<>();
    private static int counter;
    private int personalCounter;
    private Login login;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.login = new Login(this);
        this.login.startLogin();
    }

    @Override
    public void stop() throws Exception{
        System.out.println("UserFX stopped");
    }

    public void set() throws RemoteException{
        setSocketConnection(login.isSocketConnection());
        setGui(login.isGuiConnection());
        this.users.get(personalCounter).setSocketConnection(isSocketConnection());
        this.users.get(personalCounter).setGuiInterface(isGui());
        this.users.get(personalCounter).setUsername(login.getUsername());
        this.users.get(personalCounter).setYourTurn(true);
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

    public void setUserImpl(UserImpl user) throws RemoteException {
        this.users.add(user);
        this.personalCounter = counter;
        counter++;
    }

    @Override
    public void run() {
        launch();
    }
}
