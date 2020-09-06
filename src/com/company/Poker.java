package com.company;


import com.company.pokerTables.HoldemPokerTable;
import com.company.pokerTables.OmahaHoldemPokerTable;
import com.company.pokerTables.TexasHoldemPokerTable;

import java.util.Scanner;

public class Poker {
    public static void main(String[] args) {
        final String omahaSwitch = "--omaha";
        boolean isOmaha = false;
        Scanner scanner = new Scanner(System.in);

        for (String argument : args) {
            if (argument.equals(omahaSwitch)) {
                isOmaha = true;
                break;
            }
        }

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String output;
            try {
                HoldemPokerTable pokerTable = isOmaha ? new OmahaHoldemPokerTable(input) : new TexasHoldemPokerTable(input);
                pokerTable.evaluateTable();
                output = pokerTable.toString();
            } catch (IncorrectInputStringException incorrectInputStringException) {
                output = incorrectInputStringException.getMessage();
            }

            System.out.println(output);
        }
    }
}
