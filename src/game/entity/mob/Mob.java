/*
 * Decompiled with CFR 0_115.
 */
package game.entity.mob;

import game.Game;
import game.entity.HashEntity;
import game.entity.mob.Player;
import game.entity.projectile.ArrowProjectile;
import game.entity.projectile.ChuckNorrisProjectile;
import game.entity.projectile.DarthVaderPower;
import game.entity.projectile.FireProjectile;
import game.entity.projectile.HandProjectile;
import game.entity.projectile.RockProjectile;
import game.entity.projectile.particles.Spawner;
import game.graphics.Sprite;
import game.level.Level;
import game.level.tile.Tile;


public abstract class Mob
extends HashEntity {
    public static int debugcount = 0;
    public double xp = 0.0;
    public double xpcost = 0.0;
    public int lvl = 1;
    public double speed = 1.0;
    public int skillcooldown = 0;
    public boolean visible = true;
    public boolean busy = false;
    protected Sprite sprite;
    public double xdouble;
    public double ydouble;
    public double xa = 0.0;
    public double ya = 0.0;
    public int dir = -1;
    protected boolean moving = false;
    protected int largness = 8;
    protected int coolDown = 0;
    public double maxhp = 0.0;
    public double hp = 0.0;
    public double maxmp = 0.0;
    public double mp = 0.0;
    protected boolean agressed = false;
    public Mob enemy;
    private static /* synthetic */ int[] $SWITCH_TABLE$game$entity$mob$Mob$weapon;

    public void move(double x, double y, int largness) {
        if (x != 0.0 && y != 0.0) {
            this.move(x / 1.5, 0.0, largness);
            this.move(0.0, y / 1.5, largness);
            return;
        }
        if (x > 0.0) {
            this.dir = 1;
        }
        if (x < 0.0) {
            this.dir = 3;
        }
        if (y > 0.0) {
            this.dir = 2;
        }
        if (y < 0.0) {
            this.dir = 0;
        }
        if (x > 1.0) {
            while (true) {
                if (x >= 1.0) {
                    this.move(1.0, 0.0, largness);
                    x -= 1.0;
                    continue;
                }
                this.move(x, 0.0, largness);
                x = 0.0;
                return;
            }
        }
        if (y > 1.0) {
            while (y != 0) {
                if (y >= 1.0) {
                    this.move(0.0, 1.0, largness);
                    y -= 1.0;
                    continue;
                }
                this.move(0.0, y, largness);
                y = 0.0;
                return;
            }
        }
        if (!this.collision(this.xdouble + x, this.ydouble + y, largness)) {
            this.xdouble += x;
            this.ydouble += y;
        }
        if (Game.debug && this instanceof Player) {
            if (x != 0.0) {
                Game.playerSpeedX = x;
            }
            if (y != 0.0) {
                Game.playerSpeedY = y;
            }
            if (this.collision(x, y, largness)) {
                Game.callision = true;
            }
        }
    }

    @Override
    public void updatehash() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].mobs.contains(this)) {
            this.level.tilehash[this.tileIndex].mobs.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }

        Tile cur = this.level.tilehash[this.tileIndex].tile;
        if (cur == Tile.dirt_water || cur == Tile.dirt_water2 || cur == Tile.dirt_water3 || cur == Tile.sand_water  || cur == Tile.sand_water2  || cur == Tile.sand_water3) {
            this.level.add(new Spawner(this.x + random.nextInt(20) - 10, this.y + random.nextInt(10), 10, Spawner.type.WATER, this.level));
        }
    }

    @Override
    public void add() {
        this.tileIndex = (this.x >> 5) + (this.y >> 5) * this.level.tilesx;
        if (!this.level.tilehash[this.tileIndex].mobs.contains(this)) {
            this.level.tilehash[this.tileIndex].mobs.add(this);
            if (this.tileIndex != this.tileIndexOld) {
                this.level.tilehash[this.tileIndexOld].mobs.remove(this);
            }
            this.tileIndexOld = this.tileIndex;
        }
    }

    public void checklvlup() {
    }

    public void doDamage(double damage, Mob mob) {
        this.hp -= damage;
        if (this.hp <= 0.0) {
            mob.xp += this.xpcost;
            mob.checklvlup();
            this.remove();
            this.level.add(new Spawner(this.x, this.y, 20, Spawner.type.BLOOD, this.level));
            return;
        }
        this.agressed = true;
        this.enemy = mob;
//        if (damage > 5.0) {
//            this.level.add(new Spawner(this.x, this.y, 50, Spawner.type.BLOOD, this.level));
//        } else {
//            this.level.add(new Spawner(this.x, this.y, (int)(damage * 10.0), Spawner.type.BLOOD, this.level));
//        }
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render();

    protected void setPosition() {
        this.x = (int)this.xdouble;
        this.y = (int)this.ydouble;
    }

    protected void shoot(int x, int y, double dir, weapon currentWeapon) {
        switch (Mob.$SWITCH_TABLE$game$entity$mob$Mob$weapon()[currentWeapon.ordinal()]) {
            case 2: {
                if (this.mp < 0.015) break;
                FireProjectile fire = new FireProjectile(x, y, dir, this);
                FireProjectile fire2 = new FireProjectile(x, y, dir, this);
                this.coolDown = fire.getCooldown();
                this.level.add(fire);
                this.level.add(fire2);
                this.mp -= 0.015;
                break;
            }
            case 3: {
                RockProjectile rock = new RockProjectile(x, y, dir, this);
                this.coolDown = rock.getCooldown();
                this.level.add(rock);
                break;
            }
            case 1: {
                HandProjectile hand = new HandProjectile(x, y, dir, this);
                this.coolDown = hand.getCooldown();
                this.level.add(hand);
                break;
            }
            case 6: {
            	for(int i = 0; i < 2; i++){
                	DarthVaderPower darth = new DarthVaderPower(x, y, dir, this);
                    this.coolDown = darth.getCooldown();
                    this.level.add(darth);
            	}
            	break;

            }
            case 5: {
                ChuckNorrisProjectile norris = new ChuckNorrisProjectile(x, y, dir, this);
                this.coolDown = norris.getCooldown();
                this.level.add(norris);
                break;
            }

            case 4: {
                ArrowProjectile arrow = new ArrowProjectile(x, y, dir, this);
                this.coolDown = arrow.getCooldown();
                this.level.add(arrow);
            }
        }
    }

    protected boolean collision(double x, double y, double largeness) {
        int checkcurrentx = (int)(x - largeness) >> 5;
        int checkcurrenty = (int)(y - largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x + largeness) >> 5;
        checkcurrenty = (int)(y + largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x + largeness) >> 5;
        checkcurrenty = (int)(y - largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        checkcurrentx = (int)(x - largeness) >> 5;
        checkcurrenty = (int)(y + largeness) >> 5;
        if (checkcurrentx < 0 || checkcurrentx >= this.level.tilesx || checkcurrenty < 0 || checkcurrenty >= this.level.tilesy) {
            return true;
        }
        if (this.level.tilehash[checkcurrentx + checkcurrenty * this.level.tilesx].solid) {
            return true;
        }
        return false;
    }

    @Override
    public void remove() {
        this.level.tilehash[this.tileIndex].mobs.remove(this);
        this.level.mobs.remove(this);
        this.removed = true;
        --debugcount;
    }

    @Override
    public void initLevel(Level level) {
        this.level = level;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getHealth() {
        return this.hp;
    }

    public int getLargness() {
        return this.largness;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$game$entity$mob$Mob$weapon() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$game$entity$mob$Mob$weapon;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[weapon.values().length];
        try {
            arrn[weapon.ARROW.ordinal()] = 4;
        }
        catch (NoSuchFieldError v1) {}
        try {
            arrn[weapon.CHUCK_NORRIS.ordinal()] = 5;
        }
        catch (NoSuchFieldError v2) {}
        try {
            arrn[weapon.DARTH_VADER.ordinal()] = 6;
        }
        catch (NoSuchFieldError v2) {}
        try {
            arrn[weapon.FIRE.ordinal()] = 2;
        }
        catch (NoSuchFieldError v3) {}
        try {
            arrn[weapon.HAND.ordinal()] = 1;
        }
        catch (NoSuchFieldError v4) {}
        try {
            arrn[weapon.ROCK.ordinal()] = 3;
        }
        catch (NoSuchFieldError v5) {}
        $SWITCH_TABLE$game$entity$mob$Mob$weapon = arrn;
        return $SWITCH_TABLE$game$entity$mob$Mob$weapon;
    }

    protected static enum weapon {
        HAND,
        FIRE,
        ROCK,
        ARROW,
        CHUCK_NORRIS,
        DARTH_VADER;
    }

}

