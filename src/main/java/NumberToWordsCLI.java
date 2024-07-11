import NumberConvertor.*;

import java.util.Scanner;

public class NumberToWordsCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        INumberConvertor convertor = new NumberConvertor();

        while (true) {
            System.out.print("Enter a number (0 to 100000) or 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                int number = Integer.parseInt(input);
                if (number < 0 || number > 100000) {
                    System.out.println("Number out of range. Please enter a number between 0 and 100000.");
                    continue;
                }

                String words = convertor.convertNumberToWords(number);
                System.out.println("Number in words: " + words);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        scanner.close();
    }
}