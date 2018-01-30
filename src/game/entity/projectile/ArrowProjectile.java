/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile;

import game.entity.mob.Mob;
import game.entity.projectile.particles.Spawner;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.util.Rotator;

public class ArrowProjectile
extends Projectile {
    protected Sprite sprite;

    public ArrowProjectile(int x, int y, double dir, Mob mob) {
        super(x, y, dir, mob);
        this.cooldown = 90;
        this.sprite = Rotator.rotate(Sprite.arrow, this.angle);
        this.range = 750.0;
        this.speed = 7.0 + random.nextDouble() * 4.0;
        this.damage = 2.0;
        this.size = 6;
        this.xSpeed = this.speed * Math.cos(this.angle);
        this.ySpeed = this.speed * Math.sin(this.angle);
    }

    @Override
    public void update() {
        this.checkIfEnemy();
        if (this.removed) {
            return;
        }
        this.move();
    }

    @Override
    public void render() {
        Screen.renderSpriteTrancparent(this.x - 16, this.y - 16, this.sprite);
    }

    protected void move() {
        if (!this.projectileCollision(this.xx + this.xSpeed, this.yy + this.ySpeed, this.size)) {
            this.xx += this.xSpeed;
            this.yy += this.ySpeed;
            this.x = (int)this.xx;
            this.y = (int)this.yy;
        } else {
            this.remove();
            this.level.add(new Spawner(this.x, this.y, 40, Spawner.type.ARROWJUNK, this.level));
        }
        if (this.distance() >= this.range) {
            this.remove();
        }
    }

    @Override
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
                        this.level.add(new Spawner(this.x, this.y, 25, Spawner.type.ARROWJUNK, this.level));
                        return;
                    }
                    ++j;
                }
            }
            ++i;
        }
    }
}

