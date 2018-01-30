/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.minimap;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.minimap.WindowMiniMap;

public class MiniMap {
    private Sprite sprite = new Sprite("/level/spawnlevelGround.png", 200, 200);
    private WindowMiniMap window;

    public MiniMap(WindowMiniMap window) {
        this.window = window;
    }

    public void render() {
        Screen.renderMiniMap(this.window.x + 4, this.window.y + 4, this.window.width - 8, this.window.height - 8, this.sprite);
    }
}

