package customExceptions;

@SuppressWarnings("serial")
public class StackIsEmptyException extends Exception{

	public StackIsEmptyException(String message) {
		super(message);
	}
}