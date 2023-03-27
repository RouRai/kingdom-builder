package logic.cards;

import java.util.ArrayList;

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
