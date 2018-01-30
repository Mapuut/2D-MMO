/*
 * Decompiled with CFR 0_115.
 */
package game.entity.items;

import game.graphics.Sprite;

public abstract class Item {
    public ItemType type = ItemType.resource;
    public ItemRarity rarity = ItemRarity.common;
    public boolean stackable = false;
    public Sprite sprite;
    public String name;

    public abstract void showInfo();

    public static enum ItemRarity {
        common,
        rare,
        epic,
        legendary;

    }

    public static enum ItemType {
        resource,
        equipment,
        potion,
        skill,
        spell;

    }

}

