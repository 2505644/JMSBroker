package org.example;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker {

    private static final Logger logger = LoggerFactory.getLogger(Broker.class);

    private boolean isPersistent;
    private boolean isTransacted;
    private String url;
    private String queueName;

    public Broker(boolean isPersistent, boolean isTransacted, String url, String queueName) {
        this.isPersistent = isPersistent;
        this.isTransacted = isTransacted;
        this.url = url;
        this.queueName = queueName;
    }

    public void run() {
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(isPersistent);
            brokerService.setUseJmx(false);
            brokerService.addConnector(url);
            brokerService.setBrokerName("broker");

            brokerService.start();
            logger.info("Broker started");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
