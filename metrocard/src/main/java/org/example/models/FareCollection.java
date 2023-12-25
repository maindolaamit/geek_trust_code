package org.example.models;

import org.example.constants.PassengerType;

import java.util.HashMap;

public class FareCollection {
    private double fareAmount;
    private double feeAmount;
    private double discountAmount;
    private HashMap<PassengerType, Integer> passengerCountMap;

    // constructor
    public FareCollection() {
        this.fareAmount = 0.0;
        this.feeAmount = 0.0;
        this.discountAmount = 0.0;
        this.passengerCountMap = new HashMap<>();
        for (PassengerType passengerType : PassengerType.values()) {
            this.passengerCountMap.put(passengerType, 0);
        }
    }

    public void addCollection(double fareAmount) {
        this.fareAmount += fareAmount;
    }

    public void addFee(double feeAmount) {
        this.feeAmount += feeAmount;
    }

    public void addDiscount(double discountAmount) {
        this.discountAmount += discountAmount;
    }

    public void addPassenger(PassengerType passenger) {
        int count = this.passengerCountMap.get(passenger);
        this.passengerCountMap.put(passenger, count + 1);
    }

    public double getDiscount() {
        return discountAmount;
    }

    public HashMap<PassengerType, Integer> getPassegerCountMap() {
        return passengerCountMap;
    }

    public double getTotalFareAmount() {
        return this.fareAmount + this.feeAmount;
    }
}
