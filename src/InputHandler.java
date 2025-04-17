import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next(); // discard invalid input
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    public static String getValidatedNumericInput(String prompt) {
        String input;
        do {
            input = getStringInput(prompt);
            if (!LibraryManager.isNumeric(input)) {
                System.out.println("ID must be numeric. Try again.");
            }
        } while (!LibraryManager.isNumeric(input));
        return input;
    }
}
