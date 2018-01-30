/*
 * Decompiled with CFR 0_115.
 */
package game.level.tile;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.tile.Tile;

public class SolidTile
extends Tile {
    public SolidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y) {
        Screen.renderTile(x << 5, y << 5, this);
    }

    @Override
    public boolean solid() {
        return true;
    }
}

