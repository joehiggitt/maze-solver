package maze;

public class MultipleExitException extends InvalidMazeException
{
	public MultipleExitException()
	{
		super("Only one exit can be added to a maze");
	}
}