package org.example.repository;

import org.example.constants.MetroStation;
import org.example.constants.PassengerType;
import org.example.models.FareCollection;
import org.example.models.MetroCard;

import java.util.HashMap;

public class MetroRepository {
    private HashMap<String, MetroCard> userMap = new HashMap<>();
    private HashMap<MetroStation, FareCollection> collectMap = new HashMap<>();

    public void rechargeCard(String cardNumber, int amount) {
        if (!userMap.containsKey(cardNumber)) {
            userMap.put(cardNumber, new MetroCard(cardNumber, amount));
        } else {
            userMap.get(cardNumber).addBalance(amount);
        }
    }

    public int getCurrentTripCountForCard(String cardNumber) {
        if (userMap.containsKey(cardNumber)) {
            return userMap.get(cardNumber).getTripCount() + 1;
        }
        return 1;
    }

    public double getCardBalance(String cardNumber) {
        if (userMap.containsKey(cardNumber)) {
            return userMap.get(cardNumber).getBalance();
        }
        return 0;
    }

    public void checkInUser(String cardNumber, MetroStation metroStation, PassengerType passenger, int fareAmount) {
        // check the card balance and auto recharge if needed
        double fee = 0.0;
        int tripCount = getCurrentTripCountForCard(cardNumber);
        int discount = 0;
        // check the trip count of the user
        if (tripCount % 2 == 0) {
            discount = fareAmount / 2;
        }
        fareAmount = fareAmount - discount;
        // check the difference between the card balance and fare amount
        double diff = getCardBalance(cardNumber) - fareAmount;
        // Auto recharge the card if the balance is less than the fare amount
        if (diff < 0.0) {
            fee = Math.abs(diff * 0.02);
            diff = Math.abs(diff);
//            String format = String.format("%s - Insufficient balance %.2f auto recharge initiated with %.2f service fee",
//                    cardNumber, diff, fee);
//            System.out.println(format);
        } else diff = 0.0;

        // recharge the card, add fare, fee and discount to the collection and swipe the card
        swipeCard(cardNumber, metroStation, passenger, fareAmount, diff, fee, discount);
    }

    private void swipeCard(String cardNumber, MetroStation metroStation, PassengerType passenger,
                           double fareAmount, double diff, double fee, int discount) {
        rechargeCard(cardNumber, (int) diff);
        addCollection(metroStation, fareAmount);
        if (fee > 0) addFee(metroStation, Math.abs(fee));
        if (discount > 0) addDiscount(metroStation, discount);
        addPassenger(metroStation, passenger);
        // swipe card
        userMap.get(cardNumber).swipeCard(fareAmount);
    }

    private void addPassenger(MetroStation metroStation, PassengerType passenger) {
        if (collectMap.containsKey(metroStation)) {
            collectMap.get(metroStation).addPassenger(passenger);
        } else {
            FareCollection collection = new FareCollection();
            collection.addPassenger(passenger);
            collectMap.put(metroStation, collection);
        }
    }

    private void addDiscount(MetroStation station, double discountAmount) {
        if (collectMap.containsKey(station)) {
            collectMap.get(station).addDiscount(discountAmount);
        } else {
            FareCollection collection = new FareCollection();
            collection.addDiscount(discountAmount);
            collectMap.put(station, collection);
        }
    }

    private void addFee(MetroStation station, double feeAmount) {
        if (collectMap.containsKey(station)) {
            collectMap.get(station).addFee(feeAmount);
        } else {
            FareCollection collection = new FareCollection();
            collection.addFee(feeAmount);
            collectMap.put(station, collection);
        }
    }

    private void addCollection(MetroStation station, double fareAmount) {
        if (collectMap.containsKey(station)) {
            collectMap.get(station).addCollection(fareAmount);
        } else {
            FareCollection collection = new FareCollection();
            collection.addCollection(fareAmount);
            collectMap.put(station, collection);
        }
    }

    public FareCollection getStationCollection(MetroStation station) {
        FareCollection fareCollection = collectMap.get(station);
        // sort the PassengerType in the collection
        fareCollection.getPassegerCountMap().entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()));

        return fareCollection;
    }
}
