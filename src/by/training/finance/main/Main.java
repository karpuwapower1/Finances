package by.training.finance.main;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import by.training.finance.controller.Controller;
import by.training.finance.controller.command.CommandType;
import by.training.finance.controller.command.CommandUtils;
import by.training.finance.controller.constants.ControllerConstants;
import by.training.finance.view.View;
import by.training.finance.view.impl.ConsoleView;

public class Main {

	public static void main(String[] args) throws ParseException {
		Controller controller = new Controller();
		Scanner scanner = new Scanner(System.in);
		RequestMaker maker = new RequestMaker();
		View view = new ConsoleView();

		while (true) {
			if (scanner.hasNext()) {
				String request = scanner.nextLine();
				request = request + CommandUtils.COMMAND_DELIMETER + maker.makeRequest(request);
				String response = controller.execute(request);
				view.print(response);
			}
		}
	}

	private static class RequestMaker {

		HashMap<CommandType, String[]> parameters = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		View view = new ConsoleView();

		public RequestMaker() {
			parameters.put(CommandType.REGISTER,
					new String[] { ControllerConstants.LOGIN_PARAMETER, ControllerConstants.PASSWORD_PARAMETER });
			parameters.put(CommandType.SIGN_IN,
					new String[] { ControllerConstants.LOGIN_PARAMETER, ControllerConstants.PASSWORD_PARAMETER });
			parameters.put(CommandType.SIGN_OUT, new String[0]);
			parameters.put(CommandType.DELETE, new String[0]);

			parameters.put(CommandType.ADD_ACCOUNT, new String[] { ControllerConstants.ACCOUNT_NAME_PARAMETER,
					ControllerConstants.ACCOUNT_AMOUNT_PARAMETER });
			parameters.put(CommandType.REMOVE_ACCOUNT, new String[] { ControllerConstants.ACCOUNT_NAME_PARAMETER });

			parameters.put(CommandType.ADD_CATEGORY,
					new String[] { ControllerConstants.CATEGORY_NAME_PARAMETER, ControllerConstants.TYPE_PARAMETER });
			parameters.put(CommandType.REMOVE_CATEGORY,
					new String[] { ControllerConstants.CATEGORY_NAME_PARAMETER, ControllerConstants.TYPE_PARAMETER });

			parameters.put(CommandType.BALANCE, new String[0]);
			parameters.put(CommandType.PAY,
					new String[] { ControllerConstants.CATEGORY_NAME_PARAMETER,
							ControllerConstants.ACCOUNT_NAME_PARAMETER, ControllerConstants.ACCOUNT_AMOUNT_PARAMETER,
							ControllerConstants.DATE_PARAMETER });
			parameters.put(CommandType.RECEIPT,
					new String[] { ControllerConstants.CATEGORY_NAME_PARAMETER,
							ControllerConstants.ACCOUNT_NAME_PARAMETER, ControllerConstants.ACCOUNT_AMOUNT_PARAMETER,
							ControllerConstants.DATE_PARAMETER });

			parameters.put(CommandType.ALL_PAYMENTS, new String[] { ControllerConstants.TYPE_PARAMETER });
			parameters.put(CommandType.FULL_SUM, new String[] { ControllerConstants.TYPE_PARAMETER });

			parameters.put(CommandType.ALL_PAYMENTS_BETWEEN_DATES, new String[] { ControllerConstants.TYPE_PARAMETER,
					ControllerConstants.DATE_FROM_PARAMETER, ControllerConstants.DATE_TO_PARAMETER });

			parameters.put(CommandType.SHOW_INFO, new String[0]);
		}

		public String makeRequest(String command) {
			try {
				CommandType type = CommandType.valueOf(command.toUpperCase());
				String[] params = parameters.get(type);
				StringBuilder request = new StringBuilder();
				if (type != CommandType.REGISTER && type != CommandType.SIGN_IN) {
					request.append(ControllerConstants.LOGIN_PARAMETER + CommandUtils.REQUEST_PARAMETER_VALUE_DELIMETER
							+ Controller.getLogin() + CommandUtils.REQUEST_PARAMETER_DELIMETER);
				}
				for (String param : params) {
					view.print(param);
					request.append(param + CommandUtils.REQUEST_PARAMETER_VALUE_DELIMETER);
					String value = scanner.nextLine();
					request.append(value + CommandUtils.REQUEST_PARAMETER_DELIMETER);
				}
				return request.toString();
			} catch (NullPointerException | IllegalArgumentException e) {
				return new String();
			}
		}
	}
}
