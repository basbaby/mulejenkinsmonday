package runner;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JmsConsumer implements MessageListener {
    static Logger log = Logger.getLogger(JmsConsumer.class);
    private String consumerName;
    private MessageConsumer consumer = null;
    private Connection connection = null;
    private Session session = null;
    private StringBuilder contentBuilder = new StringBuilder();
    private static String outputfileLocation;
    private FileWriter fw;
    private FileReader fr;
    private String path;


    public JmsConsumer() {

    }

    public String init(String url, String qName, String ouputLocation,String fileExt) throws JMSException {
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        JmsConsumer.outputfileLocation = ouputLocation.concat("_").concat(fileSuffix).concat(".").concat(fileExt);
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            // Create a Connection
            connection = connectionFactory.createConnection();
            connection.start();
            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            log.info("Connection Successfull");
            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(qName);
            System.out.println("Connection success");
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new JmsConsumer("Consumer"));
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        } finally {
            if (null != connection) {
                // Clean up
                session.close();
                connection.close();
            }
        }
        return outputfileLocation;
    }

    public void appendStrToFile(String str) throws IOException {
        try {

            path = JmsConsumer.outputfileLocation;
            fw = new FileWriter(path, true);
            fw.write(str);
            //fw.write("\r\n");

        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        } finally {
            fw.close();
        }
    }

    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Jms message is:\n" + textMessage.getText());
                appendStrToFile(textMessage.getText().trim());
            } catch (JMSException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Queue Empty");

        }

    }

    public JmsConsumer(String consumerName) {
        this.consumerName = consumerName;
    }


}
