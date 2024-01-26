package command;

import common.ConstantsMessage;
import service.CashMachine;

public class CommandProcessor {
    private final CashMachine cashMachine;

    public CommandProcessor(CashMachine cashMachine) {
        this.cashMachine = cashMachine;
    }

    public String processCommand(String command) {
        String[] parts = command.split(" ");
        try {
            return switch (parts[0]) {
                case "+" -> cashMachine.addNotes(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                case "-" -> cashMachine.getCash(parts[1], Integer.parseInt(parts[2]));
                case "?" -> cashMachine.printCash();
                default -> ConstantsMessage.ERROR_MESSAGE;
            };
        } catch (Exception e) {
            return ConstantsMessage.ERROR_MESSAGE;
        }
    }
}