package daomephsta.dialogtree;

public class DialogTree
{
	private final DialogNode[] roots;

	public DialogTree(DialogNode[] roots)
	{
		this.roots = roots;
	}

	public DialogNode[] getRoots()
	{
		return roots;
	}
}
