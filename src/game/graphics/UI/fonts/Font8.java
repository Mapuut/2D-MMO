/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.fonts;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import java.io.PrintStream;

public class Font8 {
    private static SpriteSheet font = new SpriteSheet("/UI/font/font.png", 182, 28, 7);
    private static Sprite[] characters = Font8.split(font);
    private static String charIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$+-*/=%\"'#@&_(),;:?!\\|{}<>[]`^~. ";

    public static void render(int x, int y, String text, boolean fixed) {
        int xOffset = 0;
        int i = 0;
        while (i < text.length()) {
            char currentChar = text.charAt(i);
            int index = charIndex.indexOf(currentChar);
            if (index == -1) {
                System.err.println("Text Char not foound error!");
            }
            Screen.renderTextChar(x + xOffset, y, characters[index], -1, fixed);
            xOffset += Font8.charOffset(index);
            ++i;
        }
    }

    private static int charOffset(int index) {
        if (index == 93) {
            return 2;
        }
        if (index == 94) {
            return 3;
        }
        if (index == 34 || index == 37 || index == 70 || index == 78 || index == 79 || index == 81 || index == 83) {
            return 2;
        }
        if (index == 35 || index == 53 || index == 75 || index == 76 || index == 77 || index == 90 || index == 88) {
            return 3;
        }
        if (index == 89) {
            return 3;
        }
        if (index == 2 || index == 4 || index == 5 || index == 8 || index == 11 || index == 19 || index == 25) {
            return 4;
        }
        if (index == 28 || index == 43 || index == 45 || index == 49 || index == 63 || index == 64 || index == 65) {
            return 4;
        }
        if (index == 67 || index == 84 || index == 85 || index == 86 || index == 87) {
            return 4;
        }
        if (index == 91) {
            return 4;
        }
        if (index == 12 || index == 22 || index == 38 || index == 48 || index == 66 || index == 68 || index == 71) {
            return 6;
        }
        if (index == 72 || index == 73 || index == 82) {
            return 6;
        }
        return 5;
    }

    private static Sprite[] split(SpriteSheet sheet) {
        int size = 7;
        int amount = sheet.width * sheet.height / (size * size);
        Sprite[] sprites = new Sprite[amount];
        int current = 0;
        int[] pixels = new int[size * size];
        int yp = 0;
        while (yp < sheet.height / size) {
            int xp = 0;
            while (xp < sheet.width / size) {
                int y = 0;
                while (y < size) {
                    int x = 0;
                    while (x < size) {
                        int yo = y + yp * size;
                        int xo = x + xp * size;
                        pixels[x + y * size] = sheet.pixels[xo + yo * sheet.width];
                        ++x;
                    }
                    ++y;
                }
                sprites[current++] = new Sprite(pixels, size, size);
                ++xp;
            }
            ++yp;
        }
        return sprites;
    }
}

