/*
 * Decompiled with CFR 0_115.
 */
package game.entity.items.equipment;

import game.entity.items.Item;
import game.graphics.Sprite;

public class Leather_Boots
extends Item {
    public Leather_Boots() {
        this.type = Item.ItemType.equipment;
        this.rarity = Item.ItemRarity.common;
        this.stackable = false;
        this.sprite = Sprite.leather_boots;
        this.name = "Leather Boots";
    }

    @Override
    public void showInfo() {
    }
}

