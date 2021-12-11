public class Calculator {
    private int sum = 0;
    private String REGEX_1 = "\n";

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
                String[] numbersArray = numbers.split("[\n,]");
                for (String number: numbersArray) {
                    sum += Integer.parseInt(number);
                }
                return sum;
            }
        }
    }
}
