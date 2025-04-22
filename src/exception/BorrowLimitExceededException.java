package exception;

public class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException(String memberName) {
        super(memberName + " has reached the borrow limit (5 items).");
    }
}
