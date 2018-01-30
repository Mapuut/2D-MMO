package game.entity.projectile.particles;

import game.entity.Entity;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.level.Level;

public class WaterParticle extends Entity {
    private Sprite sprite;
    private int life;
    protected double xx;
    protected double yy;
    protected double xa;
    protected double ya;

    public WaterParticle(int x, int y, Level level) {
        this.level = level;
        int currentSprite = random.nextInt(3);
        if (currentSprite == 0) {
            this.sprite = Sprite.water1;
        } else if (currentSprite == 1) {
            this.sprite = Sprite.water2;
        } else if (currentSprite == 2) {
            this.sprite = Sprite.water3;
        }
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = random.nextInt(60) - 30;
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
    }

    @Override
    public void render() {
        Screen.renderSprite(this.x, this.y, this.sprite);
    }

    @Override
    public void update() {
        --this.life;
        if (this.life <= 0) {
            this.remove();
        }
        this.move();
    }

    private void move() {
        if (this.level.particleCollision((int)(this.xx + this.xa), (int)(this.yy + this.ya))) {
            if (this.level.particleCollision((int)(this.xx - this.xa), (int)(this.yy + this.ya))) {
                this.ya = - this.ya;
            } else {
                this.xa = - this.xa;
            }
        }
        this.x = (int)this.xx;
        this.y = (int)this.yy;
        this.xx += this.xa;
        this.yy += this.ya;
        this.xa *= 0.9;
        this.ya *= 0.9;
    }
}
