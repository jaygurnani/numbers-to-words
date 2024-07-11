import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberConvertorTest {


    @Test
    public void testConvertLessThanOneThousand() {
        assertEquals("one", NumberToWordsCLI.convertLessThanOneThousand(1));
        assertEquals("ten", NumberToWordsCLI.convertLessThanOneThousand(10));
        assertEquals("twenty one", NumberToWordsCLI.convertLessThanOneThousand(21));
        assertEquals("ninety nine", NumberToWordsCLI.convertLessThanOneThousand(99));
        assertEquals("one hundred", NumberToWordsCLI.convertLessThanOneThousand(100));
        assertEquals("one hundred one", NumberToWordsCLI.convertLessThanOneThousand(101));
        assertEquals("five hundred fifty five", NumberToWordsCLI.convertLessThanOneThousand(555));
        assertEquals("nine hundred ninety nine", NumberToWordsCLI.convertLessThanOneThousand(999));
    }

}
