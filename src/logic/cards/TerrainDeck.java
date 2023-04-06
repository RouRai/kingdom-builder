package logic.cards;

import java.util.ArrayList;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This class is mainly used in order to store Terrain Cards using a Terrain deck. It automatically shuffles
 * and retrieves the correct amount of cards to be used in the game.
 */
public class TerrainDeck extends CardDeck<TerrainCard> {

    public TerrainDeck () {
        ArrayList<TerrainCard> cards = new ArrayList<>();
        // Add all the cards necessary to the ArrayList
        addCards(cards);
    }

    /**
     * Returns the terrain cards to be used in this game.
     * @return ArrayList of shuffled Terrain Cards.
     */
    public ArrayList<TerrainCard> getTerrainDeck () {
        return getShuffledCards();
    }
}
