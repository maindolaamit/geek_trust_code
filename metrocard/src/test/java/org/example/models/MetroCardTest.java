package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetroCardTest {

    @Test
    void check_balance() {
        MetroCard metroCard = new MetroCard("MC100",100);
        assertEquals(100, metroCard.getBalance());
        metroCard.swipeCard(10);
        metroCard.swipeCard(10);
        assertEquals(80, metroCard.getBalance());
        metroCard.addBalance(100);
        assertEquals(180, metroCard.getBalance());
        assertEquals(2, metroCard.getTripCount());
    }
}