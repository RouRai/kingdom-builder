package logic.cards;

import logic.constantFolder.Constants;
import logic.constantFolder.TerrainEnum;

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
        super();
        ArrayList<TerrainCard> cards = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cards.add(new TerrainCard(TerrainEnum.CANYON, Constants.landCards[1], "CANYON"));
            cards.add(new TerrainCard(TerrainEnum.DESERT, Constants.landCards[2], "DESERT"));
            cards.add(new TerrainCard(TerrainEnum.FLOWER, Constants.landCards[3], "FLOWER_FIELD"));
            cards.add(new TerrainCard(TerrainEnum.FOREST, Constants.landCards[4], "FOREST"));
            cards.add(new TerrainCard(TerrainEnum.GRASS, Constants.landCards[0], "GRASS"));
        }
        // Add all the cards necessary to the ArrayList
        addCards(cards);
        getShuffledCards();
    }

    /**
     * Returns the terrain cards to be used in this game.
     * @return ArrayList of shuffled Terrain Cards.
     */
    public ArrayList<TerrainCard> getTerrainDeck () {
        return getShuffledCards();
    }
}
