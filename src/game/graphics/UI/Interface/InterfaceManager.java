/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface;

import game.Game;
import game.graphics.UI.Interface.WindowBasics;
import game.graphics.UI.Interface.clickmenu.ClickMenu;
import game.graphics.UI.Interface.inventory.WindowInventory;
import game.graphics.UI.Interface.minimap.WindowMiniMap;
import game.input.Mouse;

public class InterfaceManager {
    public static mode currentMode = mode.GAME;
    public static WindowBasics activated;
    private static WindowBasics minimap;
    private static WindowBasics inventory;
    private static WindowBasics leftclick;

    static {
        minimap = new WindowMiniMap(Game.width - 240, 40, 200, 152);
        inventory = new WindowInventory(Game.width - 240, Game.height - 280);
        leftclick = new ClickMenu();
    }

    public static void update() {
        leftclick.check();
        if (currentMode == mode.GAME) {
            InterfaceManager.checkAll();
        } else {
            activated.update();
        }
    }

    private static void checkAll() {
        if (Mouse.getButton() == 1) {
            return;
        }
        if (minimap.check()) {
            currentMode = mode.MINIMAP;
            activated = minimap;
            return;
        }
        if (inventory.check()) {
            currentMode = mode.INVENTORY;
            activated = inventory;
            return;
        }
        currentMode = mode.GAME;
    }

    public static void render() {
        inventory.render();
        minimap.render();
        if (InterfaceManager.leftclick.visible) {
            leftclick.render();
        }
    }

    public static mode getMode() {
        return currentMode;
    }

    public static enum mode {
        GAME,
        MINIMAP,
        INVENTORY,
        LEFT_CLICK,
        MENU;

    }

}

