package org.example;

import org.example.service.MetroService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final MetroService metroService = new MetroService();

    public static void main(String[] args) {
        // input is a file
        try {
            FileReader file = new FileReader(args[0]);
            // read input from file
            String line = null;
            BufferedReader br = new BufferedReader(file);
            while ((line = br.readLine()) != null) {
                processInput(line);
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private static void processInput(String command) {
        if (command == null) return;
        String[] commandParts = command.split("\\s+");
        String commandType = commandParts[0];
        if (commandType.equals("BALANCE")) {
            String cardNumber = commandParts[1];
            int amount = Integer.parseInt(commandParts[2]);
            metroService.rechargeCard(cardNumber, amount);
        } else if (commandType.equals("CHECK_IN")) {
            String cardNumber = commandParts[1];
            String passengerType = commandParts[2];
            String station = commandParts[3];
            metroService.checkIn(cardNumber, passengerType, station);
        } else if (command.equals("PRINT_SUMMARY")) metroService.printSummary();
    }
}