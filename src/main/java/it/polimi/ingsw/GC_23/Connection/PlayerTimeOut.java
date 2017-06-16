package it.polimi.ingsw.GC_23.Connection;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * Created by jesss on 16/06/17.
 */
public class PlayerTimeOut implements Callable<InterruptedException>{


    @Override
    public InterruptedException call() throws InterruptedException{
        Thread.sleep(10000);
        return new InterruptedException();
    }
}
