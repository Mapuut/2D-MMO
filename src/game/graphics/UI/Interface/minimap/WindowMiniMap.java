/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.minimap;

import game.graphics.Screen;
import game.graphics.UI.Interface.InterfaceManager;
import game.graphics.UI.Interface.WindowBasics;
import game.graphics.UI.Interface.minimap.MiniMap;
import game.input.Mouse;

public class WindowMiniMap
extends WindowBasics {
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    private MiniMap minimap;
    private int mouseHoldX;
    private int mouseHoldY;
    private boolean dragging;

    public WindowMiniMap(int x, int y, int width, int height) {
        this.minimap = new MiniMap(this);
        this.mouseHoldX = 0;
        this.mouseHoldY = 0;
        this.dragging = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (x + width > Screen.width) {
            this.x = Screen.width - width;
        }
        if (y + height > Screen.height) {
            this.y = Screen.height - height;
        }
    }

    @Override
    public void update() {
        if (!this.check()) {
            this.clear();
            return;
        }
        if (Mouse.getButton() == 1) {
            if (this.dragging) {
                this.x += Mouse.getX() - this.x - this.mouseHoldX;
                this.y += Mouse.getY() - this.y - this.mouseHoldY;
                if (this.x + this.width > Screen.width) {
                    this.x = Screen.width - this.width;
                }
                if (this.y + this.height > Screen.height) {
                    this.y = Screen.height - this.height;
                }
                if (this.x < 0) {
                    this.x = 0;
                }
                if (this.y < 0) {
                    this.y = 0;
                }
            } else {
                this.mouseHoldX = Mouse.getX() - this.x;
                this.mouseHoldY = Mouse.getY() - this.y;
            }
            this.dragging = true;
        } else {
            this.dragging = false;
        }
    }

    @Override
    public void render() {
        this.minimap.render();
    }

    @Override
    public boolean check() {
        if (Mouse.getX() < this.x || Mouse.getX() > this.x + this.width || Mouse.getY() < this.y || Mouse.getY() > this.y + this.height) {
            if (Mouse.getButton() == 1) {
                return true;
            }
            return false;
        }
        return true;
    }

    private void clear() {
        InterfaceManager.currentMode = InterfaceManager.mode.GAME;
    }
}

