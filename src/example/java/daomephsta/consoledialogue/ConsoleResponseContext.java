package daomephsta.consoledialogue;

import daomephsta.dialoguetree.responses.ResponseContextBase;

public class ConsoleResponseContext extends ResponseContextBase
{
	@Override
	public void displayString(String string)
	{
		System.out.println("NPC: " + string);
	}
}
