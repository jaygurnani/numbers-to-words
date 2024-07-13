package NumberConvertor;

import static NumberConvertor.Helpers.NameLookup.*;

public class NumberConvertor implements INumberConvertor {

    @Override
    public String convertNumberToWords(int number) {
        if (number == 0) {
            return "zero";
        }

        String words = "";
        int index = 0;
        int part;

        while (number > 0) {
            part = number % 1000;
            if (part != 0) {
                String partWords = convertLessThanOneThousand(part);
                words = partWords + " " + THOUSANDS[index] + " " + words;
            }
            index++;
            number = number / 1000;
        }

        return words.trim();
    }

    public String convertLessThanOneThousand(int number) {
        String current;

        if (number % 100 < 10) {
            current = UNITS[number % 100];
            number = number / 100;
        } else if (number % 100 < 20 && number % 100 > 10) {
            current = TEENS[number % 10];
            number = number / 100;
        } else {
            current = UNITS[number % 10];
            number = number / 10;

            current = TENS[number % 10] + " " + current;
            number = number / 10;
        }
        if (number == 0) {
            return current;
        }

        return UNITS[number] + " hundred " + current;
    }
}
