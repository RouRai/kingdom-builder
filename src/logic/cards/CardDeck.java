package logic.cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck<T> {

    private final ArrayList<T> cards;

    public CardDeck () {
        cards = new ArrayList<>();
    }

    /**
     * Returns the base deck
     * @return base deck
     */
    public ArrayList<T> getCards () {
        return cards;
    }

    /**
     * Shuffles the entire deck and returns it
     * @return Entire shuffled deck
     */
    public ArrayList<T> getShuffledCards () {
        return getShuffledCards(cards.size());
    }

    /**
     * Shuffles a copy of the deck and returns a random amount of the cards
     * @param count The amount of cards to be returned
     * @return The first "count" number of cards in the shuffled deck
     */
    public ArrayList<T> getShuffledCards (int count) {
        ArrayList<T> copy = new ArrayList<>();
        Collections.copy(copy, cards);

        Collections.shuffle(copy);

        ArrayList<T> toReturn = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            toReturn.add(copy.get(i));
        }

        return toReturn;
    }

    /**
     * Adds all the cards from the given additional cards to the current deck
     * @param additionalCards Cards to add to the deck
     */
    public void addCards (ArrayList<T> additionalCards) {
        cards.addAll(additionalCards);
    }
}
