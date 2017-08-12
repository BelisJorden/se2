import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jorden on 9-8-2017.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Generator generator = new Generator();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("nogistest", false, false, false, null);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String message = generator.generate(20, 20);
                System.out.println(generator.generate(70, 80));
                try {
                    channel.basicPublish("","nogistest", null,message.getBytes("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 0, 1000);
    }
}
