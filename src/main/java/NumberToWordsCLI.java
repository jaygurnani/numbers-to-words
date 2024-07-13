import NumberConvertor.*;

public class NumberToWordsCLI {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Invalid input side");
        }

        try {
            INumberConvertor convertor = new NumberConvertor();
            String input = args[0];
            int number = Integer.parseInt(input);
            if (number < 0 || number > 100000) {
                throw new IllegalArgumentException("Number out of range. Please enter a number between 0 and 100000.");
            }

            String words = convertor.convertNumberToWords(number);
            System.out.println(words);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
        }
    }
}