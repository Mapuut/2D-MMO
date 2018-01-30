/*
 * Decompiled with CFR 0_115.
 */
package game.level;

import game.entity.HashEntity;
import game.entity.mob.GoblinSoldier;
import game.entity.mob.GoblinStandart;
import game.entity.objects.Rock;
import game.level.Level;
import game.level.TileHash;
import game.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class SpawnLevel
extends Level {
    public SpawnLevel(String pathGround, String pathObjects) {
        super(pathGround, pathObjects);
    }

    @Override
    protected void loadLevel(String pathGround, String pathObjects) {
        try {
            BufferedImage imageGraund = ImageIO.read(SpawnLevel.class.getResourceAsStream(pathGround));
            BufferedImage imageObjects = ImageIO.read(SpawnLevel.class.getResourceAsStream(pathObjects));
            int w = this.tilesx = imageGraund.getWidth();
            int h = this.tilesy = imageGraund.getHeight();
            this.levelPixelsGraund = new int[w * h];
            this.levelPixelsObjects = new int[w * h];
            this.levelPixelsGraund = imageGraund.getRGB(0, 0, w, h, this.levelPixelsGraund, 0, w);
            this.levelPixelsObjects = imageObjects.getRGB(0, 0, w, h, this.levelPixelsObjects, 0, w);
            this.tilehash = new TileHash[w * h];
            int y = 0;
            while (y < h) {
                int x = 0;
                while (x < w) {
                    this.tilehash[x + y * w] = new TileHash((x << 5) + 16, (y << 5) + 16);
                    this.tilehash[x + y * w].tile = this.getTileGround(x, y);
                    if (this.getTileObject(x, y) == Tile.noneTile) {
                        this.tilehash[x + y * w].tile2 = this.getTileMask(x, y);
                        this.tilehash[x + y * w].layer = 3;
                    } else {
                        this.tilehash[x + y * w].tile2 = this.getTileObject(x, y);
                        this.tilehash[x + y * w].layer = 2;
                        this.tilehash[x + y * w].solid = this.getTileObject(x, y).solid();
                    }
                    ++x;
                }
                ++y;
            }
            this.tilehash[0].setlevel(this);
            imageGraund.flush();
            imageObjects.flush();
            imageGraund = null;
            imageObjects = null;
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed load Level");
        }
        int i = 0;
        while (i < 20) {
            this.add(new GoblinStandart(400 + i * 10, 400 + i * 10, this));
            this.add(new GoblinSoldier(400 + i * 10, 400 + i * 10, this));
            ++i;
        }
        this.add(new Rock(176, 80, Rock.RockType.ROCK, this));
    }
}

