package com.allogica.musicJPAPractice.Model.Auxiliaries;

import java.util.List;
import java.util.Scanner;

public class ReceiveSpecificInteger {

    public static Scanner keyboard = new Scanner(System.in);

    public static Integer receiveInteger(int minNumber, int maxNumber) {

        int number = -1;
        do {
            System.out.println("Please enter a integer number between " + minNumber + " and " + maxNumber + ": ");
            try {
                number = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number.");
            }
        } while (number > maxNumber || number < minNumber);
        return number;
    }

    public static Long receiveLong(List<Long> numbers) {
        long number = -1L;
        do {
            System.out.println("Please enter one of the Ids in the list: ");
            try {
                number = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number.");
            }
        }
        while (!numbers.contains(number));
        return number;
    }
}
