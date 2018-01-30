/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile;

import game.entity.mob.Mob;

public class HandProjectile
extends Projectile {
    public HandProjectile(int x, int y, double dir, Mob mob) {
        super(x, y, dir, mob);
        this.cooldown = 90;
        this.range = 25.0;
        this.speed = 10.0;
        this.damage = 2.0;
        this.size = 15;
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

