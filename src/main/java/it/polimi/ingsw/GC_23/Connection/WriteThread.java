package it.polimi.ingsw.GC_23.Connection;

import java.io.IOException;

/**
 * Created by Alessandro Tonali on 03/07/2017.
 */
public class WriteThread implements Runnable {
    private UserImpl user;

    public WriteThread(UserImpl user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            user.getOutWriter().println(user.getInKeyboard().readLine());
            user.setTyped(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
