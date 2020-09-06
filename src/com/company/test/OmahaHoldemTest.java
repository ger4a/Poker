package com.company.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.IncorrectInputStringException;
import com.company.pokerTables.HoldemPokerTable;
import com.company.pokerTables.OmahaHoldemPokerTable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OmahaHoldemTest {
    @Test
    public void pokerHandShouldBeEvaluatedAndSortedCorrectly() {
        Map<String, String> testsInputOutput = new HashMap<>();
        testsInputOutput.put("5c2c4c6dTh Ah3h3s7s 3d7dTdTc",  "Ah3h 3d7d=3s7s");
        testsInputOutput.put("4cKs4h8s7s Ad4sAc4d As9sKhKd",  "Ac4d=Ad4s 5d6d As9s KhKd");
        testsInputOutput.put("Ah5d3sKhQc AcAsTc9h KsQh2s4h",  "Tc9h KsQh AcAs 2h4s=2s4h");
        testsInputOutput.put("Ah5d3sKhQc AcAsTcJh KsQh2s4h",  "KsQh AcAs 2h4s=2s4h TcJh");
        testsInputOutput.put("AcAsKsQsQh AdAhTsJs KhQdJhTh",  "JhTh KhQd KcKd AdAh TsJs");
        testsInputOutput.put("AcKc3c5hTh TdTs2c4c 2d4d2s4s AdKdQcJh Ah2h9cTc",  "5d7d Ah2h AdKd TdTs 2d4d=2s4s QcJh 2c4c=9cTc");
        testsInputOutput.put("3h7dJs7h5c 8c8hKcKh KsQhAhTd",  "KsQh AhTd 8c8h KcKh");
        testsInputOutput.put("TcQcJcAcKc AsAd2d3d 4d5d2h3h QhJh9c8c",  "2d3d=2h3h=4d5d=9c8c=AsAd=QhJh=Ts8s");
        testsInputOutput.put("5c2c4c6dTh Ac3cAh3h 3s7s3d7d TdTsKc7c QcJcKhKd 9dJsJh8h",  "Jh8h 9dJs KhKd AsAd TdTs Ah3h 3d7d=3s7s QcJc Kc7c Ac3c");
        testsInputOutput.put("AsKs9h3h5d AdKdAhKh QhJhQdJd", "QdJd=QhJh AdKd=AhKh");
        testsInputOutput.put("AsKsKc3h5d AdKdAhKh QhJhQdJd", "QdJd=QhJh AdKd=AhKh");
        testsInputOutput.put("AsKsKc3hAd 4dKdAh4h QhJhQdJd", "QdJd=QhJh 4dKd Ah4h");


        for(Map.Entry<String, String> inputOutput : testsInputOutput.entrySet()){
            assertEquals(inputOutput.getValue(), evaluateString(inputOutput.getKey()));
        }
    }

    @Test
    public void pokerHandExceptionsShouldBeCaught() {
        Map<String, String> testsInputOutput = new HashMap<>();
        testsInputOutput.put("AcKc3c5hTh5s TcTs 2c4c 2d4d 2s4s AdKd QcJh Ah2h 9cTc 4d7d",  "Wrong input string: Board (AcKc3c5hTh5s) card amount is not equals to 5");
        testsInputOutput.put("AcKc3c5hTh TcTs6h 2c4c 2d4d 2s4s AdKd QcJh Ah2h 9cTc 4d7d",  "Wrong input string: Hand (TcTs6h) card amount is not equals to 2");
        testsInputOutput.put("AcKc3c5hTh TcTs6 2c4c 2d4d 2s4s AdKd QcJh Ah2h 9cTc 4d7d",  "Wrong input string: Card 6 is not a correct input");
        testsInputOutput.put("AcKc3c5hTh TcTs 2c2c",  "Wrong input string: Table contains duplicate cards (2c)");
        testsInputOutput.put("AcKc3c5hTh TcTs 2c3x",  "Wrong input string: Suit x does not exist");
        testsInputOutput.put("AcKc3c5hFh TcTs 2c3h",  "Wrong input string: Rank F does not exist");

        for(Map.Entry<String, String> inputOutput : testsInputOutput.entrySet()){
            assertEquals(inputOutput.getValue(), evaluateString(inputOutput.getKey()));
        }
    }

    public String evaluateString(String input) {
        try {
            HoldemPokerTable pokerTable = new OmahaHoldemPokerTable(input);
            pokerTable.evaluateTable();
            return pokerTable.toString();
        } catch (IncorrectInputStringException incorrectInputStringException) {
            return incorrectInputStringException.getMessage();
        }
    }
}
