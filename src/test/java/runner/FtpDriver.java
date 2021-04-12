package runner;

import org.testng.Assert;

import javax.jms.JMSException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FtpDriver {
    File inputfw, dequeueOutputfw;
    private JmsConsumer jmsConsumer = new JmsConsumer();


    public void readFile(String expectedFileLocation) throws Exception {
        System.out.println(expectedFileLocation);
        inputfw = new File(expectedFileLocation);

    }

    public void setEndpointandQueueName(String endpoint, String queueName, String actualOutFileLocation,String fileExt) throws JMSException, IOException {
        System.out.println(endpoint + ":" + queueName + ":" + actualOutFileLocation);
        String outfile=jmsConsumer.init(endpoint, queueName, actualOutFileLocation,fileExt);
        dequeueOutputfw = new File(outfile);

    }



    public void expectedResponse(boolean assertValue) throws Exception {


        //result = FileUtils.contentEquals(inputfw, dequeueOutputfw);

        BufferedReader bExp;
        BufferedReader bRes;
        String expLine;
        String resLine;
        boolean equal = false;


        try {
            bExp = new BufferedReader(new FileReader(inputfw));
            bRes = new BufferedReader(new FileReader(dequeueOutputfw));

            if ((bExp != null) && (bRes != null)) {
                expLine = bExp.readLine();
                resLine = bRes.readLine();

                equal = ((expLine == null) && (resLine == null)) || ((expLine != null) && expLine.equals(resLine));

                while (equal && expLine != null) {
                    expLine = bExp.readLine();
                    resLine = bRes.readLine();
                     if(null==expLine||null==resLine) {
                        break;
                    }
                    equal = expLine.equals(resLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(equal, assertValue);
    }
}

