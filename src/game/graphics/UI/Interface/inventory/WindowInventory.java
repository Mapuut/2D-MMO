/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.inventory;

import game.Game;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.UI.Interface.InterfaceManager;
import game.graphics.UI.Interface.WindowBasics;
import game.graphics.UI.Interface.inventory.Inventory;
import game.graphics.UI.Interface.inventory.InventoryRegion;
import game.input.Mouse;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class WindowInventory
extends WindowBasics {
    public static ArrayList<InventoryRegion> windows = new ArrayList();
    private static int width = 156;
    private static int height = 112;
    public static int currentwindow = 0;
    public static InvMode invmode = InvMode.REGULAR;
    private static boolean inRegion = false;
    private static boolean wasPressed1 = false;

    public WindowInventory(int x, int y) {
        if (x + width > Screen.width) {
            x = Screen.width - width;
        }
        if (y + height > Screen.height) {
            y = Screen.height - height;
        }
        windows.add(new InventoryRegion(x, y, width, height));
        int i = 0;
        while (i < 3) {
            WindowInventory.windows.get((int)0).inventory.add(Game.player.inventory.get(i));
            ++i;
        }
    }

    @Override
    public void update() {
        if (invmode == InvMode.REGULAR) {
            if (!this.check()) {
                InterfaceManager.currentMode = InterfaceManager.mode.GAME;
                return;
            }
            if (Mouse.getButton() != 1 && wasPressed1) {
                wasPressed1 = false;
            }
            if (Mouse.getButton() == 1 && !wasPressed1) {
                wasPressed1 = true;
                windows.get(currentwindow).updateSelected();
                if (WindowInventory.windows.get((int)WindowInventory.currentwindow).selectedtype == InventoryRegion.Selected.BACKGROUND) {
                    invmode = InvMode.DRAGGING;
                } else if (WindowInventory.windows.get((int)WindowInventory.currentwindow).selectedtype == InventoryRegion.Selected.INVENTORYICON) {
                    invmode = InvMode.DRAGGING_INV;
                    WindowInventory.windows.get((int)WindowInventory.currentwindow).activatedInv = WindowInventory.windows.get((int)WindowInventory.currentwindow).selectednum;
                } else if (WindowInventory.windows.get((int)WindowInventory.currentwindow).selectedtype == InventoryRegion.Selected.ITEM) {
                    invmode = InvMode.DRAGGING_ITEM;
                }
            }
        } else if (invmode == InvMode.DRAGGING_INV) {
            WindowInventory.regionCheck();
            invmode = InvMode.REGULAR;
        } else if (invmode == InvMode.DRAGGING) {
            invmode = InvMode.REGULAR;
        } else if (invmode == InvMode.DRAGGING_ITEM) {
            invmode = InvMode.REGULAR;
        }
        if (Mouse.getButton() == 1 && !wasPressed1) {
            wasPressed1 = true;
            if (Mouse.getY() > WindowInventory.windows.get((int)WindowInventory.currentwindow).y + 4 && Mouse.getY() < WindowInventory.windows.get((int)WindowInventory.currentwindow).y + 15) {
                int choosed = (Mouse.getX() - (WindowInventory.windows.get((int)WindowInventory.currentwindow).x + 4)) / 24;
                System.out.println(choosed);
                if (choosed >= 0 && choosed < WindowInventory.windows.get((int)WindowInventory.currentwindow).inventory.size()) {
                    WindowInventory.windows.get((int)WindowInventory.currentwindow).activatedInv = choosed;
                }
            }
        }
    }

    @Override
    public void render() {
        int i = 0;
        while (i < windows.size()) {
            windows.get(i).render();
            ++i;
        }
    }

    @Override
    public boolean check() {
        int i = 0;
        while (i < windows.size()) {
            if (Mouse.getX() >= WindowInventory.windows.get((int)i).x && Mouse.getX() <= WindowInventory.windows.get((int)i).x + width && Mouse.getY() >= WindowInventory.windows.get((int)i).y && Mouse.getY() <= WindowInventory.windows.get((int)i).y + height) {
                currentwindow = i;
                return true;
            }
            ++i;
        }
        return false;
    }

    public static void regionCheck() {
        int i = 0;
        while (i < windows.size()) {
            if (Mouse.getX() >= WindowInventory.windows.get((int)i).x && Mouse.getX() <= WindowInventory.windows.get((int)i).x + width && Mouse.getY() >= WindowInventory.windows.get((int)i).y && Mouse.getY() <= WindowInventory.windows.get((int)i).y + height) {
                currentwindow = i;
                inRegion = true;
            }
            ++i;
        }
        inRegion = false;
    }

    public void clear() {
        InterfaceManager.currentMode = InterfaceManager.mode.GAME;
    }

    public static enum InvMode {
        REGULAR,
        DRAGGING_INV,
        DRAGGING_ITEM,
        DRAGGING;
        

    }

}

