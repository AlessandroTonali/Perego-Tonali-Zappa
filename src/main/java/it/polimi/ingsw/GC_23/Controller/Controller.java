package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by jesss on 23/05/17.
 */
public interface Controller {

    public boolean isLegal() throws RemoteException;

    public void makeAction() throws IOException;
}
