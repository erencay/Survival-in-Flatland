package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceHoriz extends Tile {

    public FenceHoriz(int id) {
        super(Assets.fenceHoriz, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
