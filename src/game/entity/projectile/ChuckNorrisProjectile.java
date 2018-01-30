/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile;

import game.entity.mob.Mob;

public class ChuckNorrisProjectile
extends Projectile {
    public ChuckNorrisProjectile(int x, int y, double dir, Mob mob) {
        super(x, y, dir, mob);
        this.cooldown = 1;
        this.range = 525.0;
        this.speed = 20.0;
        this.damage = 25.0;
        this.size = 45;
        this.xSpeed = this.speed * Math.cos(this.angle);
        this.ySpeed = this.speed * Math.sin(this.angle);
    }

    @Override
    public void update() {
        this.checkIfEnemy();
        this.move();
    }

    @Override
    protected void checkIfEnemy() {
        this.getHashIndexes(this.x, this.y);
        int i = 0;
        while (i < 9) {
            if (this.tileIndexes[i] >= 0 && this.tileIndexes[i] < this.level.tilehash.length && this.size != this.level.tilehash[this.tileIndexes[i]].mobs.size()) {
                int j = 0;
                while (j < this.level.tilehash[this.tileIndexes[i]].mobs.size()) {
                    if (this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getClass() != this.mob.getClass() && Math.abs(this.x - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getX()) <= this.size + this.mob.getLargness() && Math.abs(this.y - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getY()) <= this.size + this.mob.getLargness()) {
                        this.level.tilehash[this.tileIndexes[i]].mobs.get(j).doDamage(this.damage, this.mob);
                    }
                    ++j;
                }
            }
            ++i;
        }
    }

    @Override
    public void render() {
    }

    protected void move() {
        this.xx += this.xSpeed;
        this.yy += this.ySpeed;
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        if (this.distance() >= this.range) {
            this.remove();
        }
    }
}

