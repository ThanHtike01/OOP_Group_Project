public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        boolean exit = false;
        manager.loadData();
        while (!exit) {
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
                    String bookId = String.format("%03d", LibraryManager.getNextItemId());
                    Book newBook = new Book(bookId,title1, author);
                    manager.addBook(newBook);
                    System.out.println(newBook.getDetails());
                    break;

                case 2:
                    String title2 = InputHandler.getStringInput("Enter school book title: ");
                    String subject = InputHandler.getStringInput("Enter subject: ");
                    String schoolBookId = String.format("%03d", LibraryManager.getNextItemId()); // helper
                    SchoolBook schoolBook = new SchoolBook(schoolBookId, title2, subject);
                    manager.addBook(schoolBook); // same method, since it's a LibraryItem
                    System.out.println(schoolBook.getDetails() + " [Available]");
                    break;

                case 3:
                    String name = InputHandler.getStringInput("Enter name: ");
                    Member newMember = new Member(name);
                    manager.registerMember(newMember);
                    System.out.println("Member ID: " + newMember.getMemberId() + ", Name: " + newMember.getName());
                    break;

                case 4:
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

                case 5:
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

                case 6:
                    manager.listItems();
                    break;

                case 7:
                    manager.listMembers();
                    break;

                case 8:
                    String delMemberId;
                    do {
                        delMemberId = InputHandler.getStringInput("Enter member ID to delete: ");
                        if (!LibraryManager.isNumeric(delMemberId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(delMemberId));
                    manager.deleteMember(delMemberId);
                    break;

                case 9:
                    String delBookId;
                    do {
                        delBookId = InputHandler.getStringInput("Enter book ID to delete: ");
                        if (!LibraryManager.isNumeric(delBookId)) {
                            System.out.println("ID must be numeric. Try again.");
                        }
                    } while (!LibraryManager.isNumeric(delBookId));
                    manager.deleteBook(delBookId);
                    break;

                case 10:
                    manager.saveData();
                    System.out.println("Data saved to file.");
                    break;

                case 11:
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