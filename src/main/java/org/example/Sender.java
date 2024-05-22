package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Sender extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    private String queueName;
    private int sessionMode;
    private boolean isTransacted;
    private String url;

    public Sender(String queueName, int sessionMode, boolean isTransacted, String url) {
        this.queueName = queueName;
        this.sessionMode = sessionMode;
        this.isTransacted = isTransacted;
        this.url = url;
    }

    @Override
    public void run() {
        sendMessage(sessionMode, isTransacted, url);
    }

    public void sendMessage(int sessionMode, boolean isTransacted, String url) {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(isTransacted, sessionMode);
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Hello world!");

            producer.send(message);

            logger.error("Sent the message: " + message.getText());

            //session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }
    }
}
