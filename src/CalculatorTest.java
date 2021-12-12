import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void EmptyStringTest() {
        String emptyString = "";
        Assert.assertEquals(calculator.Add(emptyString), 0);
    }

    @Test
    void OneElementStringTest() {
        String numbers1 = "7";
        Assert.assertEquals(calculator.Add(numbers1), 7);
    }

    @Test
    void TwoElementStringTest() {
        String numbers2 = "7,9";
        Assert.assertEquals(calculator.Add(numbers2), 16);
    }

    @Test
    void UnknownAmountOfNumberTest() {
        String numbers = "1,2,3,4,5,6,7,8,9,10";
        Assert.assertEquals(calculator.Add(numbers), 55);
    }

    @Test
    void StringNewlineBetweenNumbersTest () {
        String numbers = "1,2\n3,4";
        Assert.assertEquals(calculator.Add(numbers), 10);
    }

    @Test
    public void InvalidInputTest() {
        String numbers = "1,2,\n4";
        try {
            calculator.Add(numbers);
        } catch (RuntimeException e) {
            Assert.assertNotNull(e.getMessage(), "Your exception should have a description message\n");
            return;
        }
        Assert.fail("You should throw an " +
                "exception with message " +
                "Invalid input");
    }

    @Test
    public void DifferentDelimitersTest() {
        String numbers = "//;\n1;3;2;4";
        Assert.assertEquals(calculator.Add(numbers), 10);
    }

}