package by.training.finance.controller.command.impl.init;

import by.training.finance.controller.Controller;
import by.training.finance.controller.command.Command;
import by.training.finance.controller.constants.ControllerMessages;

public class SignOut implements Command {

	/*
	 * request looks like login=login
	 */

	@Override
	public String execute(String request) {
		Controller.setLogin(new String());
		return ControllerMessages.GOODBYE_MESSAGE;
	}

}
