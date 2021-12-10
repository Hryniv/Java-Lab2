public class Calculator {
    private int sum = 0;

    public int Add(String numbers) {


        switch (numbers.length()) {
            case 0:
                return sum;
            case 1 :
                return Integer.parseInt(numbers);
            default: {

                String[] numbersArray = numbers.split(",");
                for (String number: numbersArray) {
                    sum += Integer.parseInt(number);
                }
                    return sum;
            }
        }
    }
}
