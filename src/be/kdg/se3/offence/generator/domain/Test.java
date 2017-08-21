package be.kdg.se3.offence.generator.domain;

import be.kdg.se3.offence.generator.domain.entity.Offence;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Timer;
import java.util.TimerTask;


public class Test {
    public static void main(String[] args) throws Exception {
        Generator generator = new Generator(90);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();



        channel.queueDeclare("GeneratedOffences", false, false, false, null);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Offence message = generator.generate();
               // System.out.println(generator.generate(70, 80));
                Marshaller marshaller = null;
                StringWriter strWriter = new StringWriter();
                try {
                    marshaller = new Marshaller(strWriter);
                    marshaller.marshal(message);
                    System.out.printf(strWriter.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MarshalException e) {
                    e.printStackTrace();
                } catch (ValidationException e) {
                    e.printStackTrace();
                }

                try {
                    channel.basicPublish("","GeneratedOffences", null,strWriter.toString().getBytes("UTF-8"));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            // 5 sec
        }, 0, 1000);
    }
}
