package maze;

public class NoExitException extends InvalidMazeException
{
	public NoExitException()
	{
		super("At least one exit is needed in the maze");
	}
}