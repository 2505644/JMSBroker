package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Receiver extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private String queueName;
    private int sessionMode;
    private boolean isTransacted;
    private String url;

    public Receiver(String queueName, int sessionMode, boolean isTransacted, String url) {
        this.queueName = queueName;
        this.sessionMode = sessionMode;
        this.isTransacted = isTransacted;
        this.url = url;
    }

    @Override
    public void run() {
        receiveMessage(sessionMode, isTransacted, url);
    }

    void receiveMessage(int sessionMode, boolean isTransacted, String url) {

        Connection connection = null;
        Session session = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(isTransacted, sessionMode);
            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            TextMessage textMessage = (TextMessage) message;
            logger.error("Success received message: " + textMessage.getText());

            message.acknowledge();
            session.close();
            connection.close();
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }
    }
}
