package logic.cards;

import logic.constantFolder.TerrainEnum;

import java.awt.image.BufferedImage;

/**
 * Author: Rounak Rai <br>
 * Contributors: None <br> <br>
 *
 * This record is used in order to store basic relevant data to Terrain Cards, mainly their type.
 * @param type
 */
public record TerrainCard(TerrainEnum type, BufferedImage image, String terrainString) {}

