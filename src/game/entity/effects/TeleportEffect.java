/*
 * Decompiled with CFR 0_115.
 */
package game.entity.effects;

import game.entity.effects.Effect;
import game.entity.effects.SpriteEffectElements;
import game.graphics.Sprite;
import game.level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeleportEffect
extends Effect {
    private List<SpriteEffectElements> sprites = new ArrayList<SpriteEffectElements>();
    private int time = 0;

    public TeleportEffect(int x, int y, Level level, boolean first) {
        super(x, y, level);
        int count = random.nextInt(5) + 70;
        if (first) {
            double xposition;
            double yposition;
            double yspeed;
            int xmultiplayer;
            int ymultiplayer;
            Sprite randomsprite;
            int intrandomsprite;
            double xspeed;
            int i = 0;
            while (i < count) {
                randomsprite = Sprite.blackparticle3;
                intrandomsprite = random.nextInt(6);
                if (intrandomsprite == 0) {
                    randomsprite = Sprite.blackparticle1;
                }
                if (intrandomsprite == 1) {
                    randomsprite = Sprite.blackparticle2;
                }
                if (intrandomsprite == 2) {
                    randomsprite = Sprite.blackparticle3;
                }
                if (intrandomsprite == 3) {
                    randomsprite = Sprite.blackparticle4;
                }
                if (intrandomsprite == 4) {
                    randomsprite = Sprite.blackparticle5;
                }
                if (intrandomsprite == 5) {
                    randomsprite = Sprite.blackparticle6;
                }
                xmultiplayer = 1;
                ymultiplayer = 1;
                if (random.nextBoolean()) {
                    xmultiplayer = -1;
                }
                if (random.nextBoolean()) {
                    ymultiplayer = -1;
                }
                xposition = (double)x + (double)((random.nextInt(30) + 60) * xmultiplayer) * ((random.nextGaussian() + 2.0) / 2.0);
                yposition = (double)y + (double)((random.nextInt(30) + 60) * ymultiplayer) * ((random.nextGaussian() + 2.0) / 2.0);
                xspeed = ((double)x - xposition) / 35.0 * (((double)random.nextInt(21) + 90.0) / 100.0);
                yspeed = ((double)y - yposition) / 35.0 * (((double)random.nextInt(21) + 90.0) / 100.0);
                this.sprites.add(new SpriteEffectElements((int)xposition, (int)yposition, xspeed, yspeed, 35 + random.nextInt(10), randomsprite));
                ++i;
            }
            i = 0;
            while (i < count - 40) {
                randomsprite = Sprite.smoke3;
                intrandomsprite = random.nextInt(3);
                if (intrandomsprite == 0) {
                    randomsprite = Sprite.smoke1;
                }
                if (intrandomsprite == 1) {
                    randomsprite = Sprite.smoke2;
                }
                xmultiplayer = 1;
                ymultiplayer = 1;
                if (random.nextBoolean()) {
                    xmultiplayer = -1;
                }
                if (random.nextBoolean()) {
                    ymultiplayer = -1;
                }
                xposition = (double)x + (double)((random.nextInt(20) + 40) * xmultiplayer) * random.nextGaussian();
                yposition = (double)y + (double)((random.nextInt(20) + 40) * ymultiplayer) * random.nextGaussian();
                xspeed = ((double)x - xposition) / 45.0 * (((double)random.nextInt(21) + 90.0) / 100.0);
                yspeed = ((double)y - yposition) / 45.0 * (((double)random.nextInt(21) + 90.0) / 100.0);
                this.sprites.add(new SpriteEffectElements((int)xposition, (int)yposition, xspeed, yspeed, 40 + random.nextInt(10), randomsprite));
                ++i;
            }
        } else {
            int xspeedmultiplayer;
            double yspeed;
            double xspeed;
            Sprite randomsprite;
            int intrandomsprite;
            int yspeedmultiplayer;
            int i = 0;
            while (i < count) {
                randomsprite = Sprite.blackparticle3;
                intrandomsprite = random.nextInt(6);
                if (intrandomsprite == 0) {
                    randomsprite = Sprite.blackparticle1;
                }
                if (intrandomsprite == 1) {
                    randomsprite = Sprite.blackparticle2;
                }
                if (intrandomsprite == 2) {
                    randomsprite = Sprite.blackparticle3;
                }
                if (intrandomsprite == 3) {
                    randomsprite = Sprite.blackparticle4;
                }
                if (intrandomsprite == 4) {
                    randomsprite = Sprite.blackparticle5;
                }
                if (intrandomsprite == 5) {
                    randomsprite = Sprite.blackparticle6;
                }
                xspeedmultiplayer = 1;
                yspeedmultiplayer = 1;
                if (random.nextBoolean()) {
                    xspeedmultiplayer = -1;
                }
                if (random.nextBoolean()) {
                    yspeedmultiplayer = -1;
                }
                xspeed = (random.nextGaussian() + 2.0) * (double)xspeedmultiplayer / 2.0;
                yspeed = (random.nextGaussian() + 2.0) * (double)yspeedmultiplayer / 2.0;
                this.sprites.add(new SpriteEffectElements(x, y, xspeed, yspeed, 40 + random.nextInt(15), randomsprite));
                ++i;
            }
            i = 0;
            while (i < count - 40) {
                randomsprite = Sprite.smoke3;
                intrandomsprite = random.nextInt(3);
                if (intrandomsprite == 0) {
                    randomsprite = Sprite.smoke1;
                }
                if (intrandomsprite == 1) {
                    randomsprite = Sprite.smoke2;
                }
                xspeedmultiplayer = 1;
                yspeedmultiplayer = 1;
                if (random.nextBoolean()) {
                    xspeedmultiplayer = -1;
                }
                if (random.nextBoolean()) {
                    yspeedmultiplayer = -1;
                }
                xspeed = (random.nextGaussian() + 1.0) * (double)xspeedmultiplayer / 2.0;
                yspeed = (random.nextGaussian() + 1.0) * (double)yspeedmultiplayer / 2.0;
                this.sprites.add(new SpriteEffectElements(x, y, xspeed, yspeed, 40 + random.nextInt(10), randomsprite));
                ++i;
            }
        }
    }

    @Override
    public void update() {
        this.updatehash();
        ++this.time;
        int i = 0;
        while (i < this.sprites.size()) {
            this.sprites.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.sprites.size()) {
            if (this.sprites.get((int)i).removed) {
                this.sprites.remove(i);
            }
            ++i;
        }
        if (this.sprites.size() == 0 || this.time >= 2000) {
            this.remove();
        }
    }

    @Override
    public void render() {
        if (this.time << 3 >= this.sprites.size()) {
            this.time = this.sprites.size() >> 3;
        }
        int i = 0;
        while (i < this.time << 3) {
            this.sprites.get(i).render();
            ++i;
        }
    }
}

