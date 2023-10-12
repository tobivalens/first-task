package customExceptions;

public class QueueIsEmptyException extends Exception{

	public QueueIsEmptyException(String msg) {
		super(msg);
	}
}