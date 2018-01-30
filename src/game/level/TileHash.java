/*
 * Decompiled with CFR 0_115.
 */
package game.level;

import game.entity.HashEntity;
import game.entity.effects.Effect;
import game.entity.items.ItemBag;
import game.entity.mob.Mob;
import game.entity.objects.Object;
import game.entity.projectile.particles.RockParticle;
import game.level.Level;
import game.level.tile.Tile;
import game.util.MobPositionComparator;
import java.util.ArrayList;
import java.util.Collections;

public class TileHash {
    public Tile tile;
    public Tile tile2;
    public int layer = 0;
    public boolean solid = false;
    public boolean visible = false;
    public ArrayList<ItemBag> items = new ArrayList<ItemBag>(1);
    public ArrayList<Mob> mobs = new ArrayList<Mob>(1);
    public ArrayList<Object> objects = new ArrayList<Object>(1);
    public ArrayList<HashEntity> projectiles = new ArrayList<HashEntity>(1);
    public ArrayList<Effect> effects = new ArrayList<Effect>(1);
    private int w = 0;
    private int h = 0;
    public static int time = 0;
    public static Level level;

    public void setlevel(Level level) {
        TileHash.level = level;
    }

    public TileHash(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void render() {
        int i;
        if (this.items.size() != 0) {
            if (this.items.size() < 3) {
                i = 0;
                while (i < this.items.size()) {
                    this.items.get(i).render();
                    ++i;
                }
            } else {
                i = 0;
                while (i < 3) {
                    this.items.get(i).render();
                    ++i;
                }
            }
        }
        Collections.sort(this.mobs, new MobPositionComparator());
        i = 0;
        while (i < this.mobs.size()) {
            this.mobs.get(i).render();
            ++i;
        }
        i = 0;
        while (i < this.objects.size()) {
            this.objects.get(i).render();
            ++i;
        }
        i = 0;
        while (i < this.projectiles.size()) {
            this.projectiles.get(i).render();
            ++i;
        }
        i = 0;
        while (i < this.objects.size()) {
            this.objects.get(i).rendertop();
            ++i;
        }
    }

    public void rendertop() {
        int i = 0;
        while (i < this.effects.size()) {
            this.effects.get(i).render();
            ++i;
        }
    }

    public void update() {
        int i = 0;
        while (i < this.mobs.size()) {
            level.add(new RockParticle(this.w, this.h, level));
            ++i;
        }
        i = 0;
        while (i < this.objects.size()) {
            level.add(new RockParticle(this.w, this.h, level));
            ++i;
        }
        i = 0;
        while (i < this.projectiles.size()) {
            level.add(new RockParticle(this.w, this.h, level));
            ++i;
        }
        i = 0;
        while (i < this.items.size()) {
            level.add(new RockParticle(this.w, this.h, level));
            if (i > 10) break;
            ++i;
        }
        i = 0;
        while (i < this.effects.size()) {
            level.add(new RockParticle(this.w, this.h, level));
            ++i;
        }
    }
}

