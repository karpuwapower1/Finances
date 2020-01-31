package by.training.finance.controller;

import java.util.HashMap;

import by.training.finance.controller.command.Command;
import by.training.finance.controller.command.CommandType;
import by.training.finance.controller.command.impl.WrongCommand;
import by.training.finance.controller.command.impl.counter.AllPayments;
import by.training.finance.controller.command.impl.counter.FullSum;
import by.training.finance.controller.command.impl.counter.PaymentsBetweenDates;
import by.training.finance.controller.command.impl.init.Delete;
import by.training.finance.controller.command.impl.init.Register;
import by.training.finance.controller.command.impl.init.SignIn;
import by.training.finance.controller.command.impl.init.SignOut;
import by.training.finance.controller.command.impl.user.AddAccount;
import by.training.finance.controller.command.impl.user.AddCategory;
import by.training.finance.controller.command.impl.user.GetBalance;
import by.training.finance.controller.command.impl.user.ReceiptMoney;
import by.training.finance.controller.command.impl.user.RemoveAccount;
import by.training.finance.controller.command.impl.user.RemoveCategory;
import by.training.finance.controller.command.impl.user.ShowUser;
import by.training.finance.controller.command.impl.user.SpendMoney;

public class CommandProvider {

	private final HashMap<CommandType, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandType.DELETE, new Delete());
		commands.put(CommandType.REGISTER, new Register());
		commands.put(CommandType.SIGN_IN, new SignIn());
		commands.put(CommandType.SIGN_OUT, new SignOut());
		commands.put(CommandType.ADD_ACCOUNT, new AddAccount());
		commands.put(CommandType.REMOVE_ACCOUNT, new RemoveAccount());
		commands.put(CommandType.ADD_CATEGORY, new AddCategory());
		commands.put(CommandType.REMOVE_CATEGORY, new RemoveCategory());
		commands.put(CommandType.BALANCE, new GetBalance());
		commands.put(CommandType.PAY, new SpendMoney());
		commands.put(CommandType.RECEIPT, new ReceiptMoney());
		commands.put(CommandType.ALL_PAYMENTS, new AllPayments());
		commands.put(CommandType.FULL_SUM, new FullSum());
		commands.put(CommandType.ALL_PAYMENTS_BETWEEN_DATES, new PaymentsBetweenDates());
		commands.put(CommandType.SHOW_INFO, new ShowUser());
		commands.put(CommandType.WROND_COMMAND, new WrongCommand());
	}

	public Command getCommand(String commandName) {
		try {
			Command command = commands.get(CommandType.valueOf(commandName.toUpperCase()));
			if (command == null) {
				command = commands.get(CommandType.WROND_COMMAND);
			}
			return command;
		} catch (IllegalArgumentException e) {
			return commands.get(CommandType.WROND_COMMAND);
		}
	}
}
