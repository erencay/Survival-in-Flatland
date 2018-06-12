package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class FenceTL extends Tile {

    public FenceTL(int id) {
        super(Assets.fenceTL, id);
    }

        public boolean isSolid() {
        return true;
    }
}