/*
 * Decompiled with CFR 0_115.
 */
package game.entity.objects;

import game.entity.HashEntity;
import game.entity.mob.Mob;
import game.level.Level;
import game.level.TileHash;
import java.util.ArrayList;

public abstract class Object
extends HashEntity {
    public boolean available = true;
    public int hp = 1;
    public ItemType type;

    @Override
    public abstract void update();

    @Override
    public abstract void render();

    public abstract void rendertop();

    public void getItem(Mob mob) {
    }

    @Override
    protected void updatehash() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].objects.contains(this)) {
            this.level.tilehash[this.tileIndex].objects.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    @Override
    public void add() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].objects.contains(this)) {
            this.level.tilehash[this.tileIndex].objects.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    public static enum ItemType {
        ROCK;

    }

}

