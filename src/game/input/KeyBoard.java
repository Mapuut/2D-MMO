/*
 * Decompiled with CFR 0_115.
 */
package game.input;

import game.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard
implements KeyListener {
    public static boolean[] keys = new boolean[300];
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean space;

    public static void update() {
        up = keys[38] || keys[87];
        down = keys[40] || keys[83];
        left = keys[37] || keys[65];
        right = keys[39] || keys[68];
        space = keys[32];
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyBoard.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyBoard.keys[e.getKeyCode()] = false;
        if (e.getKeyCode() == 81) {
            Game.debug = !Game.debug;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

