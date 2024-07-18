import NumberConvertor.*;

public class NumberToWordsCLI {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Invalid input, arguments must be size 1");
        }

        try {
            INumberConvertor convertor = new NumberConvertor();
            String input = args[0];
            long number = Long.parseLong(input);
            if (number < 0 || number > 1000000000000L) {
                throw new IllegalArgumentException("Number out of range. Please enter a number between 0 and 1,000,000,000,000.");
            }

            String words = convertor.convertNumberToWords(number);
            System.out.println(words);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
        }
    }
}