/*
 * Decompiled with CFR 0_115.
 */
package game.graphics.UI.Interface.inventory;

import game.entity.items.Item;

public class Slots {
    public Item item;
    public boolean empty = true;
    public boolean noded = false;
    public int quantity = 0;

    public void add(Item item) {
        this.item = item;
        this.empty = false;
        this.quantity = 1;
    }

    public void reset() {
        this.item = null;
        this.empty = true;
        this.noded = false;
        this.quantity = 0;
    }
}

