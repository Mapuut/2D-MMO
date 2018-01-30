/*
 * Decompiled with CFR 0_115.
 */
package game.entity.items;

import game.entity.items.Item;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.inventory.Inventory;
import game.level.Level;
import game.level.TileHash;
import java.util.ArrayList;
import java.util.List;

public class ItemBag {
    public int x;
    public int y;
    public Item item;
    public Level level;
    private int tilehashindex;
    private int lifetime = 50000;

    public ItemBag(int x, int y, Item item, Level level) {
        this.x = x;
        this.y = y;
        this.item = item;
        this.level = level;
    }

    public void update() {
        --this.lifetime;
        if (this.lifetime <= 0) {
            this.remove();
        }
    }

    public void render() {
        Screen.renderSprite(this.x, this.y, this.item.sprite);
    }

    public void remove() {
        this.level.tilehash[this.tilehashindex].items.remove(this);
        this.level.items.remove(this);
    }

    public void add() {
        this.tilehashindex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        this.level.tilehash[this.tilehashindex].items.add(this);
    }

    public void take(Player player) {
        int i = 0;
        while (i < player.inventory.size()) {
            if (player.inventory.get(i).add(this.item)) {
                this.level.items.remove(this);
                this.level.tilehash[this.tilehashindex].items.remove(this);
                return;
            }
            ++i;
        }
    }
}

