package it.polimi.ingsw.GC_23.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jesss on 03/06/17.
 */
public interface User extends Remote{

    void printer(String string) throws RemoteException;
    String reader() throws IOException;

}
