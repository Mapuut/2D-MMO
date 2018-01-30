/*
 * Decompiled with CFR 0_115.
 */
package game.level.tile;

import game.graphics.Sprite;

public abstract class Tile {
    public Sprite sprite;
    public static Tile noneTile = new NoneTile();
    public static Tile noneTileSolid = new NoneTileSolid();
    public static Tile voidTile = new VoidTile(Sprite.voidTile);
    public static Tile grass = new BackgroundTile(Sprite.grass);

    public static Tile dirt = new BackgroundTile(Sprite.dirt);
    public static Tile dirt2 = new BackgroundTile(Sprite.dirt2);
    public static Tile dirt3 = new BackgroundTile(Sprite.dirt3);

    public static Tile sand = new BackgroundTile(Sprite.sand);
    public static Tile sand2 = new BackgroundTile(Sprite.sand2);
    public static Tile sand3 = new BackgroundTile(Sprite.sand3);

    public static Tile sand_water = new BackgroundTile(Sprite.sand_water);
    public static Tile sand_water2 = new BackgroundTile(Sprite.sand_water2);
    public static Tile sand_water3 = new BackgroundTile(Sprite.sand_water3);

    public static Tile dirt_water = new BackgroundTile(Sprite.dirt_water);
    public static Tile dirt_water2 = new BackgroundTile(Sprite.dirt_water2);
    public static Tile dirt_water3 = new BackgroundTile(Sprite.dirt_water3);

