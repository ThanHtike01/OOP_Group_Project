import java.util.ArrayList;
import java.util.List;

public class Member {
    private static int memberCounter = 0;
    private String memberId;
    private String name;
    private List<String> borrowedItems;

    public Member(String name) {
        this.memberId = String.format("%03d", ++memberCounter);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void borrowItem(String itemId) {
        borrowedItems.add(itemId);
    }

    public void returnItem(String itemId) {
        borrowedItems.remove(itemId);
    }

    public List<String> getBorrowedItems() {
        return borrowedItems;
    }

    public static void setMemberCounter(int value) {
        memberCounter = value;
    }
}