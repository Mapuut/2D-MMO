/*
 * Decompiled with CFR 0_115.
 */
package game.entity.projectile;

import game.entity.mob.Mob;
import game.entity.projectile.particles.Spawner;
import game.graphics.Screen;
import game.graphics.Sprite;

public class FireProjectile
extends Projectile {
    public FireProjectile(int x, int y, double dir, Mob mob) {
        super(x, y, dir, mob);
        this.x = x + random.nextInt(40) - 20;
        this.y = y + random.nextInt(40) - 20;
        this.xx = this.x;
        this.yy = this.y;
        this.cooldown = 2;
        this.range = 350.0 + random.nextInt(300);
        this.speed = 4.0 + random.nextDouble() * 2.0;
        this.damage = 0.3;
        this.size = 14;
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
        Screen.renderSpriteTrancparent(this.x - 16, this.y - 16, Sprite.fire);
    }

    protected void move() {
        if (!this.projectileCollision(this.xx + this.xSpeed, this.yy + this.ySpeed, this.size)) {
            this.xx += this.xSpeed;
            this.yy += this.ySpeed;
            this.x = (int)this.xx;
            this.y = (int)this.yy;
        } else {
            this.remove();
            this.level.add(new Spawner(this.x, this.y, 25, Spawner.type.FIREBALL, this.level));
        }
        if (this.distance() >= this.range) {
            this.remove();
        }
    }
    
    protected void checkIfEnemy() {
        this.getHashIndexes(this.x, this.y);
        int i = 0;
        while (i < 9) {
            if (this.tileIndexes[i] >= 0 && this.tileIndexes[i] < this.level.tilehash.length && this.size != this.level.tilehash[this.tileIndexes[i]].mobs.size()) {
                int j = 0;
                while (j < this.level.tilehash[this.tileIndexes[i]].mobs.size()) {
                    if (this.level.tilehash[this.tileIndexes[i]].mobs.get(j) != this.mob && Math.abs(this.x - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getX()) <= this.size + this.mob.getLargness() && Math.abs(this.y - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getY()) <= this.size + this.mob.getLargness()) {
                    	this.level.tilehash[this.tileIndexes[i]].mobs.get(j).move(this.xSpeed/3, this.ySpeed/3, this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getLargness());
                    	this.level.tilehash[this.tileIndexes[i]].mobs.get(j).doDamage(this.damage, this.mob);
                    	int ran = Projectile.random.nextInt(20);
                    	if (ran == 0){
                        	this.remove();
                        	break;
                    	}
                    	

                    }
                    ++j;
                }
            }
            ++i;
        }
        
    }


    protected void checkIfEnemyt() {
        this.getHashIndexes(this.x, this.y);
        int i = 0;
        while (i < 9) {
            int size;
            if (this.tileIndexes[i] >= 0 && this.tileIndexes[i] < this.level.tilehash.length && (size = this.level.tilehash[this.tileIndexes[i]].mobs.size()) != 0) {
                int j = 0;
                while (j < size) {
                    if (this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getClass() != this.mob.getClass() && Math.abs(this.x - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getX()) <= this.size + this.mob.getLargness() && Math.abs(this.y - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getY()) <= this.size + this.mob.getLargness()) {
                        this.level.tilehash[this.tileIndexes[i]].mobs.get(j).xdouble += this.xSpeed;
                        this.level.tilehash[this.tileIndexes[i]].mobs.get(j).ydouble += this.ySpeed;	
                    	this.level.tilehash[this.tileIndexes[i]].mobs.get(j).doDamage(this.damage, this.mob);

                        this.remove();
                        this.level.add(new Spawner(this.x, this.y, 25, Spawner.type.FIREBALL, this.level));
                        return;
                    }
                    ++j;
                }
            }
            ++i;
        }
    }
}

