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
            // read the file
            BufferedReader br = new BufferedReader(file);
            while (br.readLine() != null) {
                processInput(br.readLine());
            }

        } catch (IOException e) {
            System.out.println("File not found");
        }
        // process the file
        System.out.println("Hello world!");
    }

    private static void processInput(String command) {
        String[] commandParts = command.split("\\s+");
        String commandType = commandParts[0];
        String cardNumber = commandParts[1];
        switch (commandType) {
            case "BALANCE" -> {
                int amount = Integer.parseInt(commandParts[2]);
                metroService.rechargeCard(cardNumber, amount);
            }
            case "CHECK_IN" -> {
                String passengerType = commandParts[2];
                String station = commandParts[3];
                metroService.checkIn(cardNumber, passengerType, station);
            }
            case "PRINT_SUMMARY" -> metroService.printSummary();
        }
    }
}