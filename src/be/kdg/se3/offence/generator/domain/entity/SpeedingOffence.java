package be.kdg.se3.offence.generator.domain.entity;

import java.sql.Timestamp;

/**
 * Created by jorden on 2-8-2017.
 */
public class SpeedingOffence extends be.kdg.se3.offence.generator.domain.entity.Offence {
    private int maxSpeed;
    private int speed;

    public SpeedingOffence(Timestamp timestamp, String licencePlate, String street, String city, int maxSpeed, int speed) {
        super(timestamp, licencePlate, street, city);
        this.maxSpeed = maxSpeed;
        this.speed = speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return  "SpeedingOffence{" +
                "maxSpeed=" + maxSpeed +
                ", speed=" + speed +
                '}' + super.toString();
    }
}
