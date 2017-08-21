package be.kdg.se3.offence.generator.domain;

import be.kdg.se3.offence.generator.domain.entity.Offence;

import java.sql.Timestamp;

/**
 * Created by jorden on 21-8-2017.
 */
public class EmissionOffence extends Offence {

    private int minEuronorm;
    private int euronorm;

    public EmissionOffence(Timestamp timestamp, String licencePlate, String street, String city, int minEuronorm, int euronorm) {
        super(timestamp, licencePlate, street, city);
        this.minEuronorm = minEuronorm;
        this.euronorm = euronorm;
    }

    public int getMinEuronorm() {
        return minEuronorm;
    }

    public int getEuronorm() {
        return euronorm;
    }

    @Override
    public String toString() {
        return "EmissionOffence{" +
                "minEuronorm=" + minEuronorm +
                ", euronorm=" + euronorm +
                '}' + super.toString();
    }
}
