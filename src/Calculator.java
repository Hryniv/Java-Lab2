import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {
    public static StringBuilder delimiter = new StringBuilder(",|\n");
    public static final String inv_delimiter = ",\n";

    public static int add(String numbers)
    {
        if (numbers.isEmpty())
        {
            return 0;
        }
        if (numbers.contains(inv_delimiter))
        {
            throw new RuntimeException("Invalid delimiter - " + inv_delimiter);
        }

        if (numbers.startsWith("//"))
        {
            String[] elements = numbers.split("\n", 2);
            expand_delimiter(elements[0].substring(2));
            numbers = elements[1];
        }

        negativeNumbersCheck(Arrays.stream(numbers.split(delimiter.toString()))
                .map(Integer::parseInt)
                .filter(i -> i < 0)
                .map(String::valueOf));

        return Arrays.stream(numbers.split(delimiter.toString()))
                .mapToInt(Integer::parseInt)
                .filter(i -> i <= 1000)
                .sum();
    }

    private static void negativeNumbersCheck(Stream<String> stream)
    {
        String negativeNumbers = stream.collect(Collectors.joining(","));
        if (!negativeNumbers.isEmpty())
        {
            throw new NegativeNumberException("Negative is not allow : "
                    + negativeNumbers);
        }
    }

    private static void expand_delimiter(String delimiters)
    {
        System.out.println(delimiter.append("|")
                .append(Arrays.stream(delimiters.split("]"))
                        .map(i -> i.contains("[") ? i.substring(1) : i)
                        .map(Calculator::danglingMetaCharacterToSimple)
                        .collect(Collectors.joining("|"))));
    }

    private static String danglingMetaCharacterToSimple(String characters) {
        StringBuilder builder = new StringBuilder();
        for (char c: characters.toCharArray()) {
            builder.append("\\").append(c);
        }
        return builder.toString();
    }
}
