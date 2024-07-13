package NumberConvertor;


import static NumberConvertor.Helpers.NameLookup.*;

public class NumberConvertor implements INumberConvertor {

    @Override
    public String convertNumberToWords(int number) {
        if (number == 0) {
            return "zero";
        }
        if (number < 20) {
            return UNITS[number];
        }
        if (number < 100) {
            return TENS[number / 10] + (number % 10 != 0 ? "-" + UNITS[number % 10] : "");
        }
        if (number < 1000) {
            return UNITS[number / 100] + " hundred" +
                    (number % 100 != 0 ? " and " +
                            // Convert the tens position
                            convertNumberToWords(number % 100) : "");
        }
        if (number < 1000000) {
            // Convert the thousands position with just the number
            return convertNumberToWords(number / 1000) + " thousand" +
                    (number % 1000 != 0 ? (number % 1000 < 100 ? " and " : ", ") +
                            // Convert the hundreds position
                            convertNumberToWords(number % 1000) : "");
        }
        return "";
    }
}
