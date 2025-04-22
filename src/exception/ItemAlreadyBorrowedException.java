package exception;

public class ItemAlreadyBorrowedException extends Exception {
    public ItemAlreadyBorrowedException(String itemTitle){
        super(itemTitle + " is already borrowed.");
    }
}
