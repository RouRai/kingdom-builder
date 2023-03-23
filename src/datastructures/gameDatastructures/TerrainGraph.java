package datastructures.gameDatastructures;

import datastructures.abstractDatastructures.Graph;
import logic.constantFolder.TerrainEnum;

import java.io.File;
import java.util.ArrayList;

public class TerrainGraph extends Graph<TerrainNode> {

    public TerrainGraph(ArrayList<File> boardTextFiles) {
        super(new TerrainNode(TerrainEnum.CANYON));
    }


}
