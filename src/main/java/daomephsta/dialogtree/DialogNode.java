package daomephsta.dialogtree;

public class DialogNode
{
	private final String text;
	private final IResponse response;
	private final boolean isTerminal;
	
	public DialogNode(String text, IResponse response, boolean isTerminal)
	{
		this.text = text;
		this.response = response;
		this.isTerminal = isTerminal;
	}
	
	public String getText()
	{
		return text;
	}
	
	public IResponse getResponse()
	{
		return response;
	}
	
	public boolean isTerminal()
	{
		return isTerminal;
	}
}
