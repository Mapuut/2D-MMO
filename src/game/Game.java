/*
 * Decompiled with CFR 0_115.
 */
package game;

import game.entity.mob.Mob;
import game.entity.mob.Player;
import game.entity.projectile.Projectile;
import game.entity.projectile.particles.Spawner;
import game.graphics.Screen;
import game.graphics.UI.Interface.InterfaceManager;
import game.graphics.UI.Interface.clickmenu.ClickMenu;
import game.graphics.UI.fonts.Font8;
import game.input.KeyBoard;
import game.input.Mouse;
import game.level.Level;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.ImageCapabilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
//import sun.java2d.pipe.hw.ExtendedBufferCapabilities;

public class Game extends Canvas implements Runnable {
    public static boolean debug = false;
    private static double frameLimit = 120.0;
    private static double frameLimiter = 1.0E9 / frameLimit;
    private int debugframes = 0;
    private int debugupdates = 0;
    public static boolean callision = false;
    public static double playerSpeedX = 0.0;
    public static double playerSpeedY = 0.0;
    private static final BufferCapabilities bc = new BufferCapabilities(new ImageCapabilities(true), new ImageCapabilities(true), null);
    //private static BufferCapabilities bc = new ExtendedBufferCapabilities(new ImageCapabilities(true), new ImageCapabilities(true), BufferCapabilities.FlipContents.COPIED, ExtendedBufferCapabilities.VSyncType.VSYNC_ON);
    private Thread thread;
    private JFrame frame;
    private static BufferStrategy bs;
    public static Graphics g;
    public static Game game;
    public static Player player;
    private static KeyBoard keyboard;
    private static Level level;
    private Mouse mouse;
    private static boolean running;
    private static String titel;
    public static double scale;
    public static boolean increase;
    public static boolean decrease;
    public static int width;
    public static int height;
    public static ScreenState screenstate;
    public static int xCamera;
    public static int yCamera;
    private static double xCameraSpeed;
    private static double yCameraSpeed;
    public static int xCameraCenter;
    public static int yCameraCenter;
    private static int counter;
    public static BufferedImage image;
    private static /* synthetic */ int[] $SWITCH_TABLE$game$Game$ScreenState;

    static {
        running = false;
        titel = "Man & Satan";
        scale = 1.0;
        increase = false;
        decrease = false;
        width = 1200;
        height = 700;
        screenstate = ScreenState.LOADING;
        xCamera = 0;
        yCamera = 0;
        xCameraSpeed = 0.0;
        yCameraSpeed = 0.0;
        xCameraCenter = 0;
        yCameraCenter = 0;
        counter = 0;
        image = new BufferedImage(width, height , 1);
    }

