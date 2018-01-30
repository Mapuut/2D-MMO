/*
 * Decompiled with CFR 0_115.
 */
package game.entity.effects;

import game.entity.HashEntity;
import game.entity.mob.Mob;
import game.level.Level;
import game.level.TileHash;
import java.util.ArrayList;
import java.util.List;

public abstract class Effect
extends HashEntity {
    public Effect(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render();

    @Override
    protected void updatehash() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].effects.contains(this)) {
            this.level.tilehash[this.tileIndex].effects.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    @Override
    public void add() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].effects.contains(this)) {
            this.level.tilehash[this.tileIndex].effects.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    @Override
    public void remove() {
        this.level.tilehash[this.tileIndex].effects.remove(this);
        this.level.effects.remove(this);
        this.removed = true;
    }
}

