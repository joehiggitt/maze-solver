package maze;

public class InvalidCharacterException extends InvalidMazeException
{
	public InvalidCharacterException()
	{
		super("An invalid character has been entered.");
	}
}