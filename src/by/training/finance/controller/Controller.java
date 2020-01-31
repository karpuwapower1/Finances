package by.training.finance.controller;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandUtils;

public final class Controller {

	private CommandProvider provider = new CommandProvider();
	private static String login = new String();

	public String execute(String request) {
		String commandName = CommandUtils.getCommandName(request).toUpperCase();
		Command command = provider.getCommand(commandName);
		String response = command.execute(CommandUtils.getRequestString(request));
		return response;
	}

	public static void setLogin(String login) {
		Controller.login = login;
	}

	public static String getLogin() {
		return login;
	}
}
