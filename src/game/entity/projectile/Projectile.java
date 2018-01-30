/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile;

import game.entity.HashEntity;
import game.entity.mob.Mob;

public abstract class Projectile
extends HashEntity {
    protected final int xOrigin;
    protected final int yOrigin;
    protected double angle;
    protected Mob mob;
    protected int cooldown = 0;
    protected double xSpeed = 0.0;
    protected double ySpeed = 0.0;
    protected double speed = 0.0;
    protected double rateOfFire;
    protected double range;
    protected double damage;
    protected int size;
    protected double xx;
    protected double yy;
    protected int[] tileIndexes = new int[9];
    public static int debugcount = 0;

    public Projectile(int x, int y, double dir, Mob mob) {
        this.xOrigin = x;
        this.yOrigin = y;
        this.angle = dir;
        this.xx = x;
        this.yy = y;
        this.mob = mob;
        this.x = x;
        this.y = y;
        ++debugcount;
    }

    protected void checkIfEnemy() {
        this.getHashIndexes(this.x, this.y);
        int i = 0;
        while (i < 9) {
            int size;
            if (this.tileIndexes[i] >= 0 && this.tileIndexes[i] < this.level.tilehash.length && (size = this.level.tilehash[this.tileIndexes[i]].mobs.size()) != 0) {
                int j = 0;
                while (j < size) {
                    if (this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getClass() != this.mob.getClass() && Math.abs(this.x - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getX()) <= this.size + this.mob.getLargness() && Math.abs(this.y - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getY()) <= this.size + this.mob.getLargness()) {
                        this.level.tilehash[this.tileIndexes[i]].mobs.get(j).doDamage(this.damage, this.mob);
                        this.remove();
                        return;
                    }
                    ++j;
                }
            }
            ++i;
        }
    }

    @Override
    public void updatehash() {
        int tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (tileIndex < 0 || tileIndex >= this.level.tilehash.length) {
            this.remove();
            return;
        }
        this.tileIndex = tileIndex;
        if (!this.level.tilehash[this.tileIndex].projectiles.contains(this)) {
            this.level.tilehash[this.tileIndex].projectiles.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].projectiles.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    public boolean projectileCollision(double x, double y, int largeness) {
        int checkcurrentx = (int)(x - (double)largeness) >> 5;
        int checkcurrenty = (int)(y - (double)largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x + (double)largeness) >> 5;
        checkcurrenty = (int)(y + (double)largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x + (double)largeness) >> 5;
        checkcurrenty = (int)(y - (double)largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x - (double)largeness) >> 5;
        checkcurrenty = (int)(y + (double)largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        return false;
    }

    @Override
    public void add() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (this.tileIndex < 0 || this.tileIndex >= this.level.tilehash.length) {
            --debugcount;
            this.level.projectiles.remove(this);
            return;
        }
        if (!this.level.tilehash[this.tileIndex].projectiles.contains(this)) {
            this.level.tilehash[this.tileIndex].projectiles.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].projectiles.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    protected void getHashIndexes(int x, int y) {
        this.tileIndexes[0] = (x >> 5) + (y >> 5) * this.level.tilesx;
        this.tileIndexes[1] = (x + 32 >> 5) + (y + 32 >> 5) * this.level.tilesx;
        this.tileIndexes[2] = (x - 32 >> 5) + (y - 32 >> 5) * this.level.tilesx;
        this.tileIndexes[3] = (x + 32 >> 5) + (y - 32 >> 5) * this.level.tilesx;
        this.tileIndexes[4] = (x - 32 >> 5) + (y + 32 >> 5) * this.level.tilesx;
        this.tileIndexes[5] = (x + 32 >> 5) + (y >> 5) * this.level.tilesx;
        this.tileIndexes[6] = (x >> 5) + (y + 32 >> 5) * this.level.tilesx;
        this.tileIndexes[7] = (x >> 5) + (y - 32 >> 5) * this.level.tilesx;
        this.tileIndexes[8] = (x - 32 >> 5) + (y >> 5) * this.level.tilesx;
    }

    @Override
    public void remove() {
        this.level.tilehash[this.tileIndex].projectiles.remove(this);
        this.level.projectiles.remove(this);
        this.removed = true;
        --debugcount;
    }

    public double distance() {
        double dist = 0.0;
        dist = Math.sqrt(Math.abs((this.xOrigin - this.x) * (this.xOrigin - this.x)) + Math.abs((this.yOrigin - this.y) * (this.yOrigin - this.y)));
        return dist;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCooldown() {
        return this.cooldown;
    }
}

