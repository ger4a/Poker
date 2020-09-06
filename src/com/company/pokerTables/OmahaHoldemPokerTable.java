package com.company.pokerTables;

import com.company.Card;
import com.company.CardSet;
import com.company.EvaluatedHand;
import com.company.combinations.CombinationFactory;
import com.company.enums.PokerTableTypes;

import java.util.ArrayList;
import java.util.Collections;

public class OmahaHoldemPokerTable extends HoldemPokerTable {
    public OmahaHoldemPokerTable(String inputString) {
        super(inputString, PokerTableTypes.OMAHA);
    }

    @Override
    public void evaluateTable() {
        ArrayList<EvaluatedHand> evaluatedTwoCardHand = new ArrayList<>();

        //Since omaha player should pick 2 cards from his hand, splitting 4 cards into combination of 2 and evaluating them, then picking the best combination
        for (CardSet hand : hands) {
            for (CardSet twoCardsSet : splitHand(hand)) {
                evaluatedTwoCardHand.add(new EvaluatedHand(twoCardsSet, CombinationFactory.getCombination(board, twoCardsSet)));
            }
            Collections.sort(evaluatedTwoCardHand, Collections.reverseOrder());
            evaluatedHands.add(new EvaluatedHand(hand, evaluatedTwoCardHand.get(0).getCombination()));
        }

        Collections.sort(evaluatedHands);
    }

    private ArrayList<CardSet> splitHand(CardSet hand) {
        ArrayList<CardSet> splitHand = new ArrayList();
        ArrayList<Card> handCards = hand.getCards();
        for (int i = 0; i < handCards.size(); i++) {
            for (int j = i + 1; j < handCards.size(); j++) {
                ArrayList twoCardSet = new ArrayList<>();
                twoCardSet.add(handCards.get(i));
                twoCardSet.add(handCards.get(j));
                splitHand.add(new CardSet(twoCardSet));
            }
        }
        return splitHand;
    }
}
