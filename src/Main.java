public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        boolean exit = false;
        manager.loadData();
        while (!exit) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. List All Members");
            System.out.println("7. Save Data to File");
            System.out.println("8. Delete Book");
            System.out.println("9. Delete Member");
            System.out.println("10. Exit");

            int choice = InputHandler.getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    String title1 = InputHandler.getStringInput("Enter book title: ");
                    String author = InputHandler.getStringInput("Enter author: ");
                    manager.addBook(new Book(title1, author));
                    break;

                case 2:
                    // String memberId = InputHandler.getStringInput("Enter member ID: ");
                    String name = InputHandler.getStringInput("Enter name: ");
                    manager.registerMember(new Member(name));
                    break;

                case 3:
                    String borrowId = InputHandler.getStringInput("Enter item ID to borrow: ");
                    String borrowerId = InputHandler.getStringInput("Enter your member ID: ");
                    manager.borrowItem(borrowId, borrowerId);
                    break;
                    // try {
                    //     String borrowId = InputHandler.getStringInput("Enter item ID to borrow: ");
                    //     String borrowerId = InputHandler.getStringInput("Enter your member ID: ");
                    //     manager.borrowItem(borrowId, borrowerId);
                    // } catch (NumberFormatException e) {
                    //     System.out.println("Invalid input. IDs must be numbers.");
                    // }
                    // break;

                case 4:
                    String returnId = InputHandler.getStringInput("Enter item ID to return: ");
                    String returnerId = InputHandler.getStringInput("Enter your member ID: ");
                    manager.returnItem(returnId, returnerId);
                    break;

                case 5:
                    manager.listItems();
                    break;

                case 6:
                    manager.listMembers();
                    break;

                case 7:
                    manager.saveData();
                    System.out.println("Data saved to file.");
                    break;

                case 8:
                    String delBookId = InputHandler.getStringInput("Enter book ID to delete: ");
                    manager.deleteBook(delBookId);
                    break;

                case 9:
                    String delMemberId = InputHandler.getStringInput("Enter member ID to delete: ");
                    manager.deleteMember(delMemberId);
                    break;

                case 10:
                    int saveOption = InputHandler.getIntInput("Before Exit. 1. Save data.. 2. Don't save data... ");
                    if (saveOption == 1) {
                        manager.saveData();
                    }
                    System.out.println("Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}