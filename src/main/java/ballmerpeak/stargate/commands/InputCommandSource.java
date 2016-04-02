package ballmerpeak.stargate.commands;

public interface InputCommandSource {
	public void setInputCommandHandler(InputCommandHandler handler);

	public InputCommand getNextCommand();
	
}