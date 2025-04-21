public class SchoolBook extends LibraryItem {
    private String subject;

    public SchoolBook(String id, String title, String subject) {
        super(id, title); // Call the superclass constructor
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    // Overrides the abstract method
    @Override
    public String getDetails() {
        return "School Book - ID: " + getId() + ", Title: " + getTitle() + ", Subject: " + subject;
    }
}