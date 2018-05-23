package daomephsta.consoledialogue;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import daomephsta.dialoguetree.Conversation;
import daomephsta.dialoguetree.DialogueNode;
import daomephsta.dialoguetree.DialogueTree;
import daomephsta.dialoguetree.DialogueTreeParser;

public class ConsoleDialogue
{	
	public static void main(String[] args)
	{
		try(Scanner sc = new Scanner(System.in))
		{
			DialogueTree tree = DialogueTreeParser.parse(new File("test.json"));
			Conversation conversation = new Conversation(tree, new ConsoleResponseContext());
			while(conversation.hasNext())
			{
				List<DialogueNode> choices = conversation.choices();
				StringBuilder sb = new StringBuilder("----------------------------------------" + System.lineSeparator());
				for(int c = 0; c < choices.size(); c++)
				{
					sb.append((c + 1) + ". " + choices.get(c).getText() + System.lineSeparator());
				}
				sb.append("What do you say? ");
				System.out.print(sb);
				conversation.choose(choices.get(getChoiceInt(sc, choices.size()) - 1));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static int getChoiceInt(Scanner sc, int choiceCount)
	{
		int choice = 0;
		boolean success = true;
		try
		{
			choice = sc.nextInt();
		}
		catch(InputMismatchException e)
		{
			success = false;
		}
		success = success && choice <= choiceCount && choice > 0; 
		if(!success) 
		{
			System.out.println("That is not a valid choice");
			return getChoiceInt(sc, choiceCount);
		}
		return choice; 
	}
}
