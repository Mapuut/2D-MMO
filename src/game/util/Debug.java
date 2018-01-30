/*
 * Decompiled with CFR 0_115.
 */
package game.util;

import game.graphics.Screen;

public class Debug {
    private Debug() {
    }

    public static void drawRect(int x, int y, int width, int height, boolean fixed) {
        Screen.drawRect(x, y, width, height, -65536, fixed);
    }

    public static void drawRect(int x, int y, int width, int height, int color, boolean fixed) {
        Screen.drawRect(x, y, width, height, color, fixed);
    }
}

