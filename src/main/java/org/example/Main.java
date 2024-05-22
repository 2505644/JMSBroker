package org.example;

import javax.jms.Session;

public class Main {
    private static final boolean IS_PERSISTENT = true;
    //private static final boolean IS_PERSISTENT = false;
    private static final boolean IS_TRANSACTED = false;
    //private static final boolean IS_TRANSACTED = true;
    //private static final String URL = "tcp://localhost:61616";
    private static final String URL = "vm://broker";
    private static final String QUEUENAME = "queue";

    //private static final int SESSION_MODE = Session.SESSION_TRANSACTED;
    private static final int SESSION_MODE = Session.AUTO_ACKNOWLEDGE;
    //private static final int SESSION_MODE = Session.DUPS_OK_ACKNOWLEDGE;
    //private static final int SESSION_MODE = Session.CLIENT_ACKNOWLEDGE;

    public static void main(String[] args) {
        Sender sender = new Sender(QUEUENAME, SESSION_MODE, IS_TRANSACTED, URL);
        Receiver receiver = new Receiver(QUEUENAME, SESSION_MODE, IS_TRANSACTED, URL);

        Broker broker = new Broker(IS_PERSISTENT, IS_TRANSACTED, URL, QUEUENAME);
        broker.run();
        try {
            sender.start();
            sender.join();

            receiver.start();
            receiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
