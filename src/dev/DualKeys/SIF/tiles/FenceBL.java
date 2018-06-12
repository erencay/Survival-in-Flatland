package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceBL extends Tile {

    public FenceBL(int id) {
        super(Assets.fenceBL, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
