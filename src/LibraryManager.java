import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<LibraryItem> items;
    private List<Member> members;

    public LibraryManager() {
        items = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add new items
    public void addBook(Book book) {
        items.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }
        for (LibraryItem item : items) {
            System.out.println(item.getDetails() + (item.isAvailable() ? " [Available]" : " [Borrowed]"));
        }
        System.out.println("Total Books: " + items.size());
    }

    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberId() + ", Name: " + member.getName() +
                    ", Borrowed Items: " + member.getBorrowedItems());
        }
        System.out.println("Total Members: " + members.size());
    }

    public void borrowItem(String itemId, String memberId) {
        LibraryItem item = findItemById(itemId);
        Member member = findMemberById(memberId);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        if (!item.isAvailable()) {
            System.out.println("Item is already borrowed.");
            return;
        }
        if (member.getBorrowedItems().size() >= 5) {
            System.out.println("Borrow limit reached. A member can only borrow up to 5 items.");
            return;
        }

        item.borrow();
        member.borrowItem(itemId);
        System.out.println(member.getName() + " borrowed \"" + item.getTitle() + "\" successfully.");
    }

    public void returnItem(String itemId, String memberId) {
        LibraryItem item = findItemById(itemId);
        Member member = findMemberById(memberId);

        if (item == null || member == null) {
            System.out.println("Invalid item or member ID.");
            return;
        }

        if (!member.getBorrowedItems().contains(itemId)) {
            System.out.println("This item was not borrowed by this member.");
            return;
        }

        item.returnItem();
        member.returnItem(itemId);
        System.out.println((member.getName() + " returned \"" + item.getTitle() + "\" successfully."));
    }

    // Helper methods
    private LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null;
    }

    public void loadData() {
        items = FileHandler.loadItemsFromFile();
        members = FileHandler.loadMembersFromFile();
    }

    public void saveData() {
        FileHandler.saveItemsToFile(items);
        FileHandler.saveMembersToFile(members);
    }

    public void deleteBook(String bookId) {
        LibraryItem item = findItemById(bookId);
        if (item == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!item.isAvailable()) {
            System.out.println("Cannot delete a borrowed book.");
            return;
        }

        items.remove(item);
        System.out.println("Book deleted successfully.");
    }

    public void deleteMember(String memberId) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (!member.getBorrowedItems().isEmpty()) {
            System.out.println("Member has borrowed items. Return them first.");
            return;
        }

        members.remove(member);
        System.out.println("Member deleted successfully.");
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+"); // Accepts only digits (no negative or decimal)
    }
}
