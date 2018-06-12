package dev.DualKeys.SIF.tiles;

import dev.DualKeys.SIF.graphics.Assets;

public class River extends Tile {

    public River(int id) {
        super(Assets.river, id);
    }

    public boolean isSolid() {
        return true;
    }
}
