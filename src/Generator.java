import domain.EmissionOffence;
import domain.Offence;
import domain.SpeedingOffence;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by jorden on 2-8-2017.
 */
public class Generator {
    public Generator() {
    }

    private int[] maxSpeedList = {30,50,70,120};
    private String[] cityList = {"Antwerpen", "Brussel", "Mechelen", "Aartselaar", "Leuven", "Lier", "Bornem"};
    private String[] licensePlateList = {"1-BSR-328", "1-RBD-285", "1-AKD-845", "2-ASF-247", "1-DLJ-458", "1-MKN-291", "1-PDO-746"};

    public Offence generate(int lowerBound,int upperBound) {
      Timestamp timestamp = generateTimestamp();
       String licenseplate = generateLicenseplate(licensePlateList);
      String street =  generateStreet();
        String city = generateCity(cityList);


        Offence offence;
        //kans op speedoffence
        if (Math.random() * 100 < 50) {
            int maxSpeed = generateMaxSpeed(maxSpeedList);
            int speed = generateSpeed(maxSpeed);
            offence = new SpeedingOffence(timestamp, licenseplate, street, city, maxSpeed, speed);
        }
        else {
            int mineuronorm = generateMinEuronorm();
            int euronorm =  generateEuronorm(mineuronorm);

             offence = new EmissionOffence(timestamp,licenseplate,street,city,mineuronorm,euronorm);
        }

        return offence;
    }

    private int generateSpeed(int maxSpeed) {
        Random r = new Random();
        int min = maxSpeed + 1;
        int max = maxSpeed + 50;
        return r.nextInt(max-min) + min;

    }

    private String generateCity(String[] cityList) {
        int random = new Random().nextInt(cityList.length);
        return cityList[random];
    }

    private String generateLicenseplate(String[] licensePlateList) {
        int random = new Random().nextInt(licensePlateList.length);
        return licensePlateList[random];
    }

    private int generateMaxSpeed(int[] maxSpeedList) {
        int random = new Random().nextInt(maxSpeedList.length);
        return maxSpeedList[random];
    }


    private String generateStreet() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 100; i++) {
                char c = chars[r.nextInt(chars.length)];
                sb.append(c);
            }
            return  sb.toString();
    }

    private int generateMinEuronorm() {
        Random r = new Random();
        int min = 3;
        int max = 7;
        int result = r.nextInt(max-min) + min;
        return result;
    }

    private int generateEuronorm(int mineuronorm) {
        Random r = new Random();
        int min = 1;
        int max = mineuronorm;
        int result = r.nextInt(max-min) + min;
        return  result;
    }

    private Timestamp generateTimestamp() {
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp timestamp = new Timestamp(offset + (long)(Math.random() * diff));
        return  timestamp;
    }

    
    /*public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            //mineuronorm
            Random r = new Random();
            int min = 3;
            int max = 7;
            int result = r.nextInt(max-min) + min;
            long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
            long diff = end - offset + 1;

            //timestamp
            Timestamp timestamp = new Timestamp(offset + (long)(Math.random() * diff));
            System.out.println(result + "timestamp" + timestamp);



        }
        //street
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);

        //maxspeed
         int[] maxSpeedList = {30,50,70,120};
        int r = new Random().nextInt(maxSpeedList.length);
        System.out.println(maxSpeedList[r]);

    }*/

}
