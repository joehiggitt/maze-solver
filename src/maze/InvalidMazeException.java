package maze;

/**
* Exception that indicates a problem with the maze being created.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
*/
public class InvalidMazeException extends Exception
{
	/**
	* Constructs the excpetion
	* @param message the error message
	*/
	public InvalidMazeException(String message)
	{
		super(message);
	}
}