/*
 * Decompiled with CFR 0_115.
 */
package game.entity.mob;

import game.Game;
import game.entity.mob.Mob;
import game.graphics.AnimatedSprite;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.fonts.Font8;
import game.level.Level;
import game.level.Node;
import game.util.Vector2i;
import java.util.List;
import java.util.Random;

public class GoblinStandart
extends Mob {
    private static AnimatedSprite downanim = new AnimatedSprite(Sprite.goblindown);
    private static AnimatedSprite upanim = new AnimatedSprite(Sprite.goblinup);
    private static AnimatedSprite leftanim = new AnimatedSprite(Sprite.goblinleft);
    private static AnimatedSprite rightanim = new AnimatedSprite(Sprite.goblinright);
    private int animCount = 0;
    private int anim = 0;
    boolean walking = false;
    private int time = 0;
    private int countdown = 10;
    private int ranDir = random.nextInt(8);
    private List<Node> path = null;
    private Vector2i enemyposition = new Vector2i(-1, -1);

    public GoblinStandart(int x, int y, Level level) {
        this.x = x;
        this.xdouble = this.x;
        this.y = y;
        this.ydouble = this.y;
        this.level = level;
        this.dir = 3;
        this.sprite = downanim.getSprite(this.anim);
        this.largness = 5;
        this.hp = 10.0;
        this.maxhp = 10.0;
        this.xpcost = 0.3;
        this.speed = 1.0;
        ++debugcount;
    }

    private void randomMove() {
        if (this.time >= this.countdown) {
            this.ranDir = random.nextInt(9);
            this.countdown = random.nextInt(500);
            this.time = 0;
        }
        if (this.ranDir == 0) {
            this.ya -= 1.0;
        }
        if (this.ranDir == 1) {
            this.ya -= 1.0;
            this.xa += 1.0;
        }
        if (this.ranDir == 2) {
            this.xa += 1.0;
        }
        if (this.ranDir == 3) {
            this.ya += 1.0;
            this.xa += 1.0;
        }
        if (this.ranDir == 4) {
            this.ya += 1.0;
        }
        if (this.ranDir == 5) {
            this.ya += 1.0;
            this.xa -= 1.0;
        }
        if (this.ranDir == 6) {
            this.xa -= 1.0;
        }
        if (this.ranDir == 7) {
            this.ya -= 1.0;
            this.xa -= 1.0;
        }
        if (this.ranDir == 8) {
            this.ya = 0.0;
            this.xa = 0.0;
        }
    }

    @Override
    public void update() {
        this.xa = 0.0;
        this.ya = 0.0;
        if (this.coolDown > 0) {
            --this.coolDown;
        }
        ++this.time;
        if (!this.agressed) {
            this.randomMove();
        }
        if (this.animCount < 75000 && this.walking) {
            ++this.animCount;
        } else {
            this.animCount = 0;
            this.anim = 0;
        }
        if (this.animCount >= 12) {
            this.animCount = 0;
            this.anim = this.anim >= GoblinStandart.downanim.size - 1 ? 1 : ++this.anim;
        }
        if (this.agressed) {
            if (this.enemy.getHealth() <= 0.0 || this.enemy.isRemoved()) {
                this.enemy = null;
                this.agressed = false;
            } else {
                int enemyX = this.enemy.getX();
                int enemyY = this.enemy.getY();
                if (this.enemyposition.getX() != enemyX >> 5 || this.enemyposition.getY() != enemyY >> 5) {
                    Vector2i start = new Vector2i(this.x >> 5, this.y >> 5);
                    Vector2i goal = new Vector2i(enemyX >> 5, enemyY >> 5);
                    this.path = this.level.findPath(start, goal);
                    this.enemyposition = new Vector2i(enemyX >> 5, enemyY >> 5);
                    if (this.path == null) {
                        this.enemyposition = new Vector2i(-1, -1);
                        this.enemy = null;
                        this.agressed = false;
                    }
                }
                if (this.path != null && this.path.size() > 0) {
                    Vector2i vec = this.path.get((int)(this.path.size() - 1)).tile;
                    if (this.x < (vec.getX() << 5) + 16) {
                        this.xa = this.speed;
                    }
                    if (this.x > (vec.getX() << 5) + 16) {
                        this.xa = - this.speed;
                    }
                    if (this.y < (vec.getY() << 5) + 16) {
                        this.ya = this.speed;
                    }
                    if (this.y > (vec.getY() << 5) + 16) {
                        this.ya = - this.speed;
                    }
                    if (this.x == (vec.getX() << 5) + 16 && this.y == (vec.getY() << 5) + 16) {
                        this.path.remove(this.path.size() - 1);
                    }
                }
                if (this.coolDown <= 0) {
                    double xdir = enemyX - this.x;
                    double ydir = enemyY - this.y;
                    double dir = Math.atan2(ydir, xdir);
                    this.shoot(this.x, this.y, dir, Mob.weapon.HAND);
                }
            }
        }
        if (this.xa != 0.0 || this.ya != 0.0) {
            this.move(this.xa, this.ya, this.largness);
            this.walking = true;
        } else {
            this.walking = false;
        }
        this.setPosition();
    }

    @Override
    public void render() {
        if (this.dir == 0) {
            this.sprite = upanim.getSprite(this.anim);
        }
        if (this.dir == 1) {
            this.sprite = rightanim.getSprite(this.anim);
        }
        if (this.dir == 2) {
            this.sprite = downanim.getSprite(this.anim);
        }
        if (this.dir == 3) {
            this.sprite = leftanim.getSprite(this.anim);
        }
        Screen.renderSprite(this.x - 16, this.y - 26, this.sprite);
        Screen.renderHealth(this.x - 14, this.y - 24, this.maxhp, this.hp);
        Font8.render(this.x - Game.xCamera - 20, this.y - Game.yCamera + 12, "[" + this.lvl + "] Goblin", true);
    }
}

