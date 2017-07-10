package it.polimi.ingsw.GC_23.Controller;

import it.polimi.ingsw.GC_23.SingleCost;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by jesss on 23/05/17.
 */
public interface Controller {

    /**
     *  method for verify the condition to do a move
     * @return true if you can do the move, false if you can not
     * @throws RemoteException
     */
    public boolean isLegal() throws RemoteException;

    /**
     * method for doing action of the move
     * @throws IOException
     */
    public void makeAction() throws IOException;
}
