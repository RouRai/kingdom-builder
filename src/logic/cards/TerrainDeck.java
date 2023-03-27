package logic.cards;

import java.util.ArrayList;

public class TerrainDeck extends CardDeck<TerrainCard> {

    public TerrainDeck () {
        ArrayList<TerrainCard> cards = new ArrayList<>();
        // Add all the cards necessary to the ArrayList
        addCards(cards);
    }

    public ArrayList<TerrainCard> getTerrainDeck () {
        return getShuffledCards();
    }
}
