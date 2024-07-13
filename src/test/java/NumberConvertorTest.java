import NumberConvertor.INumberConvertor;
import NumberConvertor.NumberConvertor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class NumberConvertorTest {

    INumberConvertor numberConvertor;

    public NumberConvertorTest() {
        numberConvertor = new NumberConvertor();
    }

    @Test
    public void testSampleInput() {
        assertEquals("fifty-two", numberConvertor.convertNumberToWords(52));
        assertEquals("one thousand", numberConvertor.convertNumberToWords(1000));
        assertEquals("one hundred and one", numberConvertor.convertNumberToWords(101));
        assertEquals("three hundred and fifty-two", numberConvertor.convertNumberToWords(352));
        assertEquals("twelve thousand, three hundred", numberConvertor.convertNumberToWords(12300));
        assertEquals("twelve thousand and fifty-five", numberConvertor.convertNumberToWords(12055));
        assertEquals("twelve thousand, three hundred and forty-five", numberConvertor.convertNumberToWords(12345));
    }

    @Test
    public void testConvertLessThanOneThousand() {
        assertEquals("one", numberConvertor.convertNumberToWords(1));
        assertEquals("ten", numberConvertor.convertNumberToWords(10));
        assertEquals("twenty-one", numberConvertor.convertNumberToWords(21));
        assertEquals("ninety-nine", numberConvertor.convertNumberToWords(99));
        assertEquals("one hundred", numberConvertor.convertNumberToWords(100));
        assertEquals("one hundred and one", numberConvertor.convertNumberToWords(101));
        assertEquals("five hundred and fifty-five", numberConvertor.convertNumberToWords(555));
        assertEquals("nine hundred and ninety-nine", numberConvertor.convertNumberToWords(999));
    }

    @Test
    public void testConvertGreaterThanOneThousand() {
        assertEquals("one thousand",numberConvertor.convertNumberToWords(1000));
        assertEquals("one thousand and one", numberConvertor.convertNumberToWords(1001));
        assertEquals("ten thousand", numberConvertor.convertNumberToWords(10000));
        assertEquals("ten thousand and one", numberConvertor.convertNumberToWords(10001));
        assertEquals("one hundred thousand", numberConvertor.convertNumberToWords(100000));
    }

    @Test
    public void testExceptionHandling() {
        assertThrows(IllegalArgumentException.class, ()-> NumberToWordsCLI.main(new String[]{"-1"}));
        assertThrows(IllegalArgumentException.class, ()-> NumberToWordsCLI.main(new String[]{"a"}));
        assertThrows(RuntimeException.class, ()-> NumberToWordsCLI.main(new String[]{}));
    }
}
