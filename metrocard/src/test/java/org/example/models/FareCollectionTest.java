package org.example.models;

import org.example.constants.PassengerType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FareCollectionTest {

    @Test
    void test_collection_fare() {
        FareCollection collection = new FareCollection();
        collection.addCollection(100);
        collection.addFee(10);
        collection.addDiscount(20);
        collection.addPassenger(PassengerType.ADULT);
        collection.addPassenger(PassengerType.ADULT);
        collection.addPassenger(PassengerType.KID);
        collection.addPassenger(PassengerType.KID);
        collection.addPassenger(PassengerType.SENIOR_CITIZEN);
        assertEquals(110, collection.getTotalFareAmount());
        assertEquals(20, collection.getDiscount());
        HashMap<PassengerType, Integer> passegerCountMap = collection.getPassegerCountMap();
        assertEquals(2, passegerCountMap.get(PassengerType.ADULT));
        assertEquals(2, passegerCountMap.get(PassengerType.ADULT));
        assertEquals(1, passegerCountMap.get(PassengerType.SENIOR_CITIZEN));
    }
}