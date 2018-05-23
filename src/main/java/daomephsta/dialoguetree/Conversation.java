package daomephsta.dialoguetree;

import com.google.common.collect.ImmutableList;

import daomephsta.dialoguetree.responses.ResponseContextBase;

public class Conversation
{
	private final DialogueTree tree;
	private final ResponseContextBase responseContext;
	private DialogueNode currentNode;
	
	public Conversation(DialogueTree tree, ResponseContextBase responseContext)
	{
		this.tree = tree;
		this.responseContext = responseContext;
	}

	public boolean hasNext()
	{
		if(currentNode == null) return !tree.getRoots().isEmpty();
		return !currentNode.getChildren().isEmpty();
	}

	public ImmutableList<DialogueNode> choices()
	{
		return getChoices();
	}
	
	public void choose(DialogueNode option)
	{
		if(!getChoices().contains(option)) throw new IllegalArgumentException(option + " is not a child of " + (currentNode != null ? currentNode : tree));
		currentNode = option;
		option.getResponse().respond(responseContext);
	}
	
	private ImmutableList<DialogueNode> getChoices()
	{
		return currentNode != null ? currentNode.getChildren() : tree.getRoots();
	}
}
