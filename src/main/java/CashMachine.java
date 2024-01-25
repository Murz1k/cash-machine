import java.util.HashMap;
import java.util.Map;

import java.util.*;

public class CashMachine {
    private Map<String, TreeMap<Integer, Integer>> cash;

    public CashMachine() {
        this.cash = new HashMap<>();
    }

    public String addNotes(String currency, int value, int number) {
        if (!isValidCurrency(currency) || !isValidValue(value) || number <= 0) {
            return "ERROR";
        }

        cash.putIfAbsent(currency, new TreeMap<>(Comparator.reverseOrder()));
        cash.get(currency).merge(value, number, Integer::sum);
        return "OK";
    }

    public String getCash(String currency, int amount) {
        if (!cash.containsKey(currency) || amount <= 0) {
            return "ERROR";
        }

        List<String> output = new ArrayList<>();
        TreeMap<Integer, Integer> denominations = cash.get(currency);

        for (int value : denominations.keySet()) {
            if (amount >= value) {
                int requiredNotes = Math.min(amount / value, denominations.get(value));
                if (requiredNotes > 0) {
                    denominations.put(value, denominations.get(value) - requiredNotes);
                    amount -= value * requiredNotes;
                    output.add(value + " " + requiredNotes);
                }
            }
        }

        if (amount > 0) {

            for (String line : output) {
                String[] parts = line.split(" ");
                int value = Integer.parseInt(parts[0]);
                int count = Integer.parseInt(parts[1]);
                denominations.put(value, denominations.get(value) + count);
            }
            return "ERROR";
        }

        return String.join("\n", output) + "\nOK";
    }

    public String printCash() {
        StringBuilder output = new StringBuilder();
        for (String currency : cash.keySet()) {
            for (Map.Entry<Integer, Integer> entry : cash.get(currency).entrySet()) {
                output.append(currency).append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
            }
        }
        return output.append("OK").toString();
    }

    private boolean isValidCurrency(String currency) {
        return currency != null && currency.matches("[A-Z]{3}");
    }

    private boolean isValidValue(int value) {
        return value == 10 || value == 50 || value == 100 || value == 500 || value == 1000;
    }
}

