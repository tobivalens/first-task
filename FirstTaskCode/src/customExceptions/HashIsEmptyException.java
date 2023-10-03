package customExceptions;

@SuppressWarnings("serial")
public class HashIsEmptyException extends Exception{

	
	public HashIsEmptyException(String message) {
		super(message);
		
	}
}