public class Book extends LibraryItem {
    private static int bookCounter = 0;
    private String author;

    public Book(String title, String author) {
        super(String.format("%03d", ++bookCounter), title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getDetails() {
        // TODO Auto-generated method stub
        return "Book - ID: " + getId() + ", Title: " + getTitle() + ", Author: " + author;
    }
    
}
