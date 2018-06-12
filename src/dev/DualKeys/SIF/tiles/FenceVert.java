package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceVert extends Tile {

    public FenceVert(int id) {
        super(Assets.fenceVert, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
