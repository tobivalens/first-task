package customExceptions;

public class HeapFullException extends Exception{

    public HeapFullException(String msg){
        super(msg);
    }
}