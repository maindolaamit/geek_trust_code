package org.example.service;

import org.example.constants.MetroStation;
import org.example.constants.PassengerType;
import org.example.models.FareCollection;
import org.example.repository.MetroRepository;

import java.util.Map;

public class MetroService {
    private final MetroRepository repository = new MetroRepository();

    public static int getFareAmount(PassengerType passenger) {
        int amount = 0;
        if (passenger == PassengerType.ADULT) {
            amount = 200;
        } else if (passenger == PassengerType.KID) {
            amount = 50;
        } else if (passenger == PassengerType.SENIOR_CITIZEN) {
            amount = 100;
        }
        return amount;
    }

    public void rechargeCard(String cardNumber, int amount) {
        repository.rechargeCard(cardNumber, amount);
    }

    public void checkIn(String cardNumber, String passengerType, String station) {
        PassengerType passenger = PassengerType.valueOf(passengerType);
        int fareAmount = getFareAmount(passenger);
        repository.checkInUser(cardNumber, MetroStation.valueOf(station), passenger, fareAmount);
    }

    public void printSummary() {
        // print the summary
        StringBuilder summary = new StringBuilder();
        // group by Station
        for (MetroStation station : MetroStation.values()) {
            FareCollection collection = repository.getStationCollection(station);
            summary.append("TOTAL_COLLECTION ").append(station.name()).append(" ")
                    .append((int)collection.getTotalFareAmount()).append(" ").append((int)collection.getDiscount()).append("\n");
            summary.append("PASSENGER_TYPE_SUMMARY").append("\n");
            // loop over the passenger types for the station
            for (Map.Entry<PassengerType, Integer> entry : collection.getPassegerCountMap().entrySet()) {
                summary.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
            }
        }
        System.out.println(summary);
    }
}
