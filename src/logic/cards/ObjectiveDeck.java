package logic.cards;

import java.util.ArrayList;

public class ObjectiveDeck extends CardDeck<ObjectiveCard> {
    public ObjectiveDeck() {
        ArrayList<ObjectiveCard> cards = new ArrayList<>();
        // Add all the cards necessary to the ArrayList
        addCards(cards);
    }

    /**
     * Returns the objectives of a new game.
     * @return ArrayList of shuffled Objective Cards
     */
    public ArrayList<ObjectiveCard> getObjectives () {
        return getShuffledCards(3);
    }
}
