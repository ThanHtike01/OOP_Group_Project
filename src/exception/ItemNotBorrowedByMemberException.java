package exception;

public class ItemNotBorrowedByMemberException extends Exception{
    public ItemNotBorrowedByMemberException(String memberName, String itemId){
        super(memberName + " did not borrow item with ID " + itemId);
    }
}
