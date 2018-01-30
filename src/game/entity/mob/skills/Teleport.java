/*
 * Decompiled with CFR 0_115.
 */
package game.entity.mob.skills;

import game.Game;
import game.entity.HashEntity;
import game.entity.effects.TeleportEffect;
import game.entity.mob.Mob;
import game.entity.mob.skills.Skill;
import game.input.Mouse;
import game.level.Level;
import game.level.tile.Tile;

public class Teleport
extends Skill {
    private int xstart = 0;
    private int ystart = 0;
    private int xend = Game.xCameraCenter + (Mouse.getX() - Game.width / 2);
    private int yend = Game.yCameraCenter + (Mouse.getY() - Game.height / 2);
    private int time = 0;
    private Level level;

    public Teleport(Mob mob) {
        if (mob.level.getTileObject(this.xend + mob.getLargness() >> 5, this.yend + mob.getLargness() >> 5).solid()) {
            this.completed = true;
        }
        if (mob.level.getTileObject(this.xend - mob.getLargness() >> 5, this.yend + mob.getLargness() >> 5).solid()) {
            this.completed = true;
        }
        if (mob.level.getTileObject(this.xend + mob.getLargness() >> 5, this.yend - mob.getLargness() >> 5).solid()) {
            this.completed = true;
        }
        if (mob.level.getTileObject(this.xend - mob.getLargness() >> 5, this.yend - mob.getLargness() >> 5).solid()) {
            this.completed = true;
        }
        if (!this.completed) {
            this.mob = mob;
            mob.busy = true;
            this.xstart = mob.x;
            this.ystart = mob.y;
            this.level = mob.level;
            this.level.add(new TeleportEffect(this.xstart, this.ystart, this.level, true));
            mob.skillcooldown = 90;
            mob.mp -= 2.0;
        }
    }

    @Override
    public void update() {
        ++this.time;
        if (this.time == 20) {
            this.mob.visible = false;
            this.mob.xdouble = this.xend;
            this.mob.ydouble = this.yend;
        }
        if (this.time == 40) {
            this.level.add(new TeleportEffect(this.xend, this.yend, this.level, false));
        }
        if (this.time == 45) {
            this.mob.dir = this.xend - this.xstart >= 0 ? (this.yend - this.ystart >= 0 ? (this.xend - this.xstart >= this.yend - this.ystart ? 1 : 2) : (this.xend - this.xstart >= - this.yend - this.ystart ? 1 : 0)) : (this.yend - this.ystart >= 0 ? (- this.xend - this.xstart >= this.yend - this.ystart ? 3 : 2) : (- this.xend - this.xstart >= - this.yend - this.ystart ? 3 : 0));
            this.mob.visible = true;
            this.mob.busy = false;
            this.completed = true;
        }
    }
}

