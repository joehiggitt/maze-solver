package maze;

public class MultipleEntranceExcpetion extends InvalidMazeException
{
	public MultipleEntranceExcpetion()
	{
		super("Only one entrance can be added to a maze");
	}
}