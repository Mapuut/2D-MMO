/*
 * Decompiled with CFR 0_115.
 */
package game.graphics;

import game.graphics.Sprite;

public class AnimatedSprite {
    private Sprite[] sprites;
    public int size = 0;

    public AnimatedSprite(Sprite sprite) {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        this.size = h / w;
        this.sprites = new Sprite[this.size];
        int i = 0;
        while (i < this.size) {
            int[] pixels = new int[w * w];
            int y = 0;
            while (y < w) {
                int x = 0;
                while (x < w) {
                    pixels[x + y * w] = sprite.pixels[x + (y * w + w * w * i)];
                    ++x;
                }
                ++y;
            }
            this.sprites[i] = new Sprite(w, w, pixels);
            ++i;
        }
    }

    public Sprite getSprite(int frame) {
        return this.sprites[frame];
    }
}

