/*
 * Decompiled with CFR 0_115.
 */
package game.entity.mob.skills;

import game.entity.mob.Mob;

public abstract class Skill {
    public boolean completed = false;
    public int cooldown = 0;
    protected Mob mob;

    public abstract void update();
}

