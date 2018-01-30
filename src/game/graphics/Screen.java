/*
 * Decompiled with CFR 0_115.
 */
package game.graphics;

import game.Game;
import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.graphics.Sprite;
import game.level.Level;
import game.level.TileHash;
import game.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Screen {
    public static int width;
    public static int height;
    public static int[] pixels;
    public static int xOffset;
    public static int yOffset;
    public static int trancparent;
    
    

    static {
        pixels = ((DataBufferInt)Game.image.getRaster().getDataBuffer()).getData();
        xOffset = 0;
        yOffset = 0;
        trancparent = -65281;
    }

    public Screen(int width, int height) {
        Screen.width = width;
        Screen.height = height;
    }

    public static void renderTextChar(int xp, int yp, Sprite sprite, int color, boolean fixed) {
        if (!fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        int y = 0;
        while (y < sprite.getHeight()) {
            int ya = y + yp;
            int x = 0;
            while (x < sprite.getWidth()) {
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height && sprite.pixels[x + y * sprite.getWidth()] != trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = -2763307;
                }
                ++x;
            }
            ++y;
        }
    }

    public static void renderTile(int xp, int yp, Tile tile) {
    	xp -= Screen.xOffset;
        yp -= Screen.yOffset;
        int y = 0;
        while (y < 32) {
            int ya = y + yp;
            int x = 0;
            while (x < 32) {
                int col;
                int xa = x + xp;
                if (xa >= 0 && xa < Screen.width && ya >= 0 && ya < Screen.height && (col = tile.sprite.pixels[x + y * tile.sprite.SIZE]) != Screen.trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = col;
                }
                ++x;
            }
            ++y;
        }
        
    }
    
    public static void renderSprite(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        int y = 0;
        while (y < sprite.height) {
            int ya = y + yp;
            int x = 0;
            while (x < sprite.width) {
                int col;
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height && (col = sprite.pixels[x + y * sprite.width]) != trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = col;
                }
                ++x;
            }
            ++y;
        }
    }

    public static void renderSpriteFixed(int xp, int yp, Sprite sprite) {
        int y = 0;
        while (y < sprite.height) {
            int ya = y + yp;
            int x = 0;
            while (x < sprite.width) {
                int col;
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height && (col = sprite.pixels[x + y * sprite.width]) != trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = col;
                }
                ++x;
            }
            ++y;
        }
    }

    public static void renderSpriteTrancparent(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        int y = 0;
        while (y < sprite.height) {
            int ya = y + yp;
            int x = 0;
            while (x < sprite.width) {
                int col;
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height && (col = sprite.pixels[x + y * sprite.width]) != trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = Screen.hasAlpha(col) ? Screen.blendColors(col, pixels[xa + ya * width]) : col;
                }
                ++x;
            }
            ++y;
        }
    }

    private static boolean hasAlpha(int rgb2) {
        if (rgb2 >>> 24 < 255) {
            return true;
        }
        return false;
    }

    private static int blendColors(int rgb2, int rgb1) {
        int factor = rgb2 >>> 24;
        int f1 = 256 - factor;
        return ((rgb1 & 16711935) * f1 + (rgb2 & 16711935) * factor & -16711936 | (rgb1 & 65280) * f1 + (rgb2 & 65280) * factor & 16711680) >>> 8;
    }

    public static void renderHealth(int xp, int yp, double maxhp, double hp) {
        xp -= xOffset;
        yp -= yOffset;
        int y = 0;
        while (y < Sprite.healthbar.getHeight()) {
            int ya = y + yp;
            int x = 0;
            while (x < Sprite.healthbar.getWidth()) {
                int col;
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height && (col = Sprite.healthbar.pixels[x + y * Sprite.healthbar.getWidth()]) != trancparent) {
                    Screen.pixels[xa + ya * Screen.width] = col;
                }
                ++x;
            }
            ++y;
        }
        double health = hp / maxhp;
        ++xp;
        ++yp;
        int y2 = 0;
        while (y2 < Sprite.healthbar.getHeight() - 2) {
            int ya = y2 + yp;
            int x = 0;
            while ((double)x < (double)(Sprite.healthbar.getWidth() - 2) * health) {
                int xa = x + xp;
                if (xa >= 0 && xa < width && ya >= 0 && ya < height) {
                    int col;
                    int green = (int)(255.0 * health);
                    int red = 255 - green;
                    Screen.pixels[xa + ya * Screen.width] = col = -16777216 | red << 16 | green << 8;
                }
                ++x;
            }
            ++y2;
        }
    }

    public static void renderMana(int xp, int yp, double maxmp, double mp) {
        int ya;
        int xa;
        xp -= xOffset;
        yp -= yOffset - 4;
        double mana = mp / maxmp;
        int col = 255;
        int i = 0;
        while ((double)i < (double)Sprite.healthbar.getWidth() * mana) {
            ya = yp;
            xa = i + xp;
            if (xa >= 0 && xa < width && ya >= 0 && ya < height) {
                Screen.pixels[xa + ya * Screen.width] = col;
            }
            ++i;
        }
        i = 0;
        while ((double)i < (double)Sprite.healthbar.getWidth() * mana) {
            ya = yp + 1;
            xa = i + xp;
            if (xa >= 0 && xa < width && ya >= 0 && ya < height) {
                Screen.pixels[xa + ya * Screen.width] = -16777216;
            }
            ++i;
        }
    }

    public static void setOffset(int xOffset, int yOffset) {
        Screen.yOffset = yOffset;
        Screen.xOffset = xOffset;
    }

    public static void renderXp(double xp) {
        int y = 0;
        while (y < 4) {
            int x = 0;
            while (x < width) {
                int col = 1073741824;
                if ((double)(x * Game.width / width) < xp) {
                    col = -1063583519;
                }
                Screen.pixels[x + y * Screen.width] = Screen.hasAlpha(col) ? Screen.blendColors(col, pixels[x + y * width]) : col;
                ++x;
            }
            ++y;
        }
    }

    public static void renderWindow(int xx, int yy, int width, int height) {
        int currenty;
        int y = 0;
        while (y < height) {
            currenty = yy + y;
            Screen.pixels[xx + currenty * Screen.width] = -15986669;
            Screen.pixels[xx + 1 + currenty * Screen.width] = -15986669;
            Screen.pixels[xx + 2 + currenty * Screen.width] = -15986669;
            Screen.pixels[xx + 3 + currenty * Screen.width] = -13946833;
            ++y;
        }
        int currentx = xx + width - 1;
        int y2 = 0;
        while (y2 < height) {
            int currenty2 = yy + y2;
            Screen.pixels[currentx + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 1 + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 2 + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 3 + currenty2 * Screen.width] = -13946833;
            ++y2;
        }
        int x = 3;
        while (x < width - 3) {
            currentx = xx + x;
            Screen.pixels[currentx + yy * Screen.width] = -15986669;
            Screen.pixels[currentx + (yy + 1) * Screen.width] = -15986669;
            Screen.pixels[currentx + (yy + 2) * Screen.width] = -15986669;
            Screen.pixels[currentx + (yy + 3) * Screen.width] = -13946833;
            ++x;
        }
        currenty = yy + height - 1;
        int x2 = 3;
        while (x2 < width - 3) {
            currentx = xx + x2;
            Screen.pixels[currentx + currenty * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 1) * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 2) * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 3) * Screen.width] = -13946833;
            ++x2;
        }
        int y3 = 4;
        while (y3 < height - 4) {
            int x3 = 4;
            while (x3 < width - 4) {
                Screen.pixels[xx + x3 + (yy + y3) * Screen.width] = Screen.blendColors(-1157627904, pixels[xx + x3 + (yy + y3) * Screen.width]);
                ++x3;
            }
            ++y3;
        }
    }

    public static void renderMiniMap(int xx, int yy, int width, int height, Sprite sprite) {
        int x;
        int currenty;
        int windowxx = xx - 4;
        int windowyy = yy - 4;
        int windowwidth = width + 8;
        int windowheight = height + 8;
        int y = 0;
        while (y < windowheight) {
            currenty = windowyy + y;
            Screen.pixels[windowxx + currenty * Screen.width] = -15986669;
            Screen.pixels[windowxx + 1 + currenty * Screen.width] = -15986669;
            Screen.pixels[windowxx + 2 + currenty * Screen.width] = -15986669;
            Screen.pixels[windowxx + 3 + currenty * Screen.width] = -13946833;
            ++y;
        }
        int currentx = windowxx + windowwidth - 1;
        int y2 = 0;
        while (y2 < windowheight) {
            int currenty2 = windowyy + y2;
            Screen.pixels[currentx + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 1 + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 2 + currenty2 * Screen.width] = -15986669;
            Screen.pixels[currentx - 3 + currenty2 * Screen.width] = -13946833;
            ++y2;
        }
        int x2 = 3;
        while (x2 < windowwidth - 3) {
            currentx = windowxx + x2;
            Screen.pixels[currentx + windowyy * Screen.width] = -15986669;
            Screen.pixels[currentx + (windowyy + 1) * Screen.width] = -15986669;
            Screen.pixels[currentx + (windowyy + 2) * Screen.width] = -15986669;
            Screen.pixels[currentx + (windowyy + 3) * Screen.width] = -13946833;
            ++x2;
        }
        currenty = windowyy + windowheight - 1;
        int x3 = 3;
        while (x3 < windowwidth - 3) {
            currentx = windowxx + x3;
            Screen.pixels[currentx + currenty * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 1) * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 2) * Screen.width] = -15986669;
            Screen.pixels[currentx + (currenty - 3) * Screen.width] = -13946833;
            ++x3;
        }
        int xoffset = - Game.xCameraCenter % 32 >> 3;
        int yoffset = - Game.yCameraCenter % 32 >> 3;
        int x0 = (Game.xCameraCenter >> 5) - (width >> 3);
        int x1 = (Game.xCameraCenter >> 5) + (width >> 3);
        int y0 = (Game.yCameraCenter >> 5) - (height >> 3);
        int y1 = (Game.yCameraCenter >> 5) + (height >> 3);
        int y3 = y0;
        while (y3 <= y1) {
            int ya = (y3 - y0) * 4 + yy;
            x = x0;
            while (x <= x1) {
                int xvalue;
                int w;
                int yvalue;
                int h;
                int xa = (x - x0) * 4 + xx;
                if (x < 0 || x >= sprite.getWidth()) {
                    w = 0;
                    while (w < 4) {
                        h = 0;
                        while (h < 4) {
                            xvalue = xa + h + xoffset;
                            yvalue = ya + w + yoffset;
                            if (xvalue < xx + width && xvalue >= xx && yvalue < yy + height && yvalue >= yy) {
                                Screen.pixels[xvalue + yvalue * Screen.width] = Screen.blendColors(-872415232, pixels[xvalue + yvalue * Screen.width]);
                            }
                            ++h;
                        }
                        ++w;
                    }
                } else {
                    int colindex = x + y3 * sprite.getWidth();
                    if (colindex < 0 || colindex >= sprite.pixels.length) {
                        w = 0;
                        while (w < 4) {
                            h = 0;
                            while (h < 4) {
                                xvalue = xa + h + xoffset;
                                yvalue = ya + w + yoffset;
                                if (xvalue < xx + width && xvalue >= xx && yvalue < yy + height && yvalue >= yy) {
                                    Screen.pixels[xvalue + yvalue * Screen.width] = Screen.blendColors(-872415232, pixels[xvalue + yvalue * Screen.width]);
                                }
                                ++h;
                            }
                            ++w;
                        }
                    } else {
                        int col = sprite.pixels[colindex];
                        w = 0;
                        while (w < 4) {
                            int yvalue2 = ya + w + yoffset;
                            if (yvalue2 < yy + height && yvalue2 >= yy) {
                                int h2 = 0;
                                while (h2 < 4) {
                                    int xvalue2 = xa + h2 + xoffset;
                                    if (xvalue2 < xx + width && xvalue2 >= xx) {
                                        Screen.pixels[xvalue2 + yvalue2 * Screen.width] = Screen.blendColorsWithSolidColor(col, pixels[xvalue2 + yvalue2 * Screen.width], 204);
                                    }
                                    ++h2;
                                }
                            }
                            ++w;
                        }
                    }
                }
                ++x;
            }
            ++y3;
        }
        int y4 = y0;
        while (y4 <= y1) {
            if (y4 >= 0 && y4 < sprite.getHeight()) {
                x = x0;
                while (x <= x1) {
                    int size;
                    if (x >= 0 && x < sprite.getWidth() && (size = Game.getLevel().tilehash[y4 * sprite.getWidth() + x].mobs.size()) != 0) {
                        int i = 0;
                        while (i < size) {
                            Mob mob = Game.getLevel().tilehash[y4 * sprite.getWidth() + x].mobs.get(i);
                            int col = -1145372672;
                            if (mob instanceof Player) {
                                col = -1140850689;
                            }
                            int xpixel = (mob.getX() >> 3) - (x0 << 2) + xx + xoffset - 2;
                            int ypixel = (mob.getY() >> 3) - (y0 << 2) + yy + yoffset - 2;
                            int j = 0;
                            while (j < 4) {
                                int k = 0;
                                while (k < 4) {
                                    int xvalue = xpixel + k;
                                    int yvalue = ypixel + j;
                                    if (xvalue < xx + width && xvalue >= xx && yvalue < yy + height && yvalue >= yy) {
                                        Screen.pixels[xvalue + yvalue * Screen.width] = Screen.blendColors(col, pixels[xvalue + yvalue * Screen.width]);
                                    }
                                    ++k;
                                }
                                ++j;
                            }
                            ++i;
                        }
                    }
                    ++x;
                }
            }
            ++y4;
        }
    }

    private static int blendColorsWithSolidColor(int rgb1, int rgb2, int trancparency) {
        int alpha = trancparency;
        int newB = (rgb1 & 255) * alpha + (rgb2 & 255) * (255 - alpha) >> 8;
        int newG = (rgb1 >> 8 & 255) * alpha + (rgb2 >> 8 & 255) * (255 - alpha) & 65280;
        int newR = (rgb1 >> 16 & 255) * alpha + (rgb2 >> 16 & 255) * (255 - alpha) & 65280;
        return newR << 8 | newG | newB;
    }

    public static void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
        if (!fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        int x = xp;
        while (x < xp + width) {
            if (x >= 0 && x < Screen.width && yp < Screen.height) {
                if (yp > 0) {
                    Screen.pixels[x + yp * Screen.width] = color;
                }
                if (yp + height < Screen.height && yp + height > 0) {
                    Screen.pixels[x + (yp + height) * Screen.width] = color;
                }
            }
            ++x;
        }
        int y = yp;
        while (y <= yp + height) {
            if (xp < Screen.width && y >= 0 && y < Screen.height) {
                if (xp > 0) {
                    Screen.pixels[xp + y * Screen.width] = color;
                }
                if (xp + width < Screen.width && xp + width > 0) {
                    Screen.pixels[xp + width + y * Screen.width] = color;
                }
            }
            ++y;
        }
    }
}

