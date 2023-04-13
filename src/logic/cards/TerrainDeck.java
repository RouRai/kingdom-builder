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
            cards.add(new TerrainCard(TerrainEnum.CANYON, Constants.landCards[0]));
            cards.add(new TerrainCard(TerrainEnum.DESERT, Constants.landCards[1]));
            cards.add(new TerrainCard(TerrainEnum.FLOWER, Constants.landCards[2]));
            cards.add(new TerrainCard(TerrainEnum.FOREST, Constants.landCards[3]));
            cards.add(new TerrainCard(TerrainEnum.GRASS, Constants.landCards[4]));
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
    public TerrainCard getFirstCard(){
        TerrainCard temp = getCards().get(0);
        getCards().remove(0);
        return temp;
    }
}
