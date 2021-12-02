import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        long firstNumber = scanner.nextLong();
        String operation = scanner.next();
        long secondNumber = scanner.nextLong();

        switch (operation) {
            case "+":
                System.out.println(firstNumber + secondNumber);
                break;
            case "-":
                System.out.println(firstNumber - secondNumber);
                break;
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Division by 0!");
                } else {
                    System.out.println(firstNumber / secondNumber);
                }
                break;
            case "*":
                System.out.println(firstNumber * secondNumber);
                break;
            default:
                System.out.println("Unknown operator");
                break;
        }
    }
}