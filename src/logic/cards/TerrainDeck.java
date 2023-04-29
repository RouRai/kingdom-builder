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
        Constants c = new Constants();
        ArrayList<TerrainCard> cards = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cards.add(new TerrainCard(TerrainEnum.GRASS, Constants.getLandCardsCards()[0]));
            cards.add(new TerrainCard(TerrainEnum.CANYON, Constants.getLandCardsCards()[1]));
            cards.add(new TerrainCard(TerrainEnum.DESERT, Constants.getLandCardsCards()[2]));
            cards.add(new TerrainCard(TerrainEnum.FLOWER, Constants.getLandCardsCards()[3]));
            cards.add(new TerrainCard(TerrainEnum.FOREST, Constants.getLandCardsCards()[4]));
        }
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
