package NumberConvertor;
import java.text.DecimalFormat;

public class NumberConvertor implements INumberConvertor {

    private static final String[] TENS = {
            "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] UNITS = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
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
        // 0 to 999,999,999,999
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

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions, false) + " billion, ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions, false) + " million, ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands, false) + " thousand, ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        if (Integer.parseInt(snumber.substring(9, 10)) == 0 & (billions > 0 || millions > 0 || hundredThousands > 0)) {
            result = result.replaceAll(",\\s$"," ");
            tradThousand = convertLessThanOneThousand(thousands, true);
        } else {
            tradThousand = convertLessThanOneThousand(thousands, false);

        }
        result =  result + tradThousand;

        // remove extra spaces
        result = result.replaceAll("^\\s+", "")
                .replaceAll("\\b\\s{2,}\\b", " ").trim();
        return result.replaceAll(",\\s*$", "");
    }
}
