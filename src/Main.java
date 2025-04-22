import exception.*;

// Main class / entry point
public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        boolean exit = false;

        // Load saved books and members from file
        manager.loadData();

        // Main loop to keep the program running until the user exits
        while (!exit) {
            //Display the main menu
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add School Book");
            System.out.println("3. Register Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. List All Books");
            System.out.println("7. List All Members");
            System.out.println("8. Delete Member");
            System.out.println("9. Delete Book");
            System.out.println("10. Save Data to File");
            System.out.println("11. Exit");

            int choice = InputHandler.getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    String title1 = InputHandler.getStringInput("Enter book title: ");
                    String author = InputHandler.getStringInput("Enter author: ");
                    String bookId = String.format("%03d", LibraryManager.getNextItemId()); //Format ID like "001"
                    Book newBook = new Book(bookId, title1, author);
                    manager.addBook(newBook);
                    System.out.println(newBook.getDetails()); //Confirm book detail
                    break;

                case 2:
                    String title2 = InputHandler.getStringInput("Enter school book title: ");
                    String subject = InputHandler.getStringInput("Enter subject: ");
                    String schoolBookId = String.format("%03d", LibraryManager.getNextItemId());
                    SchoolBook schoolBook = new SchoolBook(schoolBookId, title2, subject);
                    manager.addBook(schoolBook); 
                    System.out.println(schoolBook.getDetails() + " [Available]");
                    break;

                case 3:
                    String name = InputHandler.getStringInput("Enter name: ");
                    Member newMember = new Member(name);
                    manager.registerMember(newMember);
                    System.out.println("Member ID: " + newMember.getMemberId() + ", Name: " + newMember.getName());
                    break;

                case 4:
                    manager.showBookList();
                    manager.showMemberList();
                    String borrowId = InputHandler.getValidatedNumericInput("Enter item ID to borrow: ");
                    String borrowerId = InputHandler.getValidatedNumericInput("Enter your member ID: ");
                    try{
                        manager.borrowItem(borrowId, borrowerId);
                    } catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    manager.showMemberList();
                    String returnId = InputHandler.getValidatedNumericInput("Enter item ID to return: ");
                    String returnerId = InputHandler.getValidatedNumericInput("Enter your member ID: ");
                    try{
                        manager.returnItem(returnId, returnerId);
                    } catch(ItemNotFoundException | MemberNotFoundException e ){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    manager.showBookList();
                    break;

                case 7:
                    manager.showMemberList();
                    break;

                case 8:
                    manager.showMemberList();
                    String delMemberId = InputHandler.getValidatedNumericInput("Enter member ID to delete: ");
                    try{
                        manager.deleteMember(delMemberId);
                    } catch(MemberNotFoundException e ){
                        System.out.println("Error: " + e.getMessage());
                    }
                    
                    break;

                case 9:
                    manager.showBookList();
                    String delBookId = InputHandler.getValidatedNumericInput("Enter book ID to delete: ");
                    try{
                        manager.deleteBook(delBookId);
                    } catch(ItemNotFoundException e ){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 10:
                    manager.showBookList();
                    manager.showMemberList();
                    manager.saveData();
                    System.out.println("Data saved to file.");
                    break;

                // Exit the program with confirmation on whether to save data
                case 11:
                int saveOption;
                do {
                    saveOption = InputHandler.getIntInput("Before Exit. 1. Save data.. 2. Don't save data... ");
                    if (saveOption != 1 && saveOption != 2) {
                        System.out.println("Please enter 1 or 2.");
                    }
                } while (saveOption != 1 && saveOption != 2);
                    if (saveOption == 1) {
                        manager.saveData();
                    }
                    System.out.println("Goodbye!");
                    exit = true; //End the program
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}