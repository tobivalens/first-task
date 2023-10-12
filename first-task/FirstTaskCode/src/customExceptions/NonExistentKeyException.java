package customExceptions;

public class NonExistentKeyException extends Exception{

	public NonExistentKeyException(String msg){
		super(msg);
	}
}