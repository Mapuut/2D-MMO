/*
 * Decompiled with CFR 0_115.
 */
package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private String path;
    public int width;
    public int height;
    public int SIZE;
    public int[] pixels;
    public static final SpriteSheet grass = new SpriteSheet("/textures/grass.png");
    public static final SpriteSheet dirt = new SpriteSheet("/textures/dirt.png");
    public static final SpriteSheet sand = new SpriteSheet("/textures/sand.png");
    public static final SpriteSheet water = new SpriteSheet("/textures/water.png");
    public static final SpriteSheet cobblestone = new SpriteSheet("/textures/cobblestone.png");
    public static final SpriteSheet projectiles = new SpriteSheet("/projectiles/exampleProjectiles.png");
    public static final SpriteSheet player = new SpriteSheet("/mobs/player/player.png");
    public static final SpriteSheet player_punch = new SpriteSheet("/mobs/player/player_punch.png");
    public static final SpriteSheet goblin = new SpriteSheet("/mobs/goblin.png");
    
    public static final SpriteSheet goblinsoldier = new SpriteSheet("/mobs/goblinsoldier.png");
    
    public static final SpriteSheet goblinboss = new SpriteSheet("/mobs/goblinboss.png");
    
    public static final SpriteSheet leather_boots = new SpriteSheet("/equipment/leather_boots.png");
    public static final SpriteSheet spawnCastle = new SpriteSheet("/textures/buildings/SpawnCastle.png");
    public static final SpriteSheet minimap_borders = new SpriteSheet("/UI/minimap/minimap.png");

    public SpriteSheet(String path) {
        this.path = path;
        this.load();
    }

    public SpriteSheet(String path, int width, int height, int size) {
        this.path = path;
        this.SIZE = size;
        this.load();
    }

    private void load() {
        try {
            System.out.println("Loading: " + this.path + "...");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResourceAsStream(this.path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.pixels = new int[this.width * this.height];
            image.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
            image.flush();
            image = null;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image load.. ERROR !");
        }
    }
}

