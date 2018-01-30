/*
 * Decompiled with CFR 0_115.
 */
package game.entity;

import game.level.Level;
import java.util.Random;

public class Entity {
    public int x;
    public int y;
    protected boolean removed = false;
    public Level level;
    protected static Random random = new Random();
    protected int tileIndex = 0;
    protected int tileIndexOld = 0;

    public void update() {
    }

    public void render() {
    }

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return this.removed;
    }

    public void initLevel(Level level) {
        this.level = level;
    }
}

