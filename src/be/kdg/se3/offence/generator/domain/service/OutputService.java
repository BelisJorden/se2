package be.kdg.se3.offence.generator.domain.service;

import be.kdg.se3.offence.generator.domain.entity.Offence;

/**
 * Created by jorden on 21-8-2017.
 */
public interface OutputService {

    void publish(Offence offence);
}
