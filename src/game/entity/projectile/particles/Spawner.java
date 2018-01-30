/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile.particles;

import game.entity.Entity;
import game.entity.HashEntity;
import game.entity.mob.Mob;
import game.entity.projectile.particles.ArrowParticle;
import game.entity.projectile.particles.BloodParticle;
import game.entity.projectile.particles.FireParticle;
import game.entity.projectile.particles.RockParticle;
import game.level.Level;
import game.level.TileHash;
import java.util.ArrayList;
import java.util.List;

public class Spawner
extends HashEntity {
    public static int debugcount = 0;
    private List<Entity> list = new ArrayList<Entity>();
    private int life = 2000;
    private static /* synthetic */ int[] $SWITCH_TABLE$game$entity$projectile$particles$Spawner$type;

    public Spawner(int x, int y, int amount, type type2) {
        this(x, y, amount, type2, null);
    }

    public Spawner(int x, int y, int amount, type type2, Level level) {
        this.x = x;
        this.y = y;
        switch (Spawner.$SWITCH_TABLE$game$entity$projectile$particles$Spawner$type()[type2.ordinal()]) {
            case 1: {
                int i = 0;
                while (i < amount) {
                    this.list.add(new FireParticle(x, y, level));
                    ++i;
                }
                break;
            }
            case 2: {
                int i = 0;
                while (i < amount) {
                    this.list.add(new RockParticle(x, y, level));
                    ++i;
                }
                break;
            }
            case 3: {
                int i = 0;
                while (i < amount) {
                    this.list.add(new BloodParticle(x, y, level));
                    ++i;
                }
                break;
            }
            case 4: {
                int i = 0;
                while (i < amount) {
                    this.list.add(new ArrowParticle(x, y, level));
                    ++i;
                }
                break;
            }
            case 5: {
                int i = 0;
                while (i < amount) {
                    this.list.add(new WaterParticle(x, y, level));
                    ++i;
                }
                break;
            }
        }
        ++debugcount;
    }

    @Override
    public void render() {
        int i = 0;
        while (i < this.list.size()) {
            this.list.get(i).render();
            ++i;
        }
    }

    @Override
    public void update() {
        --this.life;
        if (this.life <= 0 || this.list.size() == 0) {
            this.remove();
        }
        int i = 0;
        while (i < this.list.size()) {
            this.list.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.list.size()) {
            if (this.list.get(i).isRemoved()) {
                this.list.remove(i);
            }
            ++i;
        }
    }

    @Override
    public void updatehash() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].projectiles.contains(this)) {
            this.level.tilehash[this.tileIndex].projectiles.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].projectiles.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    @Override
    public void add() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].projectiles.contains(this)) {
            this.level.tilehash[this.tileIndex].projectiles.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    @Override
    public void remove() {
        this.level.tilehash[this.tileIndex].projectiles.remove(this);
        this.level.spawners.remove(this);
        this.removed = true;
        --debugcount;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$game$entity$projectile$particles$Spawner$type() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$game$entity$projectile$particles$Spawner$type;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[type.values().length];
        try {
            arrn[type.WATER.ordinal()] = 5;
        }
        catch (NoSuchFieldError v1) {}
        try {
            arrn[type.ARROWJUNK.ordinal()] = 4;
        }
        catch (NoSuchFieldError v1) {}
        try {
            arrn[type.BLOOD.ordinal()] = 3;
        }
        catch (NoSuchFieldError v2) {}
        try {
            arrn[type.FIREBALL.ordinal()] = 1;
        }
        catch (NoSuchFieldError v3) {}
        try {
            arrn[type.ROCK.ordinal()] = 2;
        }
        catch (NoSuchFieldError v4) {}
        $SWITCH_TABLE$game$entity$projectile$particles$Spawner$type = arrn;
        return $SWITCH_TABLE$game$entity$projectile$particles$Spawner$type;
    }

    public static enum type {
        FIREBALL,
        ROCK,
        BLOOD,
        ARROWJUNK,
        WATER;
    }

}

