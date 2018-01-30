/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface;

public abstract class WindowBasics {
    public boolean visible = false;

    public abstract void update();

    public abstract void render();

    public abstract boolean check();

    public void setVisible(boolean bool) {
        this.visible = bool;
    }
}

