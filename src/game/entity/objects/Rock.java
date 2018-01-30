/*
 * Decompiled with CFR 0_115.
 */
package game.entity.objects;

import game.entity.objects.Object;
import game.graphics.Screen;
import game.level.Level;
import game.level.TileHash;
import game.level.tile.Tile;
import java.util.ArrayList;

public class Rock
extends Object {
    private int cooldown = 0;

    public Rock(int x, int y, RockType type2, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
        level.levelPixelsObjects[(x >> 5) + (y >> 5) * level.tilesx] = -8355712;
        level.tilehash[(x >> 5) + (y >> 5) * level.tilesx].objects.add(this);
    }

    @Override
    public void update() {
        ++this.cooldown;
        if (this.cooldown == 500) {
            this.available = false;
        }
    }

    @Override
    public void render() {
    }

    @Override
    public void rendertop() {
        if (this.available) {
            Screen.renderTile(this.x - 16, this.y - 48, Tile.cobblestone_up);
        }
    }

    public static enum RockType {
        ROCK;

    }

}

