import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private int sum = 0;
    String[] numbersArray;
    String delimiter = "[\n,]";
    List<String> negativeNumbers = new ArrayList<>();

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

                numbersArray = getNumbersArray(numbers, delimiter);

                for (String number: numbersArray) {
                    int  current = Integer.parseInt(number);
                    if (current < 0) {
                        negativeNumbers.add(number);
                    }
                    sum += current > 1000 ? 0: current;
                }

                if (!negativeNumbers.isEmpty()) {
                    String message = "Negative not allow " + negativeNumbers.toString().replace("[", "").replace("]", "");
                    throw new NegativeNumberException(message);
                }

                return sum;
            }
        }
    }

    private static String[] getNumbersArray(String numbers, String delimiter) {
        String[] numbersArray;

        if (numbers.startsWith("//[")) {
            Pattern pattern = Pattern.compile("//\\[(.+)]\n(.+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(numbers);
            matcher.matches();

            delimiter = matcher.group(1);
            numbers = matcher.group(2);
            numbersArray = numbers.split(Pattern.quote(delimiter));

        } else if (numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.+)\n(.+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(numbers);
            matcher.matches();

            delimiter = matcher.group(1);
            numbers = matcher.group(2);
            numbersArray = numbers.split(Pattern.quote(delimiter));

        } else {
            numbersArray = numbers.split(delimiter);
        }

        return numbersArray;
    }
}
