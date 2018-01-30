/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.inventory;

import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.inventory.Inventory;
import game.graphics.UI.Interface.inventory.Slots;
import game.input.Mouse;
import java.util.ArrayList;
import java.util.List;

public class InventoryRegion {
    public List<Inventory> inventory = new ArrayList<Inventory>();
    private Sprite notactive = new Sprite(28, 11, -13946833);
    private Sprite active = new Sprite(27, 11, -15986669);
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public int activatedInv = 0;
    public boolean allisready = true;
    public Selected selectedtype = Selected.INVENTORYICON;
    public int selectednum = 0;

    public InventoryRegion(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
        Screen.renderWindow(this.x, this.y, this.width, this.height);
        int i = 0;
        while (i < this.inventory.size()) {
            Screen.renderSpriteFixed(this.x + 3 + i * 28, this.y + 4, this.notactive);
            ++i;
        }
        Screen.renderSpriteFixed(this.x + 3 + this.activatedInv * 28, this.y + 3, this.active);
        this.inventory.get(this.activatedInv).render(this.x, this.y);
    }

    public void updateSelected() {
        if (Mouse.getY() >= this.y + 4 && Mouse.getY() <= this.y + 15) {
            this.selectednum = (Mouse.getX() - (this.x + 4)) / 24;
            if (this.selectednum >= 0 && this.selectednum < this.inventory.size()) {
                this.selectedtype = Selected.INVENTORYICON;
                return;
            }
            this.selectedtype = Selected.BACKGROUND;
            return;
        }
        if (Mouse.getY() >= this.y + 19) {
            this.selectednum = (Mouse.getY() - (this.y + 19)) / 36 * 4 + (Mouse.getX() - (this.x + 4)) / 36;
            if (Mouse.getY() >= this.y + 19 + this.selectednum / 4 * 36 && Mouse.getY() <= this.y + 19 + this.selectednum / 4 * 36 + 32 && Mouse.getX() >= this.x + 8 + this.selectednum % 4 * 36 && Mouse.getX() <= this.x + 8 + this.selectednum % 4 * 36 + 32) {
                if (this.selectednum >= this.inventory.get((int)this.activatedInv).slots.length) {
                    this.selectedtype = Selected.BACKGROUND;
                    return;
                }
                this.selectedtype = Selected.ITEM;
                return;
            }
            this.selectedtype = Selected.BACKGROUND;
            return;
        }
        this.selectedtype = Selected.BACKGROUND;
    }

    public static enum Selected {
        INVENTORYICON,
        ITEM,
        BACKGROUND;

    }

}

