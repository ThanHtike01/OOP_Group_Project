import java.io.*;
import java.util.*;

// FileHandler handles reading and writing of LibraryItems and Members to file.
public class FileHandler {
    private static final String ITEMS_FILE = "items.txt";
    private static final String MEMBERS_FILE = "members.txt";

    // Saves all LibraryItems (Book, SchoolBook) to items.txt
    public static void saveItemsToFile(List<LibraryItem> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEMS_FILE))) {
            for (LibraryItem item : items) {
                String line;

                // Using instanceof to handle different types
                if (item instanceof Book) {
                    Book book = (Book) item;
                    line = "Book," + book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.isAvailable();
                } else if (item instanceof SchoolBook) {
                    SchoolBook sb = (SchoolBook) item;
                    line = "SchoolBook," + sb.getId() + "," + sb.getTitle() + "," + sb.getSubject() + "," + sb.isAvailable();
                } else {
                    continue; // skip unsupported types
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    // Load items from file
    public static List<LibraryItem> loadItemsFromFile() {
        List<LibraryItem> items = new ArrayList<>();
        File file = new File(ITEMS_FILE);

        // Return empty list if file doesn't exist (first-time run safety)
        if (!file.exists()) return items;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[1];
                String title = parts[2];
                boolean available = Boolean.parseBoolean(parts[4]);

                if (type.equals("Book")) {
                    String author = parts[3];
                    Book book = new Book(id,title, author);
                    if (!available) book.borrow();
                    items.add(book);
                } else if (type.equals("SchoolBook")) {
                    String subject = parts[3];
                    SchoolBook schoolBook = new SchoolBook(id,title, subject);
                    if (!available) schoolBook.borrow();
                    items.add(schoolBook);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }

        return items;
    }

    // Save members to file
    public static void saveMembersToFile(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                // Format: memberId,name,borrowedId1
                String line = member.getMemberId() + "," + member.getName();
                for (String borrowedId : member.getBorrowedItems()) {
                    line += "," + borrowedId;
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    // Load members from file
    public static List<Member> loadMembersFromFile() {
        List<Member> members = new ArrayList<>();
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return members;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String memberId = parts[0]; // Now change to auto-generated internally
                String name = parts[1];
                Member member = new Member(memberId, name);

                //Add borrowed items back to the member
                for (int i = 2; i < parts.length; i++) {
                    member.borrowItem(parts[i]);
                }

                members.add(member);
            }
        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }

        return members;
    }
}
