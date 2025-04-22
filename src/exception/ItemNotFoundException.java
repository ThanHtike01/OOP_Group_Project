package exception;


public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemID){
        super("Item with ID '" + itemID + "' was not found.");
    } 
}
