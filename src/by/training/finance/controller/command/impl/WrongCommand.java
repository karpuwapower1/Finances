package by.training.finance.controller.command.impl;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.constants.ControllerMessages;

public class WrongCommand implements Command {
	
	public String execute(String request) {
		return ControllerMessages.ILLEGAL_COMMAND;
	}
}
