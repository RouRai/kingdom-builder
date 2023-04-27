package logic.cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is used in order to create a deck of cards given a generic type. In this case, it is to be used
 * in order to store cards of the type given.
 * @param <T>
 */
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
        ArrayList<T> copy = new ArrayList<>(cards);
        ArrayList<T> temp = new ArrayList<>();
        int rand;
        for(int i = copy.size() - 1; i >= 0; i--){
            rand = (int)(Math.random() * copy.size());
            temp.add(copy.get(rand));
            copy.remove(rand);
        }
        copy.addAll(temp);
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

    public boolean isEmpty () {
        return cards.isEmpty();
    }

}
