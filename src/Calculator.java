public class Calculator {

    public int Add(String numbers) {
        switch (numbers.length()) {
            case 0:
                return 0;
            case 1 :
                return Integer.parseInt(numbers);
            default: {
                String[] numbersArray = numbers.split(",");
                return Integer.parseInt(numbersArray[0]) + Integer.parseInt(numbersArray[1]);
            }
        }
    }
}
