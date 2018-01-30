/*
 * Decompiled with CFR 0_115.
 */
package game.graphics;

import game.graphics.SpriteSheet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class Sprite {
    public int SIZE;
    public int[] pixels;
    public int width;
    public int height;
    public static final Sprite grass = new Sprite(32, 0, 0, SpriteSheet.grass);
    public static final Sprite dirt = new Sprite(32, 0, 0, SpriteSheet.dirt);
    public static final Sprite dirt2 = new Sprite(32, 1, 0, SpriteSheet.dirt);
    public static final Sprite dirt3 = new Sprite(32, 2, 0, SpriteSheet.dirt);

    public static final Sprite sand = new Sprite(32, 0, 0, SpriteSheet.sand);
    public static final Sprite sand2 = new Sprite(32, 1, 0, SpriteSheet.sand);
    public static final Sprite sand3 = new Sprite(32, 2, 0, SpriteSheet.sand);

    public static final Sprite sand_water = new Sprite(32, 0, 0, SpriteSheet.water);
    public static final Sprite sand_water2 = new Sprite(32, 1, 0, SpriteSheet.water);
    public static final Sprite sand_water3 = new Sprite(32, 2, 0, SpriteSheet.water);

    public static final Sprite dirt_water = new Sprite(32, 0, 2, SpriteSheet.water);
    public static final Sprite dirt_water2 = new Sprite(32, 1, 2, SpriteSheet.water);
    public static final Sprite dirt_water3 = new Sprite(32, 2, 2, SpriteSheet.water);

    public static final Sprite cobblestone_up = new Sprite(32, 0, 0, SpriteSheet.cobblestone);
    public static final Sprite cobblestone_down = new Sprite(32, 0, 1, SpriteSheet.cobblestone);
    public static final Sprite rock = new Sprite(32, 0, 0, SpriteSheet.projectiles);
    public static final Sprite arrow = new Sprite(32, 1, 0, SpriteSheet.projectiles);
    public static final Sprite fire = new Sprite(32, 2, 0, SpriteSheet.projectiles);
    public static final Sprite voidTile = new Sprite(32, -16777216);
    public static final Sprite playerdown = new Sprite(32, 96, 0, 0, SpriteSheet.player);
    public static final Sprite playerup = new Sprite(32, 96, 1, 0, SpriteSheet.player);
    public static final Sprite playerleft = new Sprite(32, 96, 2, 0, SpriteSheet.player);
    public static final Sprite playerright = new Sprite(32, 96, 3, 0, SpriteSheet.player);
    public static final Sprite playerdown_punch = new Sprite(32, 96, 0, 0, SpriteSheet.player_punch);
    public static final Sprite playerup_punch = new Sprite(32, 96, 1, 0, SpriteSheet.player_punch);
    public static final Sprite playerleft_punch = new Sprite(32, 96, 2, 0, SpriteSheet.player_punch);
    public static final Sprite playerright_punch = new Sprite(32, 96, 3, 0, SpriteSheet.player_punch);
    public static final Sprite goblindown = new Sprite(32, 96, 0, 0, SpriteSheet.goblin);
    public static final Sprite goblinup = new Sprite(32, 96, 1, 0, SpriteSheet.goblin);
    public static final Sprite goblinleft = new Sprite(32, 96, 2, 0, SpriteSheet.goblin);
    public static final Sprite goblinright = new Sprite(32, 96, 3, 0, SpriteSheet.goblin);
    
    
    
    public static final Sprite goblinsoldierdown = new Sprite(32, 96, 0, 0, SpriteSheet.goblinsoldier);
    public static final Sprite goblinsoldierup = new Sprite(32, 96, 1, 0, SpriteSheet.goblinsoldier);
    public static final Sprite goblinsoldierleft = new Sprite(32, 96, 2, 0, SpriteSheet.goblinsoldier);
    public static final Sprite goblinsoldierright = new Sprite(32, 96, 3, 0, SpriteSheet.goblinsoldier);
    
    
    public static final Sprite goblinbossdown = new Sprite(64, 192, 0, 0, SpriteSheet.goblinboss);
    public static final Sprite goblinbossup = new Sprite(64, 192, 1, 0, SpriteSheet.goblinboss);
    public static final Sprite goblinbossleft = new Sprite(64, 192, 2, 0, SpriteSheet.goblinboss);
    public static final Sprite goblinbossright = new Sprite(64, 192, 3, 0, SpriteSheet.goblinboss);
    
    
    public static final Sprite leather_boots = new Sprite("/equipment/leather_bootsx2.png", 32, 32);
    public static final Sprite fireball_junk1 = new Sprite(1, -121855);
    public static final Sprite fireball_junk2 = new Sprite(2, -97517);
    public static final Sprite fireball_junk3 = new Sprite(1, -179);
    public static final Sprite rock_junk1 = new Sprite(1, -6908266);
    public static final Sprite rock_junk2 = new Sprite(2, -5066062);
    public static final Sprite rock_junk3 = new Sprite(1, -10066593);
    public static final Sprite arrow_junk1 = new Sprite(2, -4079167);
    public static final Sprite arrow_junk2 = new Sprite(1, -13094001);
    public static final Sprite arrow_junk3 = new Sprite(2, -9881821);
    public static final Sprite arrow_junk4 = new Sprite(1, -9881821);
    public static final Sprite blood1 = new Sprite(1, -9437184);
    public static final Sprite blood2 = new Sprite(2, -9437184);
    public static final Sprite blood3 = new Sprite(1, -3932160);
    public static final Sprite water1 = new Sprite(1, 0xff46778D);
    public static final Sprite water2 = new Sprite(2, 0xff46778D);
    public static final Sprite water3 = new Sprite(3, 0xff46778D);
    public static final Sprite healthbar = new Sprite("/UI/healthbar.png", 28, 4);
    public static final Sprite smoke1 = new Sprite("/effects/Teleport1.png", 32, 32);
    public static final Sprite smoke2 = new Sprite("/effects/Teleport2.png", 32, 32);
    public static final Sprite smoke3 = new Sprite("/effects/Teleport3.png", 32, 32);
    public static final Sprite blackparticle1 = new Sprite(2, -16777216);
    public static final Sprite blackparticle2 = new Sprite(4, -14474461);
    public static final Sprite blackparticle3 = new Sprite(2, -13421773);
    public static final Sprite blackparticle4 = new Sprite(15, -16777216);
    public static final Sprite blackparticle5 = new Sprite(25, -14474461);
    public static final Sprite blackparticle6 = new Sprite(20, -13421773);
    public static final Sprite minimapupleftborder = new Sprite(61, 0, 0, SpriteSheet.minimap_borders);
    public static final Sprite minimapuprightborder = new Sprite(61, 1, 0, SpriteSheet.minimap_borders);
    public static final Sprite minimapdownleftborder = new Sprite(61, 0, 1, SpriteSheet.minimap_borders);
    public static final Sprite minimapdownrightborder = new Sprite(61, 1, 1, SpriteSheet.minimap_borders);
    public static Sprite castle_floor = new Sprite(32, 7, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefront_down1 = new Sprite(32, 3, 11, SpriteSheet.spawnCastle);
    public static Sprite castlefront_down2 = new Sprite(32, 4, 11, SpriteSheet.spawnCastle);
    public static Sprite castlefront_down3 = new Sprite(32, 7, 11, SpriteSheet.spawnCastle);
    public static Sprite castledoor_up1 = new Sprite(32, 4, 5, SpriteSheet.spawnCastle);
    public static Sprite castledoor_up2 = new Sprite(32, 5, 5, SpriteSheet.spawnCastle);
    public static Sprite castledoor_middle1 = new Sprite(32, 3, 6, SpriteSheet.spawnCastle);
    public static Sprite castledoor_middle2 = new Sprite(32, 4, 6, SpriteSheet.spawnCastle);
    public static Sprite castledoor_middle3 = new Sprite(32, 5, 6, SpriteSheet.spawnCastle);
    public static Sprite castledoor_middle4 = new Sprite(32, 6, 6, SpriteSheet.spawnCastle);
    public static Sprite castledoor_down1 = new Sprite(32, 3, 7, SpriteSheet.spawnCastle);
    public static Sprite castledoor_down2 = new Sprite(32, 4, 7, SpriteSheet.spawnCastle);
    public static Sprite castledoor_down3 = new Sprite(32, 5, 7, SpriteSheet.spawnCastle);
    public static Sprite castledoor_down4 = new Sprite(32, 6, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefront_up1 = new Sprite(32, 3, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefront_up2 = new Sprite(32, 4, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefront_up3 = new Sprite(32, 7, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefront_middle1 = new Sprite(32, 3, 10, SpriteSheet.spawnCastle);
    public static Sprite castlefront_middle2 = new Sprite(32, 4, 10, SpriteSheet.spawnCastle);
    public static Sprite castlefront_middle3 = new Sprite(32, 7, 10, SpriteSheet.spawnCastle);
    public static Sprite castlefront_up = new Sprite(32, 4, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefront_upleft = new Sprite(32, 3, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefront_upright = new Sprite(32, 7, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftside_topup = new Sprite(32, 2, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftside_top = new Sprite(32, 2, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftside_up = new Sprite(32, 2, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftside_middle = new Sprite(32, 2, 10, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftside_down = new Sprite(32, 2, 11, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightside_topup = new Sprite(32, 8, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightside_top = new Sprite(32, 8, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightside_up = new Sprite(32, 8, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightside_middle = new Sprite(32, 8, 10, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightside_down = new Sprite(32, 8, 11, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_topup = new Sprite(32, 1, 5, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_topup2 = new Sprite(32, 2, 5, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_topup3 = new Sprite(32, 2, 6, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_top = new Sprite(32, 1, 6, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_up = new Sprite(32, 1, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_middle = new Sprite(32, 1, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleft_down = new Sprite(32, 1, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_topup = new Sprite(32, 9, 5, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_topup2 = new Sprite(32, 8, 5, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_topup3 = new Sprite(32, 8, 6, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_top = new Sprite(32, 9, 6, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_up = new Sprite(32, 9, 7, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_middle = new Sprite(32, 9, 8, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsideright_down = new Sprite(32, 9, 9, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_topup = new Sprite(32, 0, 0, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_top = new Sprite(32, 0, 1, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_up = new Sprite(32, 0, 2, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_middle = new Sprite(32, 0, 3, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_down = new Sprite(32, 0, 4, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_1 = new Sprite(32, 1, 0, SpriteSheet.spawnCastle);
    public static Sprite castlefrontleftsideleftleft_2 = new Sprite(32, 1, 1, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_topup = new Sprite(32, 10, 0, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_top = new Sprite(32, 10, 1, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_up = new Sprite(32, 10, 2, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_middle = new Sprite(32, 10, 3, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_down = new Sprite(32, 10, 4, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_1 = new Sprite(32, 9, 0, SpriteSheet.spawnCastle);
    public static Sprite castlefrontrightsiderightright_2 = new Sprite(32, 9, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_up1 = new Sprite(32, 4, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_up2 = new Sprite(32, 5, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_up3 = new Sprite(32, 6, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_middle1 = new Sprite(32, 4, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_middle2 = new Sprite(32, 5, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_middle3 = new Sprite(32, 6, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_down1 = new Sprite(32, 4, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_down2 = new Sprite(32, 5, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_down3 = new Sprite(32, 6, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsideup = new Sprite(32, 3, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidemiddle = new Sprite(32, 3, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidedown = new Sprite(32, 3, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidedowndown = new Sprite(32, 3, 4, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsideup = new Sprite(32, 7, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidemiddle = new Sprite(32, 7, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidedown = new Sprite(32, 7, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidedowndown = new Sprite(32, 7, 4, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidetopcontinue = new Sprite(32, 2, 0, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsideupcontinue = new Sprite(32, 2, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidemiddlecontinue = new Sprite(32, 2, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidedowncontinue = new Sprite(32, 2, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_leftsidedowndowncontinue = new Sprite(32, 2, 4, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidetopcontinue = new Sprite(32, 8, 0, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsideupcontinue = new Sprite(32, 8, 1, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidemiddlecontinue = new Sprite(32, 8, 2, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidedowncontinue = new Sprite(32, 8, 3, SpriteSheet.spawnCastle);
    public static Sprite castleback_rightsidedowndowncontinue = new Sprite(32, 8, 4, SpriteSheet.spawnCastle);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        this.width = size;
        this.height = size;
        this.pixels = new int[this.SIZE * this.SIZE];
        this.load(x * size, y * size, sheet);
    }

    public Sprite(String path, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        this.load(path);
    }

    public Sprite(int w, int h, int[] pixels) {
        this.SIZE = w;
        this.width = w;
        this.height = h;
        this.pixels = new int[w * h];
        int i = 0;
        while (i < pixels.length) {
            this.pixels[i] = pixels[i];
            ++i;
        }
    }

    public Sprite(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        this.load(0, 0, sheet);
    }

    public Sprite(int size, int color) {
        this.SIZE = size;
        this.width = size;
        this.height = size;
        this.pixels = new int[this.SIZE * this.SIZE];
        int i = 0;
        while (i < this.pixels.length) {
            this.pixels[i] = color;
            ++i;
        }
    }

    public Sprite(int width, int height, int color) {
        this.pixels = new int[width * height];
        this.width = width;
        this.height = height;
        int i = 0;
        while (i < this.pixels.length) {
            this.pixels[i] = color;
            ++i;
        }
    }

    public Sprite(int w, int h, int x, int y, SpriteSheet sheet) {
        this.SIZE = w;
        this.width = w;
        this.height = h;
        this.pixels = new int[w * h];
        this.load(x * w, y * h, sheet);
    }

    public Sprite(int[] pixels, int width, int height) {
        this.SIZE = width == height ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        int i = 0;
        while (i < pixels.length) {
            this.pixels[i] = pixels[i];
            ++i;
        }
    }

    public void load(int xx, int yy, SpriteSheet sheet) {
        int y = 0;
        while (y < this.height) {
            int x = 0;
            while (x < this.width) {
                this.pixels[x + y * this.width] = sheet.pixels[x + xx + (y + yy) * sheet.width];
                ++x;
            }
            ++y;
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private void load(String path) {
        try {
            System.out.println("Loading from Sprite: " + path + "...");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, this.pixels, 0, w);
            image.flush();
            image = null;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image load.. ERROR !");
        }
    }
}

