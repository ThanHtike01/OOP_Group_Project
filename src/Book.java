public class Book extends LibraryItem {
    private String author;


    public Book(String id,String title, String author) {
        super(id, title); // Call the superclass constructor
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    // Overrides the abstract method
    @Override
    public String getDetails() {
        return "Book - ID: " + getId() + ", Title: " + getTitle() + ", Author: " + author;
    }
    
}
