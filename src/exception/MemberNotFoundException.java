package exception;

public class MemberNotFoundException extends Exception{
    public MemberNotFoundException(String memberId){
        super("Member with ID '" + memberId + "' was not found.");
    }    
}
