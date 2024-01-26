import command.CommandProcessor;
import service.CashMachine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CashMachine cashMachine = new CashMachine();
        CommandProcessor processor = new CommandProcessor(cashMachine);
        System.out.println("ATM welcomes you! Enter value:");

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (!command.isEmpty()) {
                String response = processor.processCommand(command);
                System.out.println(response);
            }
        }
    }
}
