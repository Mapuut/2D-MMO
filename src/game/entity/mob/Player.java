/*
 * Decompiled with CFR 0_115.
 */
package game.entity.mob;

import game.Game;
import game.entity.items.ItemBag;
import game.entity.items.equipment.Leather_Boots;
import game.entity.mob.Mob;
import game.entity.mob.skills.Skill;
import game.entity.mob.skills.Teleport;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.InterfaceManager;
import game.graphics.UI.Interface.inventory.Inventory;
import game.graphics.UI.fonts.Font8;
import game.input.KeyBoard;
import game.input.Mouse;
import game.level.Level;
import game.level.TileHash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player
extends Mob {
    boolean walking = false;
    private static AnimatedSprite downanim = new AnimatedSprite(Sprite.playerdown);
    private static AnimatedSprite upanim = new AnimatedSprite(Sprite.playerup);
    private static AnimatedSprite leftanim = new AnimatedSprite(Sprite.playerleft);
    private static AnimatedSprite rightanim = new AnimatedSprite(Sprite.playerright);
    private static AnimatedSprite downanim_punch = new AnimatedSprite(Sprite.playerdown_punch);
    private static AnimatedSprite upanim_punch = new AnimatedSprite(Sprite.playerup_punch);
    private static AnimatedSprite leftanim_punch = new AnimatedSprite(Sprite.playerleft_punch);
    private static AnimatedSprite rightanim_punch = new AnimatedSprite(Sprite.playerright_punch);
    private int animCount = 0;
    private int anim = 0;
    public String name = "Fauser";
    public ArrayList<Skill> skills = new ArrayList();
    public ArrayList<Inventory> inventory = new ArrayList();
    private List<Mob.weapon> allWeapons = new ArrayList<Mob.weapon>();
    public Mob.weapon currentWeapon = Mob.weapon.HAND;
    private int weaponNumber = 0;
    private int time = 0;
    private boolean punch = false;

    public Player(int x, int y) {
        String userHome = System.getProperty("user.home");
        String profile_path = String.valueOf(userHome) + "/Man&Satan/settings/profile.txt";
        System.out.println(profile_path);
        File profile = new File(profile_path);
        try {
            Scanner in = new Scanner(profile);
            this.lvl = in.nextInt();
            this.maxhp = 10 + this.lvl * (1 + this.lvl);
            this.maxmp = 6 + this.lvl * (1 + this.lvl);
            this.x = in.nextInt();
            this.xdouble = this.x;
            this.y = in.nextInt();
            this.ydouble = this.y;
            this.hp = in.nextInt();
            this.mp = in.nextInt();
            in.nextLine();
            this.name = in.nextLine();
        }
        catch (FileNotFoundException e) {
            System.err.println("Profile not found... path: " + profile_path);
            this.x = x;
            this.xdouble = this.x;
            this.y = y;
            this.ydouble = this.y;
            this.hp = this.maxhp = (double)(10 + this.lvl * (1 + this.lvl));
            this.mp = this.maxmp = (double)(6 + this.lvl * (1 + this.lvl));
            e.printStackTrace();
        }
        this.largness = 8;
        this.sprite = downanim.getSprite(0);
        Mob.weapon[] xy = Mob.weapon.values();
        int i = 0;
        while (i < xy.length) {
            this.allWeapons.add(xy[i]);
            ++i;
        }
        this.xpcost = 1.0;
        this.speed = 0.9;
        i = 0;
        while (i < 3) {
            this.inventory.add(new Inventory());
            ++i;
        }
    }

    private void updateskills() {
        if (this.skillcooldown > 0) {
            --this.skillcooldown;
        }
        if (KeyBoard.space && this.skillcooldown <= 0 && this.mp >= 2.0) {
            this.skills.add(new Teleport(this));
        }
        int i = 0;
        while (i < this.skills.size()) {
            if (this.skills.get((int)i).completed) {
                this.skills.remove(i);
            } else {
                this.skills.get(i).update();
            }
            ++i;
        }
    }

    @Override
    public void checklvlup() {
        if (this.xp >= (double)(this.lvl * 100 + this.lvl * this.lvl * this.lvl * this.lvl - 1)) {
            this.xp -= (double)(this.lvl * 100 + this.lvl * this.lvl * this.lvl * this.lvl - 1);
            ++this.lvl;
            this.maxhp = 10 + this.lvl * (1 + this.lvl);
            this.maxmp = 6 + this.lvl * (1 + this.lvl);
        }
    }

    @Override
    public void remove() {
        this.removed = true;
        this.level.tilehash[this.tileIndex].mobs.remove(this);
        this.xdouble = 496.0;
        this.ydouble = 560.0;
        this.visible = true;
        this.busy = false;
        int i = 0;
        while (i < this.skills.size()) {
            this.skills.remove(i);
            ++i;
        }
        this.level.tilehash[300].mobs.add(this);
        this.tileIndexOld = 300;
    }

    private void updatestats() {
        if (this.hp <= 0.0 && !this.removed) {
            this.hp = this.maxhp / 6.0;
            this.mp = this.maxmp / 2.0;
        }
        if (this.removed) {
            this.removed = false;
        }
        if (this.hp < this.maxhp) {
            this.hp += this.maxhp / 4000.0;
            if (this.hp > this.maxhp) {
                this.hp = this.maxhp;
            }
        }
        if (this.mp < this.maxmp) {
            this.mp += this.maxmp / 4000.0;
            if (this.mp > this.maxmp) {
                this.mp = this.maxmp;
            }
        }
    }

    @Override
    public void update() {
        this.updateskills();
        this.updatestats();
        this.updatemoving();
        this.setPosition();
        if (InterfaceManager.getMode() == InterfaceManager.mode.GAME) {
            this.updateShooting();
        }
    }
    private static boolean wasPressed = false;
    private void updatemoving() {
        this.ya = 0.0;
        this.xa = 0.0;
        if (this.animCount < 75000 && this.walking) {
            ++this.animCount;
        } else {
            this.animCount = 0;
            this.anim = 0;
        }
        if (this.animCount >= 12) {
            this.animCount = 0;
            this.anim = this.anim >= Player.downanim.size - 1 ? 1 : ++this.anim;
        }
        if (KeyBoard.up) {
            this.ya -= this.speed;
        }
        if (KeyBoard.down) {
            this.ya += this.speed;
        }
        if (KeyBoard.left) {
            this.xa -= this.speed;
        }
        if (KeyBoard.right) {
            this.xa += this.speed;
        }
        if (this.xa != 0.0 && !this.busy || this.ya != 0.0 && !this.busy) {
            this.move(this.xa, this.ya, this.largness);
            this.walking = true;
        } else {
            this.walking = false;
        }
        if (KeyBoard.keys[82]){
            this.level.add(new GoblinSoldier(this.x, this.y, this.level));
        }
        
        if (KeyBoard.keys[69]){
            int i = 0;
            while (i < 7) {
                GoblinStandart goblin = new GoblinStandart(this.x + random.nextInt(20) - 10, this.y + random.nextInt(20) - 10, this.level);
                this.tileIndex = (goblin.x >> 5) + (goblin.y >> 5) * this.level.tilesx;
                if (this.tileIndex >= 0 && this.tileIndex < this.level.tilehash.length && !this.level.getTileObject(goblin.x >> 5, goblin.y >> 5).solid()) {
                    this.level.add(goblin);
                }
                ++i;
            }
        }
        if (KeyBoard.keys[48] && !wasPressed){
            this.level.add(new GoblinBoss(this.x, this.y, this.level));
//            wasPressed = true;
        }else if (!KeyBoard.keys[48]){
        	wasPressed = false;
        }
        	

    }

    private void switchWeapon() {
        if (this.weaponNumber + 1 < this.allWeapons.size()) {
            ++this.weaponNumber;
            this.currentWeapon = this.allWeapons.get(this.weaponNumber);
        } else {
            this.weaponNumber = 0;
            this.currentWeapon = this.allWeapons.get(this.weaponNumber);
        }
    }

    private void updateShooting() {
        this.punch = Mouse.getButton() == 1;
        if (this.time > 0) {
            --this.time;
        }
        if (Mouse.getButton() == 3 && this.time <= 0) {
            this.time = 25;
            this.switchWeapon();
        }
        if (this.coolDown > 0) {
            --this.coolDown;
        }
        if (Mouse.getButton() == 1 && this.coolDown <= 0 && !this.busy) {
            double xdir = Mouse.getX() - (this.x - Game.xCamera());
            double ydir = Mouse.getY() - (this.y - Game.yCamera());
            double dir = Math.atan2(ydir, xdir);
            this.shoot(this.x, this.y, dir, this.currentWeapon);
        }
    }

    @Override
    public void render() {
        if (this.dir == 0) {
            this.sprite = this.punch ? upanim_punch.getSprite(this.anim) : upanim.getSprite(this.anim);
        }
        if (this.dir == 1) {
            this.sprite = this.punch ? rightanim_punch.getSprite(this.anim) : rightanim.getSprite(this.anim);
        }
        if (this.dir == 2) {
            this.sprite = this.punch ? downanim_punch.getSprite(this.anim) : downanim.getSprite(this.anim);
        }
        if (this.dir == 3) {
            this.sprite = this.punch ? leftanim_punch.getSprite(this.anim) : leftanim.getSprite(this.anim);
        }
        if (InterfaceManager.getMode() == InterfaceManager.mode.GAME && (Mouse.getButton() == 1 && !this.walking || Mouse.getButton() == 3 && !this.walking)) {
            this.setDirIfMouse();
        }
        if (this.visible) {
            Screen.renderSprite(this.x - 15, this.y - 24, this.sprite);
            Screen.renderHealth(this.x - 14, this.y - 26, this.maxhp, this.hp);
            Screen.renderMana(this.x - 14, this.y - 26, this.maxmp, this.mp);
            Font8.render(this.x - Game.xCamera - 23, this.y - Game.yCamera + 12, "[" + this.lvl + "] " + this.name, true);
        }
    }

    private void setDirIfMouse() {
        int xs = Mouse.getX() - (this.x - Game.xCamera());
        int ys = Mouse.getY() - (this.y - Game.yCamera());
        if (xs <= 0) {
            this.sprite = ys <= 0 ? (xs < ys ? (this.punch ? leftanim_punch.getSprite(this.anim) : leftanim.getSprite(this.anim)) : (this.punch ? upanim_punch.getSprite(this.anim) : upanim.getSprite(this.anim))) : (- xs < ys ? (this.punch ? downanim_punch.getSprite(this.anim) : downanim.getSprite(this.anim)) : (this.punch ? leftanim_punch.getSprite(this.anim) : leftanim.getSprite(this.anim)));
        } else if (xs >= 0) {
            this.sprite = ys >= 0 ? (xs > ys ? (this.punch ? rightanim_punch.getSprite(this.anim) : rightanim.getSprite(this.anim)) : (this.punch ? downanim_punch.getSprite(this.anim) : downanim.getSprite(this.anim))) : (xs > - ys ? (this.punch ? rightanim_punch.getSprite(this.anim) : rightanim.getSprite(this.anim)) : (this.punch ? upanim_punch.getSprite(this.anim) : upanim.getSprite(this.anim)));
        }
    }
}

