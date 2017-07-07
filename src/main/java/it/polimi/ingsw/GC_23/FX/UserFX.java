package it.polimi.ingsw.GC_23.FX;

import it.polimi.ingsw.GC_23.Connection.User;
import it.polimi.ingsw.GC_23.Connection.UserImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by jesss on 16/06/17.
 */

public class UserFX extends Application implements Runnable, Serializable{
    private boolean socket;
    private boolean gui;
    private static ArrayList<User> users= new ArrayList<>();
    private static int counter;
    private int personalCounter;
    private Login login;
    private String username;
    private transient User user;

    @Override
    public void start(Stage primaryStage) throws RemoteException, FileNotFoundException {
        this.login = new Login(this);
        this.login.startLogin();
    }

    @Override
    public void stop() throws RemoteException{
        System.out.println("UserFX stopped");
    }

    public void set() throws RemoteException{
        setSocketConnection(login.isSocketConnection());
        setGui(login.isGuiConnection());
        this.username = login.getUsername();
        setUser(this.users.get(personalCounter));
        user.setUserFX(this);
        user.setSocketConnection(isSocketConnection());
        user.setGuiInterface(isGui());
        user.setUsername(login.getUsername());
        user.setYourTurn(true);
    }

    public String receive() throws RemoteException{
        if(user.isSocketConnection()) {
            return user.getInScanner().nextLine();
        }
        else {
            return user.getSentToGui();
        }
    }

    public void send(String string) throws RemoteException{
        if(user.isSocketConnection()) {
            user.getOutWriter().println(string);
        }
        else {
           user.addReceivedFromGui(string);
        }
    }

    public boolean isSocketConnection() throws RemoteException {
        return socket;
    }

    public void setSocketConnection(boolean socketConnection) throws RemoteException {
        this.socket = socketConnection;
    }

    public boolean isGui() throws RemoteException {
        return gui;
    }

    public void setGui(boolean gui) throws RemoteException {
        this.gui = gui;
    }

    public void setUserImpl(UserImpl user) throws RemoteException {
        this.users.add(user);
        this.personalCounter = counter;
        counter++;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public void run() {
        launch();
    }
}
