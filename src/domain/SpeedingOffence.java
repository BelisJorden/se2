package domain;

import domain.Offence;

import java.sql.Timestamp;

/**
 * Created by jorden on 2-8-2017.
 */
public class SpeedingOffence extends Offence {
    private int maxSpeed;
    private int speed;

    public SpeedingOffence(Timestamp timestamp, String licencePlate, String street, String city, int maxSpeed, int speed) {
        super(timestamp, licencePlate, street, city);
        this.maxSpeed = maxSpeed;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return  "SpeedingOffence{" +
                "maxSpeed=" + maxSpeed +
                ", speed=" + speed +
                '}' + super.toString();
    }
}
