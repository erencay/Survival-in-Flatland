package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceTR extends Tile {

    public FenceTR(int id) {
        super(Assets.fenceTR, id);
    }

    public boolean isSolid() {
        return true;
    }
}
