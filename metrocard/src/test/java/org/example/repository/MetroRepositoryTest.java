package org.example.repository;

import org.example.constants.MetroStation;
import org.example.constants.PassengerType;
import org.example.models.FareCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetroRepositoryTest {
    private MetroRepository metroRepository;

    @BeforeEach
    void setUp() {
        metroRepository = new MetroRepository();
        metroRepository.rechargeCard("MC100", 100);
        metroRepository.rechargeCard("MC101", 100);
        metroRepository.rechargeCard("MC102", 100);
        metroRepository.checkInUser("MC100", MetroStation.CENTRAL, PassengerType.ADULT, 200);
        metroRepository.checkInUser("MC100", MetroStation.AIRPORT, PassengerType.ADULT, 200);
        metroRepository.checkInUser("MC101", MetroStation.AIRPORT, PassengerType.SENIOR_CITIZEN, 100);
        metroRepository.checkInUser("MC101", MetroStation.CENTRAL, PassengerType.SENIOR_CITIZEN, 100);
        metroRepository.checkInUser("MC102", MetroStation.AIRPORT, PassengerType.KID, 50);
    }

    @Test
    void getStationCollection() {
        FareCollection collection = metroRepository.getStationCollection(MetroStation.AIRPORT);
        assertEquals(0, metroRepository.getCardBalance("MC100"));
        assertEquals(0, metroRepository.getCardBalance("MC101"));
        assertEquals(50.0, metroRepository.getCardBalance("MC102"));
    }
}