package be.kdg.se3.offence.generator.domain;


import be.kdg.se3.offence.generator.domain.entity.Offence;
import be.kdg.se3.offence.generator.domain.service.OutputService;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jorden on 21-8-2017.
 */
public class Controller {

    private OutputService outputService;
    private Generator generator;

    private boolean run = true;
    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public void stop() {
        this.run = false;
    }



    public void start(int lowerboundMilli,int upperboundMilli) {

        while (run) {
           int time=generateTimeToWait(lowerboundMilli,upperboundMilli);
            Offence message = generator.generate();
            outputService.publish(message);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }


       /* Timer timer = new Timer();


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Offence message = generator.generate(60, 20);
                outputService.publish(message);
                time=generateTimeToWait(1000,5000);

                System.out.println(time);


            }
        },0,time);*/



    private int generateTimeToWait(int lowerboundMilli,int upperboundMilli) {
        int timeToWait;
        if (lowerboundMilli == upperboundMilli) {
            timeToWait = lowerboundMilli;
        } else {
            Random random = new Random();
            timeToWait = random.nextInt(upperboundMilli-lowerboundMilli) + lowerboundMilli;
        }

        System.out.println(timeToWait + "\n");
        return  timeToWait;
    }
}
