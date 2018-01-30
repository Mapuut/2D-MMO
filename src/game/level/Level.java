/*
 * Decompiled with CFR 0_115.
 */
package game.level;

import game.Game;
import game.entity.Entity;
import game.entity.HashEntity;
import game.entity.effects.Effect;
import game.entity.items.ItemBag;
import game.entity.mob.Mob;
import game.entity.objects.Object;
import game.entity.projectile.Projectile;
import game.entity.projectile.particles.Spawner;
import game.graphics.Screen;
import game.level.tile.Tile;
import game.util.Vector2i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Level {
    private List<Entity> entities = new ArrayList<Entity>();
    protected int[] levelPixelsGraund;
    public int[] levelPixelsObjects;
    public int[] levelPixelsMask;
    public TileHash[] tilehash;
    public int tilesx;
    public int tilesy;
    public List<ItemBag> items = new ArrayList<ItemBag>();
    public List<Mob> mobs = new ArrayList<Mob>();
    public List<Object> objects = new ArrayList<Object>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    public List<Spawner> spawners = new ArrayList<Spawner>();
    public List<Effect> effects = new ArrayList<Effect>();
    public static Level spawn = new SpawnLevel("/level/spawnlevelGround.png", "/level/spawnlevelObjects.png");
    private Comparator<Node> nodeSorter;

    public Level(String pathGround, String pathObjects) {
        this.nodeSorter = new Comparator<Node>(){

            @Override
            public int compare(Node node1, Node node2) {
                if (node1.fcost > node2.fcost) {
                    return 1;
                }
                if (node1.fcost < node2.fcost) {
                    return -1;
                }
                return 0;
            }
        };
        this.loadLevel(pathGround, pathObjects);
    }

    protected void loadLevel(String pathGraund, String pathObjects) {
    }

    public void updatehash() {
        int i = 0;
        while (i < this.mobs.size()) {
            this.mobs.get(i).updatehash();
            ++i;
        }
        i = 0;
        while (i < this.projectiles.size()) {
            this.projectiles.get(i).updatehash();
            ++i;
        }
        i = 0;
        while (i < this.spawners.size()) {
            this.spawners.get(i).updatehash();
            ++i;
        }
    }

    public void update() {
        this.updatehash();
        int i = 0;
        while (i < this.items.size()) {
            this.items.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.mobs.size()) {
            this.mobs.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.objects.size()) {
            this.objects.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.projectiles.size()) {
            this.projectiles.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.spawners.size()) {
            this.spawners.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.effects.size()) {
            this.effects.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.entities.size()) {
            this.entities.get(i).update();
            ++i;
        }
        i = 0;
        while (i < this.entities.size()) {
            if (this.entities.get(i).isRemoved()) {
                this.entities.remove(i);
            }
            ++i;
        }
        if (Game.debug) {
            if (TileHash.time >= 120) {
                TileHash.time = 0;
                i = 0;
                while (i < this.tilehash.length) {
                    this.tilehash[i].update();
                    ++i;
                }
            } else {
                ++TileHash.time;
            }
        }
    }

    public boolean particleCollision(int x, int y) {
        if (y >> 5 < 0 || y >> 5 >= this.tilesy || x >> 5 < 0 || x >> 5 >= this.tilesx) {
            return true;
        }
        if (this.tilehash[(x >> 5) + (y >> 5) * this.tilesx].solid) {
            return true;
        }
        return false;
    }

    public void render(int xScroll, int yScroll) {
        int x;
        Screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 5;
        int x1 = xScroll + Screen.width + 32 >> 5;
        int y0 = yScroll >> 5;
        int y1 = yScroll + Screen.height + 32 >> 5;
        int y = y0 - 1;
        
        
        while (y <= y1 + 1) {
            x = x0 - 1;
            while (x <= x1 + 1) {
                if (x >= 0 && y >= 0 && x < this.tilesx && y < this.tilesy) {
                    this.tilehash[y * this.tilesx + x].tile.render(x, y);
                }
                ++x;
            }
            ++y;
        }

        
        
        y = y0 - 1;
        while (y <= y1 + 1) {
            x = x0 - 1;
            while (x <= x1 + 1) {
                if (x < 0 || y < 0 || x >= this.tilesx || y >= this.tilesy) {
                    Tile.voidTile.render(x, y);
                } else if (this.tilehash[y * this.tilesx + x].layer == 2) {
                    this.tilehash[y * this.tilesx + x].tile2.render(x, y);
                }
                ++x;
            }
            ++y;
        }
        
        
        
        
        
        y = y0 - 1;
        while (y <= y1 + 1) {
            x = x0 - 1;
            while (x <= x1 + 1) {
                if (x >= 0 && y >= 0 && x < this.tilesx && y < this.tilesy) {
                    this.tilehash[y * this.tilesx + x].render();
                }
                ++x;
            }
            ++y;
        }
        y = y0 - 1;
        while (y <= y1 + 1) {
            x = x0 - 1;
            while (x <= x1 + 1) {
                if (x >= 0 && y >= 0 && x < this.tilesx && y < this.tilesy && this.tilehash[y * this.tilesx + x].layer == 3) {
                    this.tilehash[y * this.tilesx + x].tile2.render(x, y);
                }
                ++x;
            }
            ++y;
        }
        y = y0 - 1;
        while (y <= y1 + 1) {
            x = x0 - 1;
            while (x <= x1 + 1) {
                if (x >= 0 && y >= 0 && x < this.tilesx && y < this.tilesy) {
                    this.tilehash[y * this.tilesx + x].rendertop();
                }
                ++x;
            }
            ++y;
        }
        int i = 0;
        while (i < this.entities.size()) {
            this.entities.get(i).render();
            ++i;
        }
        
       
        
    }

    public List<Node> findPath(Vector2i start, Vector2i goal) {
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closeList = new ArrayList<Node>();
        Node current = new Node(start, null, 0.0, this.getDistance(start, goal));
        openList.add(current);
        while (openList.size() > 0) {
            if (openList.size() > 60 || closeList.size() > 60) {
                return null;
            }
            Collections.sort(openList, this.nodeSorter);
            current = (Node)openList.get(0);
            if (current.tile.equals(goal)) {
                ArrayList<Node> path = new ArrayList<Node>();
                while (current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                return path;
            }
            openList.remove(current);
            closeList.add(current);
            int x = current.tile.getX();
            int y = current.tile.getY();
            int i = 0;
            while (i < 9) {
                Tile at;
                int yi;
                int xi;
                if (i != 4 && !(at = this.getTileObject(x + (xi = i % 3 - 1), y + (yi = i / 3 - 1))).solid()) {
                    Vector2i a = new Vector2i(x + xi, y + yi);
                    double gcost = current.gcost + this.getDistance(current.tile, a);
                    double hcost = this.getDistance(a, goal);
                    Node node = new Node(a, current, gcost, hcost);
                    if (!(this.vecinList(closeList, a) && gcost >= node.gcost || this.vecinList(openList, a) && gcost >= node.gcost)) {
                        openList.add(node);
                    }
                }
                ++i;
            }
        }
        return null;
    }

    private boolean vecinList(List<Node> list, Vector2i vector) {
        for (Node n : list) {
            if (!n.tile.equals(vector)) continue;
            return true;
        }
        return false;
    }

    private double getDistance(Vector2i tile, Vector2i goal) {
        double dx = tile.getX() - goal.getX();
        double dy = tile.getY() - goal.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void add(ItemBag item) {
        this.items.add(item);
        item.add();
    }

    public void add(HashEntity e) {
        if (e instanceof Projectile) {
            this.projectiles.add((Projectile)e);
            e.initLevel(this);
            e.add();
        } else if (e instanceof Mob) {
            this.mobs.add((Mob)e);
            e.add();
        } else if (e instanceof Spawner) {
            this.spawners.add((Spawner)e);
            e.initLevel(this);
            e.add();
        } else if (e instanceof Effect) {
            this.effects.add((Effect)e);
            e.add();
        } else if (e instanceof Object) {
            this.objects.add((Object)e);
            e.add();
        } else {
            this.entities.add(e);
        }
    }

    public void add(Entity e) {
        this.entities.add(e);
    }

    public Tile getTileGround(int x, int y) {
        if (x < 0 || y < 0 || x >= this.tilesx || y >= this.tilesy) {
            return Tile.voidTile;
        }
        if (this.levelPixelsGraund[x + y * this.tilesx] == -65281) {
            return Tile.noneTile;
        }
        if (this.levelPixelsGraund[x + y * this.tilesx] == -12767210) {
            return Tile.dirt3;
        }
        if (this.levelPixelsGraund[x + y * this.tilesx] == -16426492) {
            return Tile.grass;
        }
        if (this.levelPixelsGraund[x + y * this.tilesx] == -8561602) {
            return Tile.castle_floor;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xffE6D893) {
            return Tile.sand3;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff8BB1AB) {
            return Tile.sand_water;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff7CAAAD) {
            return Tile.sand_water2;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff6C9496) {
            return Tile.sand_water3;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff4B6E7B) {
            return Tile.dirt_water;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff497385) {
            return Tile.dirt_water2;
        }

        if (this.levelPixelsGraund[x + y * this.tilesx] == 0xff46778D) {
            return Tile.dirt_water3;
        }

        return Tile.voidTile;
    }

    public Tile getTileObject(int x, int y) {
        if (x < 0 || y < 0 || x >= this.tilesx || y >= this.tilesy) {
            return Tile.noneTileSolid;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -65281) {
            return Tile.noneTile;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8355712) {
            return Tile.cobblestone_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619146) {
            return Tile.castlefront_down1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619145) {
            return Tile.castlefront_down2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619144) {
            return Tile.castlefront_down3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895456) {
            return Tile.castledoor_down1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895455) {
            return Tile.castledoor_down2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895454) {
            return Tile.castledoor_down3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895453) {
            return Tile.castledoor_down4;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619132) {
            return Tile.castlefrontleftside_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619131) {
            return Tile.castlefrontleftside_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619126) {
            return Tile.castlefrontleftside_topup_solid;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619130) {
            return Tile.castlefrontleftside_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619133) {
            return Tile.castlefrontleftside_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619127) {
            return Tile.castlefrontrightside_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619120) {
            return Tile.castlefrontrightside_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619125) {
            return Tile.castlefrontrightside_topup_solid;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619119) {
            return Tile.castlefrontrightside_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619128) {
            return Tile.castlefrontrightside_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619118) {
            return Tile.castlefrontleftsideleft_topup_solid;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619115) {
            return Tile.castlefrontleftsideleft_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619114) {
            return Tile.castlefrontleftsideleft_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619112) {
            return Tile.castlefrontleftsideleft_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619113) {
            return Tile.castlefrontleftsideleft_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619111) {
            return Tile.castlefrontrightsideright_topup_solid;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619102) {
            return Tile.castlefrontrightsideright_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619101) {
            return Tile.castlefrontrightsideright_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619099) {
            return Tile.castlefrontrightsideright_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619100) {
            return Tile.castlefrontrightsideright_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619088) {
            return Tile.castlefrontleftsideleftleft_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619095) {
            return Tile.castlefrontleftsideleftleft_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619081) {
            return Tile.castlefrontrightsiderightright_down;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619082) {
            return Tile.castlefrontrightsiderightright_middle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619066) {
            return Tile.castleback_down1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619065) {
            return Tile.castleback_down2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619064) {
            return Tile.castleback_down3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619055) {
            return Tile.castleback_leftsidedown;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619054) {
            return Tile.castleback_leftsidedowndown;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619051) {
            return Tile.castleback_rightsidedown;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619050) {
            return Tile.castleback_rightsidedowndown;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618944) {
            return Tile.castleback_leftsidedowncontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618943) {
            return Tile.castleback_leftsidedowndowncontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618939) {
            return Tile.castleback_rightsidedowncontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618938) {
            return Tile.castleback_rightsidedowndowncontinue;
        }
        return Tile.noneTile;
    }

    public Tile getTileMask(int x, int y) {
        if (x < 0 || y < 0 || x >= this.tilesx || y >= this.tilesy) {
            return Tile.noneTile;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -65281) {
            return Tile.noneTile;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8355711) {
            return Tile.cobblestone_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895488) {
            return Tile.castledoor_up1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895487) {
            return Tile.castledoor_up2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895472) {
            return Tile.castledoor_middle1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895471) {
            return Tile.castledoor_middle2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895470) {
            return Tile.castledoor_middle3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -8895469) {
            return Tile.castledoor_middle4;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619152) {
            return Tile.castlefront_up1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619151) {
            return Tile.castlefront_up2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619150) {
            return Tile.castlefront_up3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619149) {
            return Tile.castlefront_middle1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619148) {
            return Tile.castlefront_middle2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619147) {
            return Tile.castlefront_middle3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619143) {
            return Tile.castlefront_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619136) {
            return Tile.castlefront_upleft;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619135) {
            return Tile.castlefront_upright;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619134) {
            return Tile.castlefrontleftside_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619129) {
            return Tile.castlefrontrightside_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619110) {
            return Tile.castlefrontleftsideleft_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619117) {
            return Tile.castlefrontleftsideleft_topup2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619116) {
            return Tile.castlefrontleftsideleft_topup3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619109) {
            return Tile.castlefrontrightsideright_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619104) {
            return Tile.castlefrontrightsideright_topup2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619103) {
            return Tile.castlefrontrightsideright_topup3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619098) {
            return Tile.castlefrontleftsideleftleft_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619097) {
            return Tile.castlefrontleftsideleftleft_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619096) {
            return Tile.castlefrontleftsideleftleft_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619087) {
            return Tile.castlefrontleftsideleftleft_1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619086) {
            return Tile.castlefrontleftsideleftleft_2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619085) {
            return Tile.castlefrontrightsiderightright_topup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619084) {
            return Tile.castlefrontrightsiderightright_top;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619083) {
            return Tile.castlefrontrightsiderightright_up;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619080) {
            return Tile.castlefrontrightsiderightright_1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619079) {
            return Tile.castlefrontrightsiderightright_2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619072) {
            return Tile.castleback_up1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619071) {
            return Tile.castleback_up2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619070) {
            return Tile.castleback_up3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619069) {
            return Tile.castleback_middle1;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619068) {
            return Tile.castleback_middle2;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619067) {
            return Tile.castleback_middle3;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619063) {
            return Tile.castleback_leftsideup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619056) {
            return Tile.castleback_leftsidemiddle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619053) {
            return Tile.castleback_rightsideup;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619052) {
            return Tile.castleback_rightsidemiddle;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619049) {
            return Tile.castleback_leftsidetopcontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619048) {
            return Tile.castleback_leftsideupcontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13619047) {
            return Tile.castleback_leftsidemiddlecontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618942) {
            return Tile.castleback_rightsidetopcontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618941) {
            return Tile.castleback_rightsideupcontinue;
        }
        if (this.levelPixelsObjects[x + y * this.tilesx] == -13618940) {
            return Tile.castleback_rightsidemiddlecontinue;
        }
        return Tile.noneTile;
    }

    public int getEntitySize() {
        return this.entities.size();
    }

}

