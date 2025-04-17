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
            System.out.println("7. Delete Member");
            System.out.println("8. Delete Book");
            System.out.println("9. Save Data to File");
            System.out.println("10. Exit");

            int choice = InputHandler.getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    String title1 = InputHandler.getStringInput("Enter book title: ");
                    String author = InputHandler.getStringInput("Enter author: ");
                    Book newBook = new Book(title1, author);
                    manager.addBook(newBook);
                    System.out.println(newBook.getDetails());
                    break;

                case 2:
                    String name = InputHandler.getStringInput("Enter name: ");
                    Member newMember = new Member(name);
                    manager.registerMember(newMember);
                    System.out.println("Member ID: " + newMember.getMemberId() + ", Name: " + newMember.getName());
                    break;

                case 3:
                    String borrowId;
                    String borrowerId;
                    do {
                        borrowId = InputHandler.getStringInput("Enter item ID to borrow: ");
                        borrowerId = InputHandler.getStringInput("Enter your member ID: ");
                        if (!LibraryManager.isNumeric(borrowId) || !LibraryManager.isNumeric(borrowerId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(borrowId) || !LibraryManager.isNumeric(borrowerId));
                    manager.borrowItem(borrowId, borrowerId);
                    break;

                case 4:
                    String returnId;
                    String returnerId;
                    do {
                        returnId = InputHandler.getStringInput("Enter item ID to return: ");
                        returnerId = InputHandler.getStringInput("Enter your member ID: ");
                        if (!LibraryManager.isNumeric(returnId) || !LibraryManager.isNumeric(returnerId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(returnId) || !LibraryManager.isNumeric(returnerId));
                    manager.returnItem(returnId, returnerId);
                    break;

                case 5:
                    manager.listItems();
                    break;

                case 6:
                    manager.listMembers();
                    break;

                case 7:
                    String delMemberId;
                    do {
                        delMemberId = InputHandler.getStringInput("Enter member ID to delete: ");
                        if (!LibraryManager.isNumeric(delMemberId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(delMemberId));
                    manager.deleteMember(delMemberId);
                    break;

                case 8:
                    String delBookId;
                    do {
                        delBookId = InputHandler.getStringInput("Enter book ID to delete: ");
                        if (!LibraryManager.isNumeric(delBookId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(delBookId));
                    manager.deleteBook(delBookId);
                    break;

                case 9:
                    manager.saveData();
                    System.out.println("Data saved to file.");
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