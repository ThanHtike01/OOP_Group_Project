// Use Abstract class respresenting common features of all library items
public abstract class LibraryItem {
    // Encapsulation
    private String id;
    private String title;
    private boolean isAvailable;

    // Constructor to initialize common attributes
    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Handling borrowing logic
    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Item already borrowed.");
        }
    }

    // Change the item as avaliable again
    public void returnItem() {
        isAvailable = true;
    }

    // Abstract method to provide subclasses detail formatting
    public abstract String getDetails(); // Allows override behavior
}