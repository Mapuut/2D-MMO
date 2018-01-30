package game.entity.projectile;


import game.entity.mob.Mob;
import game.graphics.Screen;
import game.graphics.Sprite;


public class DarthVaderPower extends Projectile {
	
	private Sprite sprite;
	
    public DarthVaderPower(int x, int y, double dir, Mob mob) {
        super(x, y, dir, mob);
        this.x = x + random.nextInt(40) - 20;
        this.y = y + random.nextInt(40) - 20;
        this.xx = this.x;
        this.yy = this.y;
        this.cooldown = 1;
        this.range = 1450.0 + random.nextInt(200);
        this.speed = 5.0 + random.nextDouble() * 2.0;
        this.damage = 0.01;
        this.size = 14;
        this.xSpeed = this.speed * Math.cos(this.angle);
        this.ySpeed = this.speed * Math.sin(this.angle);
        int spite_choose_int = Projectile.random.nextInt(6);
        if (spite_choose_int == 0){
        	sprite = Sprite.blackparticle1;
        }
        else if (spite_choose_int == 1){
        	sprite = Sprite.blackparticle2;
        }
        else if (spite_choose_int == 2){
        	sprite = Sprite.blackparticle3;
        }
        else if (spite_choose_int == 3){
        	sprite = Sprite.blackparticle4;
        }
        else if (spite_choose_int == 4){
        	sprite = Sprite.blackparticle5;
        }
        else if (spite_choose_int == 5){
        	sprite = Sprite.blackparticle6;
        }
        
        
        
        
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
        Screen.renderSpriteTrancparent(this.x - 16, this.y - 16, sprite);
    }

    protected void move() {
        if (!this.projectileCollision(this.xx + this.xSpeed, this.yy + this.ySpeed, this.size)) {
            this.xx += this.xSpeed;
            this.yy += this.ySpeed;
            this.x = (int)this.xx;
            this.y = (int)this.yy;
        } else {
            this.remove();
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
                    if (this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getClass() != this.mob.getClass() && Math.abs(this.x - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getX()) <= this.size + this.mob.getLargness() && Math.abs(this.y - this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getY()) <= this.size + this.mob.getLargness()) {
                    	this.level.tilehash[this.tileIndexes[i]].mobs.get(j).move(this.xSpeed/2, this.ySpeed/2, this.level.tilehash[this.tileIndexes[i]].mobs.get(j).getLargness());

                    }
                    ++j;
                }
            }
            ++i;
        }
        
    }

}

