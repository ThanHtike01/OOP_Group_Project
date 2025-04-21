import java.util.Scanner;

//To handle console input from the user
public class InputHandler {
    // Single shared Scanner instance for consistent input reading
    private static final Scanner scanner = new Scanner(System.in);

    // Gets a line of string input
    public static String getStringInput(String prompt) {
        String input;
        do {
            System.out.print(prompt); // Display the prompt to the user
            input = scanner.nextLine().trim(); // Read the whole line and remove leading/trailing spaces
        
            // If the user enters only spaces or presses Enter, input becomes empty
            // This loop ensures they must enter some visible characters
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty()); 
        return input;
    }

    // Gets a validated integer input
    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim(); // read full line and trim spaces
            if (input.matches("\\d+")) { // check if it's all digits
                return Integer.parseInt(input);
            }
            System.out.println("Please enter a valid number.");
        }
    }

    // Gets numeric string input
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
