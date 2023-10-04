package customExceptions;

@SuppressWarnings("serial")
public class NonExistentKeyException extends Exception{

	public NonExistentKeyException(String message) {
		super(message);
	}
}