    public static Tile cobblestone_up = new MaskTile(Sprite.cobblestone_up);
    public static Tile cobblestone_down = new SolidTile(Sprite.cobblestone_down);
    public static Tile castle_floor = new BackgroundTile(Sprite.castle_floor);
    public static Tile castlefront_down1 = new SolidTile(Sprite.castlefront_down1);
    public static Tile castlefront_down2 = new SolidTile(Sprite.castlefront_down2);
    public static Tile castlefront_down3 = new SolidTile(Sprite.castlefront_down3);
    public static Tile castledoor_up1 = new MaskTile(Sprite.castledoor_up1);
    public static Tile castledoor_up2 = new MaskTile(Sprite.castledoor_up2);
    public static Tile castledoor_middle1 = new MaskTile(Sprite.castledoor_middle1);
    public static Tile castledoor_middle2 = new MaskTile(Sprite.castledoor_middle2);
    public static Tile castledoor_middle3 = new MaskTile(Sprite.castledoor_middle3);
    public static Tile castledoor_middle4 = new MaskTile(Sprite.castledoor_middle4);
    public static Tile castledoor_down1 = new SolidTile(Sprite.castledoor_down1);
    public static Tile castledoor_down2 = new MaskTile(Sprite.castledoor_down2);
    public static Tile castledoor_down3 = new MaskTile(Sprite.castledoor_down3);
    public static Tile castledoor_down4 = new SolidTile(Sprite.castledoor_down4);
    public static Tile castlefront_up1 = new MaskTile(Sprite.castlefront_up1);
    public static Tile castlefront_up2 = new MaskTile(Sprite.castlefront_up2);
    public static Tile castlefront_up3 = new MaskTile(Sprite.castlefront_up3);
    public static Tile castlefront_middle1 = new MaskTile(Sprite.castlefront_middle1);
    public static Tile castlefront_middle2 = new MaskTile(Sprite.castlefront_middle2);
    public static Tile castlefront_middle3 = new MaskTile(Sprite.castlefront_middle3);
    public static Tile castlefront_up = new MaskTile(Sprite.castlefront_up);
    public static Tile castlefront_upleft = new MaskTile(Sprite.castlefront_upleft);
    public static Tile castlefront_upright = new MaskTile(Sprite.castlefront_upright);
    public static Tile castlefrontleftside_topup = new MaskTile(Sprite.castlefrontleftside_topup);
    public static Tile castlefrontleftside_topup_solid = new SolidTile(Sprite.castlefrontleftside_topup);
    public static Tile castlefrontleftside_top = new SolidTile(Sprite.castlefrontleftside_top);
    public static Tile castlefrontleftside_up = new SolidTile(Sprite.castlefrontleftside_up);
    public static Tile castlefrontleftside_middle = new SolidTile(Sprite.castlefrontleftside_middle);
    public static Tile castlefrontleftside_down = new MaskTile(Sprite.castlefrontleftside_down);
    public static Tile castlefrontrightside_topup = new MaskTile(Sprite.castlefrontrightside_topup);
    public static Tile castlefrontrightside_topup_solid = new SolidTile(Sprite.castlefrontrightside_topup);
    public static Tile castlefrontrightside_top = new SolidTile(Sprite.castlefrontrightside_top);
    public static Tile castlefrontrightside_up = new SolidTile(Sprite.castlefrontrightside_up);
    public static Tile castlefrontrightside_middle = new SolidTile(Sprite.castlefrontrightside_middle);
    public static Tile castlefrontrightside_down = new MaskTile(Sprite.castlefrontrightside_down);
    public static Tile castlefrontleftsideleft_topup_solid = new SolidTile(Sprite.castlefrontleftsideleft_topup);
    public static Tile castlefrontleftsideleft_topup = new MaskTile(Sprite.castlefrontleftsideleft_topup);
    public static Tile castlefrontleftsideleft_topup2 = new SolidTile(Sprite.castlefrontleftsideleft_topup2);
    public static Tile castlefrontleftsideleft_topup3 = new SolidTile(Sprite.castlefrontleftsideleft_topup3);
    public static Tile castlefrontleftsideleft_top = new SolidTile(Sprite.castlefrontleftsideleft_top);
    public static Tile castlefrontleftsideleft_up = new SolidTile(Sprite.castlefrontleftsideleft_up);
    public static Tile castlefrontleftsideleft_middle = new SolidTile(Sprite.castlefrontleftsideleft_middle);
    public static Tile castlefrontleftsideleft_down = new MaskTile(Sprite.castlefrontleftsideleft_down);
    public static Tile castlefrontrightsideright_topup_solid = new SolidTile(Sprite.castlefrontrightsideright_topup);
    public static Tile castlefrontrightsideright_topup = new MaskTile(Sprite.castlefrontrightsideright_topup);
    public static Tile castlefrontrightsideright_topup2 = new SolidTile(Sprite.castlefrontrightsideright_topup2);
    public static Tile castlefrontrightsideright_topup3 = new SolidTile(Sprite.castlefrontrightsideright_topup3);
    public static Tile castlefrontrightsideright_top = new SolidTile(Sprite.castlefrontrightsideright_top);
    public static Tile castlefrontrightsideright_up = new SolidTile(Sprite.castlefrontrightsideright_up);
    public static Tile castlefrontrightsideright_middle = new SolidTile(Sprite.castlefrontrightsideright_middle);
    public static Tile castlefrontrightsideright_down = new MaskTile(Sprite.castlefrontrightsideright_down);
    public static Tile castlefrontleftsideleftleft_topup = new MaskTile(Sprite.castlefrontleftsideleftleft_topup);
    public static Tile castlefrontleftsideleftleft_top = new MaskTile(Sprite.castlefrontleftsideleftleft_top);
    public static Tile castlefrontleftsideleftleft_up = new MaskTile(Sprite.castlefrontleftsideleftleft_up);
    public static Tile castlefrontleftsideleftleft_middle = new SolidTile(Sprite.castlefrontleftsideleftleft_middle);
    public static Tile castlefrontleftsideleftleft_down = new MaskTile(Sprite.castlefrontleftsideleftleft_down);
    public static Tile castlefrontleftsideleftleft_1 = new MaskTile(Sprite.castlefrontleftsideleftleft_1);
    public static Tile castlefrontleftsideleftleft_2 = new MaskTile(Sprite.castlefrontleftsideleftleft_2);
    public static Tile castlefrontrightsiderightright_topup = new MaskTile(Sprite.castlefrontrightsiderightright_topup);
    public static Tile castlefrontrightsiderightright_top = new MaskTile(Sprite.castlefrontrightsiderightright_top);
    public static Tile castlefrontrightsiderightright_up = new MaskTile(Sprite.castlefrontrightsiderightright_up);
    public static Tile castlefrontrightsiderightright_middle = new SolidTile(Sprite.castlefrontrightsiderightright_middle);
    public static Tile castlefrontrightsiderightright_down = new MaskTile(Sprite.castlefrontrightsiderightright_down);
    public static Tile castlefrontrightsiderightright_1 = new MaskTile(Sprite.castlefrontrightsiderightright_1);
    public static Tile castlefrontrightsiderightright_2 = new MaskTile(Sprite.castlefrontrightsiderightright_2);
    public static Tile castleback_up1 = new MaskTile(Sprite.castleback_up1);
    public static Tile castleback_up2 = new MaskTile(Sprite.castleback_up2);
    public static Tile castleback_up3 = new MaskTile(Sprite.castleback_up3);
    public static Tile castleback_middle1 = new MaskTile(Sprite.castleback_middle1);
    public static Tile castleback_middle2 = new MaskTile(Sprite.castleback_middle2);
    public static Tile castleback_middle3 = new MaskTile(Sprite.castleback_middle3);
    public static Tile castleback_down1 = new SolidTile(Sprite.castleback_down1);
    public static Tile castleback_down2 = new SolidTile(Sprite.castleback_down2);
    public static Tile castleback_down3 = new SolidTile(Sprite.castleback_down3);
    public static Tile castleback_leftsideup = new MaskTile(Sprite.castleback_leftsideup);
    public static Tile castleback_leftsidemiddle = new MaskTile(Sprite.castleback_leftsidemiddle);
    public static Tile castleback_leftsidedown = new SolidTile(Sprite.castleback_leftsidedown);
    public static Tile castleback_leftsidedowndown = new MaskTile(Sprite.castleback_leftsidedowndown);
    public static Tile castleback_rightsideup = new MaskTile(Sprite.castleback_rightsideup);
    public static Tile castleback_rightsidemiddle = new MaskTile(Sprite.castleback_rightsidemiddle);
    public static Tile castleback_rightsidedown = new SolidTile(Sprite.castleback_rightsidedown);
    public static Tile castleback_rightsidedowndown = new MaskTile(Sprite.castleback_rightsidedowndown);
    public static Tile castleback_leftsidetopcontinue = new MaskTile(Sprite.castleback_leftsidetopcontinue);
    public static Tile castleback_leftsideupcontinue = new MaskTile(Sprite.castleback_leftsideupcontinue);
    public static Tile castleback_leftsidemiddlecontinue = new MaskTile(Sprite.castleback_leftsidemiddlecontinue);
    public static Tile castleback_leftsidedowncontinue = new SolidTile(Sprite.castleback_leftsidedowncontinue);
    public static Tile castleback_leftsidedowndowncontinue = new SolidTile(Sprite.castleback_leftsidedowndowncontinue);
    public static Tile castleback_rightsidetopcontinue = new MaskTile(Sprite.castleback_rightsidetopcontinue);
    public static Tile castleback_rightsideupcontinue = new MaskTile(Sprite.castleback_rightsideupcontinue);
    public static Tile castleback_rightsidemiddlecontinue = new MaskTile(Sprite.castleback_rightsidemiddlecontinue);
    public static Tile castleback_rightsidedowncontinue = new SolidTile(Sprite.castleback_rightsidedowncontinue);
    public static Tile castleback_rightsidedowndowncontinue = new SolidTile(Sprite.castleback_rightsidedowndowncontinue);
    public static final int col_castle_floor = -8561602;
    public static final int col_castledoor_up1 = -8895488;
    public static final int col_castledoor_up2 = -8895487;
    public static final int col_castledoor_middle1 = -8895472;
    public static final int col_castledoor_middle2 = -8895471;
    public static final int col_castledoor_middle3 = -8895470;
    public static final int col_castledoor_middle4 = -8895469;
    public static final int col_castledoor_down1 = -8895456;
    public static final int col_castledoor_down2 = -8895455;
    public static final int col_castledoor_down3 = -8895454;
    public static final int col_castledoor_down4 = -8895453;
    public static final int col_castlefront_up1 = -13619152;
    public static final int col_castlefront_up2 = -13619151;
    public static final int col_castlefront_up3 = -13619150;
    public static final int col_castlefront_middle1 = -13619149;
    public static final int col_castlefront_middle2 = -13619148;
    public static final int col_castlefront_middle3 = -13619147;
    public static final int col_castlefront_down1 = -13619146;
    public static final int col_castlefront_down2 = -13619145;
    public static final int col_castlefront_down3 = -13619144;
    public static final int col_castlefront_up = -13619143;
    public static final int col_castlefront_upleft = -13619136;
    public static final int col_castlefront_upright = -13619135;
    public static final int col_castlefrontleftside_topup = -13619134;
    public static final int col_castlefrontleftside_topup_solid = -13619126;
    public static final int col_castlefrontleftside_top = -13619133;
    public static final int col_castlefrontleftside_up = -13619132;
    public static final int col_castlefrontleftside_middle = -13619131;
    public static final int col_castlefrontleftside_down = -13619130;
    public static final int col_castlefrontrightside_topup = -13619129;
    public static final int col_castlefrontrightside_topup_solid = -13619125;
    public static final int col_castlefrontrightside_top = -13619128;
    public static final int col_castlefrontrightside_up = -13619127;
    public static final int col_castlefrontrightside_middle = -13619120;
    public static final int col_castlefrontrightside_down = -13619119;
    public static final int col_castlefrontleftsideleft_topup_solid = -13619118;
    public static final int col_castlefrontleftsideleft_topup = -13619110;
    public static final int col_castlefrontleftsideleft_topup2 = -13619117;
    public static final int col_castlefrontleftsideleft_topup3 = -13619116;
    public static final int col_castlefrontleftsideleft_top = -13619115;
    public static final int col_castlefrontleftsideleft_up = -13619114;
    public static final int col_castlefrontleftsideleft_middle = -13619113;
    public static final int col_castlefrontleftsideleft_down = -13619112;
    public static final int col_castlefrontrightsideright_topup_solid = -13619111;
    public static final int col_castlefrontrightsideright_topup = -13619109;
    public static final int col_castlefrontrightsideright_topup2 = -13619104;
    public static final int col_castlefrontrightsideright_topup3 = -13619103;
    public static final int col_castlefrontrightsideright_top = -13619102;
    public static final int col_castlefrontrightsideright_up = -13619101;
    public static final int col_castlefrontrightsideright_middle = -13619100;
    public static final int col_castlefrontrightsideright_down = -13619099;
    public static final int col_castlefrontleftsideleftleft_topup = -13619098;
    public static final int col_castlefrontleftsideleftleft_top = -13619097;
    public static final int col_castlefrontleftsideleftleft_up = -13619096;
    public static final int col_castlefrontleftsideleftleft_middle = -13619095;
    public static final int col_castlefrontleftsideleftleft_down = -13619088;
    public static final int col_castlefrontleftsideleftleft_1 = -13619087;
    public static final int col_castlefrontleftsideleftleft_2 = -13619086;
    public static final int col_castlefrontrightsiderightright_topup = -13619085;
    public static final int col_castlefrontrightsiderightright_top = -13619084;
    public static final int col_castlefrontrightsiderightright_up = -13619083;
    public static final int col_castlefrontrightsiderightright_middle = -13619082;
    public static final int col_castlefrontrightsiderightright_down = -13619081;
    public static final int col_castlefrontrightsiderightright_1 = -13619080;
    public static final int col_castlefrontrightsiderightright_2 = -13619079;
    public static final int col_castleback_up1 = -13619072;
    public static final int col_castleback_up2 = -13619071;
    public static final int col_castleback_up3 = -13619070;
    public static final int col_castleback_middle1 = -13619069;
    public static final int col_castleback_middle2 = -13619068;
    public static final int col_castleback_middle3 = -13619067;
    public static final int col_castleback_down1 = -13619066;
    public static final int col_castleback_down2 = -13619065;
    public static final int col_castleback_down3 = -13619064;
    public static final int col_castleback_leftsideup = -13619063;
    public static final int col_castleback_leftsidemiddle = -13619056;
    public static final int col_castleback_leftsidedown = -13619055;
    public static final int col_castleback_leftsidedowndown = -13619054;
    public static final int col_castleback_rightsideup = -13619053;
    public static final int col_castleback_rightsidemiddle = -13619052;
    public static final int col_castleback_rightsidedown = -13619051;
    public static final int col_castleback_rightsidedowndown = -13619050;
    public static final int col_castleback_leftsidetopcontinue = -13619049;
    public static final int col_castleback_leftsideupcontinue = -13619048;
    public static final int col_castleback_leftsidemiddlecontinue = -13619047;
    public static final int col_castleback_leftsidedowncontinue = -13618944;
    public static final int col_castleback_leftsidedowndowncontinue = -13618943;
    public static final int col_castleback_rightsidetopcontinue = -13618942;
    public static final int col_castleback_rightsideupcontinue = -13618941;
    public static final int col_castleback_rightsidemiddlecontinue = -13618940;
    public static final int col_castleback_rightsidedowncontinue = -13618939;
    public static final int col_castleback_rightsidedowndowncontinue = -13618938;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public Tile() {
    }

    public void render(int x, int y) {
    }

    public boolean solid() {
        return false;
    }
}

