import java.util.ArrayList;
import java.util.List;

import exception.*;

//Core logic handler of the system.
public class LibraryManager {
    private List<LibraryItem> items; // List to store books and school books
    private List<Member> members; // List to store members
    private static int itemCounter = 0; // Used to auto-genarate IDs

    // Constructor initializes empty lists for items and members
    public LibraryManager() {
        items = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add new items
    public void addBook(LibraryItem item) {
        items.add(item);
    }

    // Register new members
    public void registerMember(Member member) {
        members.add(member);
    }

    // List all items with availability status
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

    // List all members with borrowed items
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

    // Allow member to borrow item if conditions are met
    public void borrowItem(String itemId, String memberId) throws Exception{
        LibraryItem item = findItemById(itemId);
        Member member = findMemberById(memberId);

        if (item == null) throw new ItemNotFoundException(itemId);
        
        if (member == null) throw new MemberNotFoundException(memberId);

        if (!item.isAvailable()) throw new ItemAlreadyBorrowedException(item.getTitle());

        if (member.getBorrowedItems().size() >= 5) 
            throw new BorrowLimitExceededException(member.getName());

        item.borrow();
        member.borrowItem(itemId);
        System.out.println(member.getName() + " borrowed \"" + item.getTitle() + "\" successfully.");
    }

    // Allow a member to return borrowed item
    public void returnItem(String itemId, String memberId) throws ItemNotFoundException, MemberNotFoundException, ItemNotBorrowedByMemberException{
        LibraryItem item = findItemById(itemId);
        Member member = findMemberById(memberId);

        if (item == null) throw new ItemNotFoundException(itemId);
        
        if (member == null) throw new MemberNotFoundException(memberId);

        if (!member.getBorrowedItems().contains(itemId)) {
            throw new ItemNotBorrowedByMemberException(member.getName(), itemId);
        }

        item.returnItem();
        member.returnItem(itemId);
        System.out.println((member.getName() + " returned \"" + item.getTitle() + "\" successfully."));
    }

    // Helper methods
    // Finds a library item by its ID 
    private LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    // Finds a member by their ID
    private Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null;
    }

    // Load data from files and update ID counters based on highest existing ID
    public void loadData() {
        items = FileHandler.loadItemsFromFile();
        members = FileHandler.loadMembersFromFile();

        int maxId = 0;
    for (LibraryItem item : items) {
        try {
            int id = Integer.parseInt(item.getId());
            if (id > maxId) {
                maxId = id;
            }
        } catch (NumberFormatException e) {
            // Ignore non-numeric IDs
        }
    }
    itemCounter = maxId;

    // Also update memberCounter based on highest member ID
    int maxMemberId = 0;
    for (Member member : members) {
        try {
            int id = Integer.parseInt(member.getMemberId());
            if (id > maxMemberId) {
                maxMemberId = id;
            }
        } catch (NumberFormatException e) {
            // Ignore non-numeric IDs
        }
    }
        Member.setMemberCounter(maxMemberId);
    }

    // Save all data to files
    public void saveData() {
        FileHandler.saveItemsToFile(items);
        FileHandler.saveMembersToFile(members);
    }

    //Delete a book only if it is not currently borrowed
    public void deleteBook(String itemId) throws ItemNotFoundException {
        LibraryItem item = findItemById(itemId);
        if (item == null) throw new ItemNotFoundException(itemId);

        if (!item.isAvailable()) {
            System.out.println("Cannot delete a borrowed book.");
            return;
        }

        items.remove(item);
        System.out.println("Book deleted successfully.");
    }

    // Delete a member only if they have no borrowed items
    public void deleteMember(String memberId) throws MemberNotFoundException {
        Member member = findMemberById(memberId);
        if (member == null) throw new MemberNotFoundException(memberId);

        if (!member.getBorrowedItems().isEmpty()) {
            System.out.println("Member has borrowed items. Return them first.");
            return;
        }

        members.remove(member);
        System.out.println("Member deleted successfully.");
    }

    //Method used in input validation
    public static boolean isNumeric(String str) {
        return str.matches("\\d+"); // Accepts only digits (no negative or decimal)
    }

    // Returns the next available unique item ID
    public static int getNextItemId() {
        return ++itemCounter;
    }

    public void showBookList() {
        System.out.println("Book List");
        listItems();
        System.out.println();
    }

    public void showMemberList() {
        System.out.println("Member List");
        listMembers();
        System.out.println();
    }
}
