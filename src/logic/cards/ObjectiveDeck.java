package logic.cards;

import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to store a deck of Objective Cards based on the CardDeck class. It automatically
 * shuffles and retrieves cards when necessary, and retrieves the correct amount.
 */
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
