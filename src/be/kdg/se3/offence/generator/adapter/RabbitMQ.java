package be.kdg.se3.offence.generator.adapter;



import be.kdg.se3.offence.generator.domain.entity.Offence;
import be.kdg.se3.offence.generator.domain.service.OutputService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.TimeoutException;

public class RabbitMQ implements OutputService {
    private String host;
    private String queName;

    public RabbitMQ(String host, String queName) {
        this.host = host;
        this.queName = queName;
    }



    @Override
    public void publish(Offence offence) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection;
        Channel channel = null;
        try {
            connection = factory.newConnection();
             channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            channel.queueDeclare(queName, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Offence message = offence;
        Marshaller marshaller = null;
        StringWriter strWriter = new StringWriter();
        try {
            marshaller = new Marshaller(strWriter);
            marshaller.marshal(message);
            System.out.printf(strWriter.toString());

        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            channel.basicPublish("",queName, null,strWriter.toString().getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

