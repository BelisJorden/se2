package domain;

import java.sql.Timestamp;

/**
 * Created by jorden on 2-8-2017.
 */
public class Offence {
    private Timestamp timestamp;
    private String licencePlate;
    private String street;
    private String city;

    public Offence(Timestamp timestamp, String licencePlate, String street, String city) {
        this.timestamp = timestamp;
        this.licencePlate = licencePlate;
        this.street = street;
        this.city = city;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "domain.Offence{" +
                "timestamp=" + timestamp +
                ", licencePlate='" + licencePlate + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
