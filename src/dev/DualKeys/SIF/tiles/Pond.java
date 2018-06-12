package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class Pond extends Tile {

    public Pond(int id) {
        super(Assets.pond, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
