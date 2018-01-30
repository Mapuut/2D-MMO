/*
 * Decompiled with CFR 0_115.
 */
package game;

import game.Game;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

public class GameApplet
extends Applet
implements Runnable {
    private static final long serialVersionUID = 1;
    private static Game game = new Game();

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        this.add(game);
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public void stop() {
        game.stop();
    }

    @Override
    public void run() {
        game.run();
    }
}

