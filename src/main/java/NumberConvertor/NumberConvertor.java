package NumberConvertor;
import java.text.DecimalFormat;

public class NumberConvertor implements INumberConvertor {

    private static final String[] UNITS = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private String convertLessThanOneThousand(int number, boolean needsAnd) {
        String current;

        if (number % 100 < 20) {
            current = UNITS[number % 100];
            number /= 100;
        } else {
            current = UNITS[number % 10];
            number /= 10;

            //current = tensNames[number % 10] + current;
            current = TENS[number % 10] + (number % 10 != 0 ? "-" + current : "");
            number /= 10;
        }
        if (number == 0){
            if (needsAnd && current != null && !current.isBlank()) {
                return "and " + current.trim();
            } else {
                return current.trim();
            }
        }

        return UNITS[number] + " hundred" + (current.isEmpty() ? "" : " and " + current);
    }

    @Override
    public String convertNumberToWords(long number) {
        if (number == 0) {
            return "zero";
        }

        String snumber;

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9, 12));

        String parseBillions;
        switch (billions) {
            case 0:
                parseBillions = "";
                break;
            default :
                parseBillions = convertLessThanOneThousand(billions, false) + " billion, ";
        }
        String result =  parseBillions;

        String parseMillions;
        switch (millions) {
            case 0:
                parseMillions = "";
                break;
            default :
                parseMillions = convertLessThanOneThousand(millions, false) + " million, ";
        }
        result =  result + parseMillions;

        String parseHundredThousands;
        switch (hundredThousands) {
            case 0:
                parseHundredThousands = "";
                break;
            default :
                parseHundredThousands = convertLessThanOneThousand(hundredThousands, false) + " thousand, ";
        }
        result =  result + parseHundredThousands;

        String parseThousand;
        if (Integer.parseInt(snumber.substring(9, 10)) == 0 & (billions > 0 || millions > 0 || hundredThousands > 0)) {
            result = result.replaceAll(",\\s$"," ");
            parseThousand = convertLessThanOneThousand(thousands, true);
        } else {
            parseThousand = convertLessThanOneThousand(thousands, false);

        }
        result =  result + parseThousand;

        // remove extra spaces
        result = result.replaceAll("^\\s+", "")
                .replaceAll("\\b\\s{2,}\\b", " ").trim();
        return result.replaceAll(",\\s*$", "");
    }
}
