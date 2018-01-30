/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.inventory;

import game.entity.items.Item;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.inventory.Slots;

public class Inventory {
    public Slots[] slots = new Slots[6];
    private Sprite slotbackground = new Sprite(32, 32, -1725222865);

    public Inventory() {
        int i = 0;
        while (i < this.slots.length) {
            this.slots[i] = new Slots();
            ++i;
        }
    }

    public void render(int x, int y) {
        int i = 0;
        while (i < this.slots.length) {
            Screen.renderSpriteTrancparent(x + 8 + i % 4 * 36 + Screen.xOffset, y + 19 + i / 4 * 36 + Screen.yOffset, this.slotbackground);
            ++i;
        }
        i = 0;
        while (i < this.slots.length) {
            if (!this.slots[i].empty) {
                Screen.renderSpriteTrancparent(x + 8 + i % 4 * 36 + Screen.xOffset, y + 19 + i / 4 * 36 + Screen.yOffset, this.slots[i].item.sprite);
            }
            ++i;
        }
    }

    public boolean add(Item item) {
        int i = 0;
        while (i < this.slots.length) {
            if (this.slots[i].empty) {
                this.slots[i].add(item);
                return true;
            }
            ++i;
        }
        return false;
    }
}

