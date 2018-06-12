package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceBR extends Tile {

    public FenceBR(int id) {
        super(Assets.fenceBR, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
