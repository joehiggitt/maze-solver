package maze;

/**
* Exception that indicates an invalid character has tried to be converted into a Tile object.
* @author Joe Higgitt
* @version 1.0, 4th May 2021
* @see maze.InvalidMazeException
*/
public class InvalidCharacterException extends InvalidMazeException
{
	/**
	* Constructs the excpetion
	*/
	public InvalidCharacterException()
	{
		super("An invalid character has been entered.");
	}
}