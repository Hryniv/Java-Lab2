import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @AfterEach
    public void clear()
    {
        Calculator.delimiter = new StringBuilder(",|\n");
    }

    @Test
    public void EmptyStringTest_Ok() {
        String emptyString = "";
        Assert.assertEquals(Calculator.add(emptyString), 0);
    }

    @Test
    public void OneElementStringTest_Ok() {
        String numbers1 = "1";
        Assert.assertEquals(Calculator.add(numbers1), 1);
    }

    @Test
    public void TwoElementStringTest_Ok() {
        String numbers2 = "1,2";
        Assert.assertEquals(Calculator.add(numbers2), 3);
    }

    @Test
    public void UnknownAmountOfNumberTest() {
        String numbers = "1,2,3,4,5,6,7,8,9,10";
        Assert.assertEquals(Calculator.add(numbers), 55);
    }

    @Test
    public void NewlineBetweenNumbersTest_Ok() {
        String numbers = "1,2\n3,4";
        Assert.assertEquals(Calculator.add(numbers), 10);
    }

    @Test
    public void NewlineBetweenNumbersTest_NotOk() {
        String numbers = "1,2,\n4";
        try {
            Calculator.add(numbers);
        } catch (RuntimeException e) {
            Assert.assertNotNull(e.getMessage(), "Your exception should have a description message\n");
            return;
        }
        Assert.fail("You should throw an " +
                "exception with message " +
                "Invalid input");
    }

    @Test
    public void DifferentDelimitersTest_Ok() {
        String numbers = "//*\n1*3*2*4";
        Assert.assertEquals(Calculator.add(numbers), 10);
    }

    @Test
    public void AllNegativesNumbersTest_NotOk() {
        String numbers = "-1,-2,-3,-5,-2,-9";
        try {
            Calculator.add(numbers);
        }   catch (NegativeNumberException e) {
            Assert.assertEquals(e.getMessage(), "Negative is not allow : -1,-2,-3,-5,-2,-9");
            return;
        }
        fail();
    }

    @Test
    public void NegativeNumberTest_NotOk() {
        String numbers = "1,2,3,-5,2";
        try {
            Calculator.add(numbers);
            fail("You should throw an Exception");
        } catch (NegativeNumberException e) {
            Assert.assertEquals(e.getMessage(), "Negative is not allow : -5");
        }
    }

    @Test
    public void NumberBiggerThan1000Test_Ok() {
        String numbers = "1001,100,10,1";
        Assert.assertEquals(Calculator.add(numbers), 111);
    }

    @Test
    public void LongDelimiterTest_Ok() {
        String numbers = "//[%%%]\n4%%%6%%%7%%%9";
        Assert.assertEquals(Calculator.add(numbers), 26);
    }

    @Test
    public void MultipleDelimitersTest_Ok() {
        String numbers = "//[%%%][%%][%]\n1%2%%3%%%4%5";
        Assert.assertEquals(Calculator.add(numbers), 15);
    }
    @Test
    public void TwoLongDelimiterAndDefTest_Ok() {
        String numbers = "//[*][###]\n1*2###3###4*5###6";
        Assert.assertEquals(Calculator.add(numbers), 21);
    }
}