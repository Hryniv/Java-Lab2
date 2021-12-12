import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private int sum = 0;
    String[] numbersArray;
    String delimiter = "[\n,]";

    public int Add(String numbers) {
        switch (numbers.length()) {
            case 0:
                return sum;
            case 1 :
                return Integer.parseInt(numbers);
            default: {
                if (numbers.contains(",\n")) {
                    throw new RuntimeException("Invalid input");
                }

                if (Pattern.matches("//.+\n.+", numbers)) {

                    Pattern pattern = Pattern.compile("//.+\n", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(numbers);
                    int startSubstring = 0;

                    while (matcher.find()) {

                        startSubstring = matcher.end();
                        delimiter = numbers.substring(matcher.start() + 2, matcher.end() - 1);

                    }
                    numbersArray = numbers.substring(startSubstring).split(delimiter);

                }
                else {
                    numbersArray = numbers.split(delimiter);
                }

                for (String number: numbersArray) {
                    sum += Integer.parseInt(number);
                }
                return sum;
            }
        }
    }
}
