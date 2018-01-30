/*
 * Decompiled with CFR 0_115.
 */
package game.entity.effects;

import game.graphics.Screen;
import game.graphics.Sprite;

public class SpriteEffectElements {
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;
    private int life = 0;
    public boolean removed = false;
    private Sprite sprite;

    public SpriteEffectElements(int x, int y, double xSpeed, double ySpeed, int life, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.sprite = sprite;
        this.life = life;
    }

    public void update() {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
        --this.life;
        if (this.life <= 0) {
            this.removed = true;
        }
    }

    public void render() {
        Screen.renderSpriteTrancparent((int)this.x - this.sprite.getWidth() / 2, (int)this.y - this.sprite.getWidth() / 2, this.sprite);
    }
}

