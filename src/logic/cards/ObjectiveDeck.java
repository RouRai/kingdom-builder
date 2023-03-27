package logic.cards;

import java.util.ArrayList;

public class ObjectiveDeck extends CardDeck<ObjectiveCard> {
    public ObjectiveDeck() {
        ArrayList<ObjectiveCard> cards = new ArrayList<>();
        // Add all the cards necessary to the ArrayList
        addCards(cards);
    }

    public ArrayList<ObjectiveCard> getObjectives () {
        return getShuffledCards(3);
    }
}
