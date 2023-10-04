package customExceptions;

@SuppressWarnings("serial")
public class QueueIsEmptyException extends Exception{

	public QueueIsEmptyException(String message) {
		super(message);
	}
}