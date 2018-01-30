/*
 * Decompiled with CFR 0_115.
 */
package game.util;

import game.graphics.Sprite;

public final class Rotator {
    public static Sprite rotate(Sprite sprite, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        if (cos > 0.95) {
            cos = 1.0;
        }
        if (cos < -0.95) {
            cos = -1.0;
        }
        if (cos < 0.05 && cos >= 0.0) {
            cos = 0.0;
        }
        if (cos > -0.05 && cos <= 0.0) {
            cos = 0.0;
        }
        if (sin > 0.95) {
            sin = 1.0;
        }
        if (sin < -0.95) {
            sin = -1.0;
        }
        if (sin < 0.05 && sin > 0.0) {
            sin = 0.0;
        }
        if (sin > -0.05 && sin <= 0.0) {
            sin = 0.0;
        }
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        int[] pixels = new int[width * height];
        double centerx = width / 2;
        double centery = height / 2;
        int x = 0;
        while (x < width) {
            int y = 0;
            while (y < height) {
                double m = (double)x - centerx;
                double n = (double)y - centery;
                int j = (int)(m * cos + n * sin + centerx);
                int k = (int)(n * cos - m * sin + centery);
                if (j >= 0 && j < width && k >= 0 && k < height) {
                    pixels[y * width + x] = sprite.pixels[k * width + j];
                }
                ++y;
            }
            ++x;
        }
        Sprite newSprite = new Sprite(width, height, pixels);
        return newSprite;
    }
}

