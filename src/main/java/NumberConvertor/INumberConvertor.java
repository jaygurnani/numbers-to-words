package NumberConvertor;

public interface INumberConvertor {
    String convertNumberToWords(int number);

    String convertLessThanOneThousand(int number);
}