    public static void main(String[] args) {
        game = new Game();
        Game.game.frame.setResizable(false);
        Game.game.frame.setTitle(titel);
        Game.game.frame.add(game);
        Game.game.frame.pack();
        Game.game.frame.setDefaultCloseOperation(0);
        Game.game.frame.setLocationRelativeTo(null);
        Game.game.frame.setAutoRequestFocus(true);
        Game.game.frame.getContentPane().setBackground(Color.YELLOW);
        Game.game.frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                Game.screenstate = ScreenState.SAVING;
                String userHome = System.getProperty("user.home");
                String gameFolder = "/Man&Satan";
                String saveFolder = "/settings";
                try {
                    File theSaveDir;
                    File theDir = new File(String.valueOf(userHome) + gameFolder);
                    if (!theDir.exists()) {
                        System.out.println("creating directory: " + userHome + gameFolder);
                        boolean result = false;
                        try {
                            theDir.mkdir();
                            result = true;
                        }
                        catch (SecurityException var7_10) {
                            // empty catch block
                        }
                        if (result) {
                            System.out.println("DIR created");
                        }
                    }
                    if (!(theSaveDir = new File(String.valueOf(userHome) + gameFolder + saveFolder)).exists()) {
                        System.out.println("creating directory: " + userHome + gameFolder + saveFolder);
                        boolean result = false;
                        try {
                            theSaveDir.mkdir();
                            result = true;
                        }
                        catch (SecurityException var8_13) {
                            // empty catch block
                        }
                        if (result) {
                            System.out.println("DIR created");
                        }
                    }
                    PrintWriter writer = new PrintWriter(String.valueOf(userHome) + gameFolder + saveFolder + "/profile.txt", "UTF-8");
                    System.out.println("Saving files...");
                    writer.println(Game.player.lvl);
                    writer.println(Game.player.x);
                    writer.println(Game.player.y);
                    writer.println((int)Game.player.hp);
                    writer.println((int)Game.player.mp);
                    writer.println(Game.player.name);
                    writer.close();
                    System.out.println("Saved!");
                }
                catch (FileNotFoundException e) {
                    System.out.println("Failed to Save");
                    e.printStackTrace();
                }
                catch (UnsupportedEncodingException e) {
                    System.out.println("Failed to Save");
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        BufferedImage[] myImg = new BufferedImage[6];
        try {
            myImg[0] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon16.png"));
            myImg[1] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon24.png"));
            myImg[2] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon32.png"));
            myImg[3] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon48.png"));
            myImg[4] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon64.png"));
            myImg[5] = ImageIO.read(Game.class.getResourceAsStream("/Icon/icon128.png"));
            boolean fail = false;
            int i = 0;
            while (i < myImg.length) {
                if (myImg[i] == null) {
                    fail = true;
                }
                ++i;
            }
            if (!fail) {
                ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
                int i2 = 0;
                while (i2 < myImg.length) {
                    list.add(myImg[i2]);
                    ++i2;
                }
                Game.game.frame.setIconImages(list);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        game.start();
        System.out.println("kusi");
    }

    public Game() {
        Dimension size = new Dimension(width, height);
        this.setPreferredSize(size);
        this.frame = new JFrame();
        this.frame.setIgnoreRepaint(true);
        this.setIgnoreRepaint(true);
        Screen.width = width;
        Screen.height = height;
        keyboard = new KeyBoard();
        this.mouse = new Mouse();
        level = Level.spawn;
        this.addKeyListener(keyboard);
        player = new Player(30, 30);
        player.initLevel(level);
        level.add(player);
        ClickMenu.level = level;
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        xCamera = Game.player.x - width / 2;
        yCamera = Game.player.y - height / 2;
        xCameraCenter = xCamera + Screen.width / 2;
        yCameraCenter = xCamera + Screen.height / 2;
    }

    @Override
	public void run() {
    	System.out.println("sitt");
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 120.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates += 1;
				delta=delta -1;
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer+= 1000;
				frame.setTitle("Story" + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
	}

    public static void update() {
        KeyBoard.update();
        InterfaceManager.update();
        level.update();
        Game.offsetUpdate();
    }

    public static void offsetUpdate() {
        xCameraSpeed = (Game.player.x - (xCamera + width / 2)) / (width / 35);
        yCameraSpeed = (Game.player.y - (yCamera + height / 2)) / (height / 35);
        xCamera = (int)((double)xCamera + xCameraSpeed);
        yCamera = (int)((double)yCamera + yCameraSpeed);
        xCameraCenter = xCamera + width / 2;
        yCameraCenter = yCamera + height / 2;
    }

    public void render() {
        if (!Game.game.frame.isVisible() && counter >= 1) {
            Game.game.frame.setVisible(true);
        }
        switch (Game.$SWITCH_TABLE$game$Game$ScreenState()[screenstate.ordinal()]) {
            case 1: {
                bs = this.getBufferStrategy();
                if (bs == null) {
                    try {
                        this.createBufferStrategy(1, bc);
                    }
                    catch (AWTException e) {
                        System.err.println("Warning: cap is not supported: " + bc);
                        e.printStackTrace();
                        this.createBufferStrategy(1);
                    }
                    bs = this.getBufferStrategy();
                }
                level.render(xCamera, yCamera);
                InterfaceManager.render();
                g = bs.getDrawGraphics();
                g.drawImage(image, 0, 0, width, height, null);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Verdana", 0, 10));
                if (debug) {
                    this.debug();
                }
                g.dispose();
                bs.show();
                break;
            }
            case 2: {
                if (++counter >= 15) {
                    screenstate = ScreenState.GAME;
                    counter = 0;
                }
                if ((Game.bs = this.getBufferStrategy()) == null) {
                    try {
                        this.createBufferStrategy(2, bc);
                    }
                    catch (AWTException e) {
                        System.err.println("Warning: cap is not supported: " + bc);
                        e.printStackTrace();
                        this.createBufferStrategy(2);
                    }
                    bs = this.getBufferStrategy();
                }
                g = bs.getDrawGraphics();
                int i = 0;
                while (i < Screen.pixels.length) {
                    Screen.pixels[i] = 0;
                    ++i;
                }
                g.setFont(new Font("Verdana", 0, 10));
                if (debug) {
                    this.debug();
                }
                Font8.render(width / 2 - 15, height / 2 - 3, "Loading...", true);
                g.drawImage(image, 0, 0, width, height, null);
                g.dispose();
                bs.show();
                break;
            }
            case 4: {
                break;
            }
            case 3: {
                bs = this.getBufferStrategy();
                if (bs == null) {
                    try {
                        this.createBufferStrategy(2, bc);
                    }
                    catch (AWTException e) {
                        System.err.println("Warning: cap is not supported: " + bc);
                        e.printStackTrace();
                        this.createBufferStrategy(2);
                    }
                    bs = this.getBufferStrategy();
                }
                g = bs.getDrawGraphics();
                int i = 0;
                while (i < Screen.pixels.length) {
                    Screen.pixels[i] = 0;
                    ++i;
                }
                g.setFont(new Font("Verdana", 0, 10));
                if (debug) {
                    this.debug();
                }
                Font8.render(width / 2 - 15, height / 2 - 3, "Saving...", true);
                g.drawImage(image, 0, 0, width, height, null);
                g.dispose();
                bs.show();
                break;
            }
        }
    }

    public void debug() {
        g.drawOval(Mouse.getX() - 25, Mouse.getY() - 25, 50, 50);
        g.drawString("PlayerX: " + Game.player.xdouble + " | PlayerY: " + Game.player.ydouble, 10, 20);
        g.drawString("PlayerSpeedX: " + playerSpeedX + " | PlayerSpeedY: " + playerSpeedY, 10, 30);
        playerSpeedX = 0.0;
        playerSpeedY = 0.0;
        g.drawString("CallisionDetection: " + callision, 10, 40);
        callision = false;
        g.drawString("MouseX: " + Mouse.getX() + " | MouseY: " + Mouse.getY(), 10, 60);
        g.drawString("MouseButtonPressed: " + Mouse.getButton(), 10, 70);
        g.drawString("TileX: " + (Game.player.x >> 5) + " | TileY: " + (Game.player.y >> 5) + " | TileIndex: " + ((Game.player.x >> 5) + (Game.player.y >> 5) * Game.level.tilesx), 10, 90);
        g.drawString("CurrentTileGround: " + level.getTileGround(Game.player.x >> 5, Game.player.y >> 5), 10, 100);
        g.drawString("CurrentTileObject: " + level.getTileObject(Game.player.x >> 5, Game.player.y >> 5), 10, 110);
        g.drawString("CurrentTileMask: " + level.getTileMask(Game.player.x >> 5, Game.player.y >> 5), 10, 120);
        g.drawString("MouseOnTileGround: " + level.getTileGround(xCamera + Mouse.getX() >> 5, yCamera + Mouse.getY() >> 5), 10, 130);
        g.drawString("MouseOnTileObject: " + level.getTileObject(xCamera + Mouse.getX() >> 5, yCamera + Mouse.getY() >> 5), 10, 140);
        g.drawString("MouseOnTileMask: " + level.getTileMask(xCamera + Mouse.getX() >> 5, yCamera + Mouse.getY() >> 5), 10, 150);
        g.drawString("LvlEntities: " + level.getEntitySize(), 10, 170);
        g.drawString("Effects: ", 10, 180);
        g.drawString("Projectiles: " + Projectile.debugcount, 10, 190);
        g.drawString("Mobs&NPC: " + Mob.debugcount, 110, 170);
        g.drawString("Objects: ", 110, 180);
        g.drawString("Spawner: " + Spawner.debugcount, 110, 190);
        g.drawString("Players: ", 210, 170);
        g.drawString("Mode: " + (Object)((Object)InterfaceManager.getMode()), 10, 210);
        g.drawString("Click Menu Mode: " + (Object)((Object)ClickMenu.currentlyActivated), 10, 220);
        g.drawString("FPS: " + this.debugframes + " (limit: " + frameLimit + ")" + "  | UPS: " + this.debugupdates, width - 210, 20);
        g.drawString("Current Weapon: " + (Object)((Object)Game.player.currentWeapon), width - 210, 40);
        g.drawString("Activated Skills: " + Game.player.skills.size(), width - 210, 50);
        double hp = (int)(Game.player.hp * 10.0);
        double mp = (int)(Game.player.mp * 10.0);
        g.drawString("HP: " + hp / 10.0 + " | Max HP: " + Game.player.maxhp, width - 210, 70);
        g.drawString("MP: " + mp / 10.0 + " | Max MP: " + Game.player.maxmp, width - 210, 80);
        g.drawString("Removed: " + player.isRemoved(), width - 210, 90);
        g.drawString("LvL: " + Game.player.lvl, width - 210, 110);
        double xp = (int)(Game.player.xp * 10.0);
        g.drawString("XP: " + xp / 10.0 + " | XP needed: " + (Game.player.lvl * 100 + Game.player.lvl * Game.player.lvl * Game.player.lvl * Game.player.lvl - 1), width - 210, 120);
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        this.thread = new Thread((Runnable)this, "Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int xCamera() {
        return xCamera;
    }

    public static int yCamera() {
        return yCamera;
    }

    public static Level getLevel() {
        return level;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$game$Game$ScreenState() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$game$Game$ScreenState;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[ScreenState.values().length];
        try {
            arrn[ScreenState.GAME.ordinal()] = 1;
        }
        catch (NoSuchFieldError v1) {}
        try {
            arrn[ScreenState.LOADING.ordinal()] = 2;
        }
        catch (NoSuchFieldError v2) {}
        try {
            arrn[ScreenState.MENU.ordinal()] = 4;
        }
        catch (NoSuchFieldError v3) {}
        try {
            arrn[ScreenState.SAVING.ordinal()] = 3;
        }
        catch (NoSuchFieldError v4) {}
        $SWITCH_TABLE$game$Game$ScreenState = arrn;
        return $SWITCH_TABLE$game$Game$ScreenState;
    }

    public static enum ScreenState {
        GAME,
        LOADING,
        SAVING,
        MENU;
    }

}

