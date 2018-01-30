package game.graphics.UI.Interface.clickmenu;

import game.Game;
import game.entity.items.Item;
import game.entity.items.ItemBag;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.UI.Interface.InterfaceManager;
import game.graphics.UI.Interface.InterfaceManager.mode;
import game.graphics.UI.Interface.WindowBasics;
import game.graphics.UI.Interface.inventory.InventoryRegion;
import game.graphics.UI.Interface.inventory.InventoryRegion.Selected;
import game.graphics.UI.Interface.inventory.Slots;
import game.graphics.UI.Interface.inventory.WindowInventory;
import game.graphics.UI.Interface.inventory.WindowInventory.InvMode;
import game.graphics.UI.fonts.Font8;
import game.input.Mouse;
import game.level.Level;
import game.level.TileHash;
import java.util.ArrayList;
import java.util.List;

public class ClickMenu
  extends WindowBasics
{
  public static Level level;
  private static List<WindowRegion> region = new ArrayList<WindowRegion>();
  public static activated currentlyActivated = activated.NONE;
  private static Sprite sprite = new Sprite(62, 20, -1);
  private static int tileindex = 0;
  private static int amount = 0;
  private static int currentregion = 0;
  private static int choosed = 0;
  private static boolean wasPressed1 = false;
  private static boolean wasPressed3 = false;
  private static boolean showmore = false;
  
  private static class InventoryOptions
  {
    private static boolean visible = false;
    private static int x = 0;
    private static int y = 0;
    private static int width = 50;
    private static int height = 68;
    private static int choosed = 0;
    private static Sprite sprite = new Sprite(42, 20, -13946833);
    
    private static void render()
    {
      if (visible)
      {
        Screen.renderWindow(x, y, width, height);
        Screen.renderSprite(x + 4 + Screen.xOffset, y + choosed * 20 + 4 + Screen.yOffset, sprite);
        Font8.render(x + 5, y + 10, "Do Something", true);
        Font8.render(x + 5, y + 30, "Info", true);
        Font8.render(x + 5, y + 50, "Drop", true);
      }
    }
  }
  
  private static class TakeOptions
  {
    private static boolean visible = false;
    private static int x = 0;
    private static int y = 0;
    private static int width = 50;
    private static int height = 68;
    private static int choosed = 0;
    private static Sprite sprite = new Sprite(42, 20, -13946833);
    
    private static void render()
    {
      if (visible)
      {
        Screen.renderWindow(x, y, width, height);
        Screen.renderSprite(x + 4 + Screen.xOffset, y + choosed * 20 + 4 + Screen.yOffset, sprite);
        Font8.render(x + 5, y + 10, "Info", true);
        Font8.render(x + 5, y + 30, "Take", true);
        Font8.render(x + 5, y + 50, "Skip", true);
      }
    }
  }
  
  private static class TakeInventoryOptions
  {
    private static boolean visible = false;
    private static int x = 0;
    private static int y = 0;
    private static int width = 65;
    private static int height = 88;
    private static int choosed = 0;
    private static Sprite sprite = new Sprite(57, 20, -13946833);
    
    private static void render()
    {
      if (visible)
      {
        Screen.renderWindow(x, y, width, height);
        Screen.renderSprite(x + 4 + Screen.xOffset, y + choosed * 20 + 4 + Screen.yOffset, sprite);
        Font8.render(x + 5, y + 10, "Inventory 1", true);
        Font8.render(x + 5, y + 30, "Inventory 2", true);
        Font8.render(x + 5, y + 50, "Inventory 3", true);
        Font8.render(x + 5, y + 70, "Skip", true);
      }
    }
  }
  
  public void update()
  {
    if ((wasPressed1) && (Mouse.getButton() != 1)) {
      wasPressed1 = false;
    }
    if ((wasPressed3) && (Mouse.getButton() != 3)) {
      wasPressed3 = false;
    }
    if (currentlyActivated == activated.LEFT_CLICK_ITEM_MENU)
    {
      if (!updateitemchache()) {
        return;
      }
      updateOptionMenuPosition();
      updatechoosed();
      
      updateheight();
      if (showmore) {
        if (currentregion == region.size() - 1)
        {
          if (currentregion != amount - 1)
          {
            region.add(new WindowRegion(((WindowRegion)region.get(currentregion)).x + ((WindowRegion)region.get(currentregion)).width, ((WindowRegion)region.get(currentregion)).y, ((WindowRegion)region.get(currentregion)).width, ((WindowRegion)region.get(currentregion)).height));
            if (((WindowRegion)region.get(currentregion + 1)).x + ((WindowRegion)region.get(currentregion + 1)).width > Screen.width) {
              ((WindowRegion)region.get(currentregion + 1)).x = (((WindowRegion)region.get(currentregion)).x - ((WindowRegion)region.get(currentregion + 1)).width);
            }
            ((WindowRegion)region.get(currentregion + 1)).activated = true;
            updateheight();
            ((WindowRegion)region.get(currentregion + 1)).y = (((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - ((WindowRegion)region.get(currentregion + 1)).height);
          }
        }
        else
        {
          ((WindowRegion)region.get(currentregion + 1)).activated = true;
          if (((Mouse.getButton() == 3) && (!wasPressed3)) || ((Mouse.getButton() == 1) && (!wasPressed1)))
          {
            wasPressed1 = true;
            wasPressed3 = true;
            ((WindowRegion)region.get(currentregion + 1)).x = (Mouse.getX() - 10);
            ((WindowRegion)region.get(currentregion + 1)).y = (Mouse.getY() - 10);
          }
        }
      }
      if ((!showmore) && (currentregion < region.size() - 1))
      {
        int size = region.size() - 1;
        for (int i = size; i > currentregion; i--) {
          region.remove(i);
        }
      }
      if ((Mouse.getX() < TakeOptions.x) || (Mouse.getX() > TakeOptions.x + TakeOptions.width) || (Mouse.getY() < TakeOptions.y) || (Mouse.getY() > TakeOptions.y + TakeOptions.height))
      {
        for (int i = region.size() - 1; i >= 0; i--) {
          if ((Mouse.getX() < ((WindowRegion)region.get(i)).x) || (Mouse.getX() > ((WindowRegion)region.get(i)).x + ((WindowRegion)region.get(i)).width) || (Mouse.getY() < ((WindowRegion)region.get(i)).y) || (Mouse.getY() > ((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height))
          {
            if (i == 0) {
              clear();
            }
          }
          else if (((WindowRegion)region.get(i)).activated)
          {
            if (currentregion == i) {
              break;
            }
            currentregion = i;
            return;
          }
        }
      }
      else
      {
        if (TakeOptions.visible)
        {
          currentlyActivated = activated.TAKE_MENU;
          return;
        }
        for (int i = region.size() - 1; i >= 0; i--) {
          if ((Mouse.getX() < ((WindowRegion)region.get(i)).x) || (Mouse.getX() > ((WindowRegion)region.get(i)).x + ((WindowRegion)region.get(i)).width) || (Mouse.getY() < ((WindowRegion)region.get(i)).y) || (Mouse.getY() > ((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height))
          {
            if (i == 0) {
              clear();
            }
          }
          else if (((WindowRegion)region.get(i)).activated)
          {
            if (currentregion == i) {
              break;
            }
            currentregion = i;
            return;
          }
        }
      }


      if ((Mouse.getButton() == 1) && (!wasPressed1))
      {
        wasPressed1 = true;
        ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
      }
      if ((Mouse.getButton() == 3) && (!wasPressed3))
      {
        wasPressed3 = true;
        TakeInventoryOptions.x = Mouse.getX() - 10;
        if (TakeInventoryOptions.x < 0) {
          TakeInventoryOptions.x = 0;
        }
        if (TakeInventoryOptions.x + TakeInventoryOptions.width > Screen.width) {
          TakeInventoryOptions.x = Screen.width - TakeInventoryOptions.width;
        }
        TakeInventoryOptions.y = Mouse.getY() - 10;
        if (TakeInventoryOptions.y < 0) {
          TakeInventoryOptions.y = 0;
        }
        if (TakeInventoryOptions.y + TakeInventoryOptions.height > Screen.height) {
          TakeInventoryOptions.y = Screen.height - TakeInventoryOptions.height;
        }
        TakeInventoryOptions.visible = true;
        currentlyActivated = activated.TAKE_MENU_INVENTORY_CHOOSING;
      }
    }
    else if (currentlyActivated == activated.TAKE_MENU)
    {
      if (!updateitemchache()) {
        return;
      }
      updatechoosed();
      updateOptionInventoryMenuPosition();
      if ((Mouse.getX() < TakeInventoryOptions.x) || (Mouse.getX() > TakeInventoryOptions.x + TakeInventoryOptions.width) || (Mouse.getY() < TakeInventoryOptions.y) || (Mouse.getY() > TakeInventoryOptions.y + TakeInventoryOptions.height))
      {
        if ((Mouse.getX() < TakeOptions.x) || (Mouse.getX() > TakeOptions.x + TakeOptions.width) || (Mouse.getY() < TakeOptions.y) || (Mouse.getY() > TakeOptions.y + TakeOptions.height)) {
          for (int i = region.size() - 1; i >= 0; i--) {
            if ((Mouse.getX() < ((WindowRegion)region.get(i)).x) || (Mouse.getX() > ((WindowRegion)region.get(i)).x + ((WindowRegion)region.get(i)).width) || (Mouse.getY() < ((WindowRegion)region.get(i)).y) || (Mouse.getY() > ((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height))
            {
              if (i == 0) {
                clear();
              }
            }
            else if (((WindowRegion)region.get(i)).activated)
            {
              currentregion = i;
              currentlyActivated = activated.LEFT_CLICK_ITEM_MENU;
              TakeOptions.choosed = 0;
              TakeInventoryOptions.visible = false;
              return;
            }
          }
        }
      }
      else if (TakeInventoryOptions.visible)
      {
        currentlyActivated = activated.TAKE_MENU_INVENTORY_CHOOSING;
        return;
      }

      
      TakeOptions.choosed = (Mouse.getY() - (TakeOptions.y + 4)) / 20;
      if (TakeOptions.choosed < 0) {
        TakeOptions.choosed = 0;
      }
      if (TakeOptions.choosed >= 3) {
        TakeOptions.choosed = 2;
      }
      if (TakeOptions.choosed == 1) {
        TakeInventoryOptions.visible = true;
      } else {
        TakeInventoryOptions.visible = false;
      }
      if ((Mouse.getButton() == 1) && (!wasPressed1))
      {
        wasPressed1 = true;
        if (TakeOptions.choosed == 0) {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
        if (TakeOptions.choosed == 1) {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
        if (TakeOptions.choosed == 2)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
      }
      else if ((Mouse.getButton() == 3) && (!wasPressed3))
      {
        wasPressed3 = true;
        if (TakeOptions.choosed == 0)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
        if (TakeOptions.choosed == 1)
        {
          TakeInventoryOptions.x = Mouse.getX() - 10;
          if (TakeInventoryOptions.x < 0) {
            TakeInventoryOptions.x = 0;
          }
          if (TakeInventoryOptions.x + TakeInventoryOptions.width > Screen.width) {
            TakeInventoryOptions.x = Screen.width - TakeInventoryOptions.width;
          }
          TakeInventoryOptions.y = Mouse.getY() - 10;
          if (TakeInventoryOptions.y < 0) {
            TakeInventoryOptions.y = 0;
          }
          if (TakeInventoryOptions.y + TakeInventoryOptions.height > Screen.height) {
            TakeInventoryOptions.y = Screen.height - TakeInventoryOptions.height;
          }
          TakeInventoryOptions.visible = true;
          currentlyActivated = activated.TAKE_MENU_INVENTORY_CHOOSING;
          return;
        }
        if (TakeOptions.choosed == 2) {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
      }
    }
    else if (currentlyActivated == activated.TAKE_MENU_INVENTORY_CHOOSING)
    {
      if (!updateitemchache()) {
        return;
      }
      updatechoosed();
      updateheight();
      if ((Mouse.getX() < TakeInventoryOptions.x) || (Mouse.getX() > TakeInventoryOptions.x + TakeInventoryOptions.width) || (Mouse.getY() < TakeInventoryOptions.y) || (Mouse.getY() > TakeInventoryOptions.y + TakeInventoryOptions.height)) {
        if ((Mouse.getX() < TakeOptions.x) || (Mouse.getX() > TakeOptions.x + TakeOptions.width) || (Mouse.getY() < TakeOptions.y) || (Mouse.getY() > TakeOptions.y + TakeOptions.height))
        {
          for (int i = region.size() - 1; i >= 0; i--) {
            if ((Mouse.getX() < ((WindowRegion)region.get(i)).x) || (Mouse.getX() > ((WindowRegion)region.get(i)).x + ((WindowRegion)region.get(i)).width) || (Mouse.getY() < ((WindowRegion)region.get(i)).y) || (Mouse.getY() > ((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height))
            {
              if (i == 0) {
                clear();
              }
            }
            else if (((WindowRegion)region.get(i)).activated)
            {
              currentregion = i;
              currentlyActivated = activated.LEFT_CLICK_ITEM_MENU;
              TakeOptions.choosed = 0;
              TakeInventoryOptions.visible = false;
              return;
            }
          }
        }
        else
        {
          currentlyActivated = activated.TAKE_MENU;
          TakeInventoryOptions.choosed = 0;
          return;
        }
      }
      TakeInventoryOptions.choosed = (Mouse.getY() - (TakeInventoryOptions.y + 4)) / 20;
      if (TakeInventoryOptions.choosed < 0) {
        TakeInventoryOptions.choosed = 0;
      }
      if (TakeInventoryOptions.choosed >= 4) {
        TakeInventoryOptions.choosed = 3;
      }
      if ((Mouse.getButton() == 1) && (!wasPressed1))
      {
        wasPressed1 = true;
        if (TakeInventoryOptions.choosed == 0)
        {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
        else if (TakeInventoryOptions.choosed == 1)
        {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
        else if (TakeInventoryOptions.choosed == 2)
        {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
        else if (TakeInventoryOptions.choosed == 3)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
      }
      else if ((Mouse.getButton() == 3) && (!wasPressed3))
      {
        wasPressed3 = true;
        if (TakeInventoryOptions.choosed == 0)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
        else if (TakeInventoryOptions.choosed == 1)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
        else if (TakeInventoryOptions.choosed == 2)
        {
          choosed += 1;
          if (choosed >= level.tilehash[tileindex].items.size()) {
            choosed = 0;
          }
        }
        else if (TakeInventoryOptions.choosed == 3)
        {
          ((ItemBag)level.tilehash[tileindex].items.get(choosed)).take(Game.player);
        }
      }
    }
    else if (currentlyActivated == activated.INVENTORY_OPTION_MENU)
    {
      if ((Mouse.getX() < InventoryOptions.x) || (Mouse.getX() > InventoryOptions.x + InventoryOptions.width) || (Mouse.getY() < InventoryOptions.y) || (Mouse.getY() > InventoryOptions.y + InventoryOptions.height))
      {
        clear();
        return;
      }
      InventoryOptions.choosed = (Mouse.getY() - (InventoryOptions.y + 4)) / 20;
      if (InventoryOptions.choosed < 0) {
        InventoryOptions.choosed = 0;
      }
      if (InventoryOptions.choosed > 2) {
        InventoryOptions.choosed = 2;
      }
      if ((!InventoryOptions.visible) && (Mouse.getButton() == -1))
      {
        clear();
        return;
      }
      if ((Mouse.getButton() == 1) && (!wasPressed1))
      {
        wasPressed1 = true;
        if (InventoryOptions.choosed == 2) {
          if (((game.graphics.UI.Interface.inventory.Inventory)((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).inventory.get(((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).activatedInv)).slots[((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).selectednum].item != null)
          {
            level.add(new ItemBag(Game.player.x >> 5 << 5, Game.player.y >> 5 << 5, ((game.graphics.UI.Interface.inventory.Inventory)((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).inventory.get(((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).activatedInv)).slots[((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).selectednum].item, level));
            ((game.graphics.UI.Interface.inventory.Inventory)((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).inventory.get(((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).activatedInv)).slots[((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).selectednum].reset();
            InventoryOptions.visible = false;
          }
          else
          {
            InventoryOptions.visible = false;
            return;
          }
        }
      }
    }
  }
  
  private boolean updateitemchache()
  {
    if (level.tilehash[tileindex].items.isEmpty())
    {
      clear();
      return false;
    }
    int currentheight = level.tilehash[tileindex].items.size() * 20;
    if (currentheight > (Screen.height / 20 - 1) * 20)
    {
      amount = currentheight / ((Screen.height / 20 - 2) * 20) + 1;
      if ((amount - 2) * ((Screen.height / 20 - 2) * 20) + (Screen.height / 20 - 1) * 20 >= currentheight) {
        amount -= 1;
      }
      if (currentregion >= amount)
      {
        for (int i = 0; i < currentregion - (currentregion - amount); i++)
        {
          ((WindowRegion)region.get(i)).x = ((WindowRegion)region.get(i + (currentregion - amount) + 1)).x;
          ((WindowRegion)region.get(i)).y = ((WindowRegion)region.get(i + (currentregion - amount) + 1)).y;
          if (((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height > Screen.height) {
            ((WindowRegion)region.get(i)).y = (Screen.height - ((WindowRegion)region.get(i)).height);
          }
        }
        currentregion = amount - 1;
      }
      int size = region.size() - 1;
      for (int i = size; i > amount - 1; i--) {
        region.remove(i);
      }
      return true;
    }
    amount = 1;
    if (amount != region.size())
    {
      if (currentregion != 0) {
        for (int i = 0; i < 1; i++)
        {
          ((WindowRegion)region.get(i)).x = ((WindowRegion)region.get(currentregion)).x;
          ((WindowRegion)region.get(i)).y = ((WindowRegion)region.get(currentregion)).y;
          if (((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height > Screen.height) {
            ((WindowRegion)region.get(i)).y = (Screen.height - ((WindowRegion)region.get(i)).height);
          }
        }
      }
      int size = region.size() - 1;
      for (int i = size; i > amount - 1; i--) {
        region.remove(i);
      }
    }
    currentregion = 0;
    return true;
  }
  
  private void updatechoosed()
  {
    if (choosed > level.tilehash[tileindex].items.size() - 1) {
      choosed = level.tilehash[tileindex].items.size() - 1;
    }
    if (currentlyActivated == activated.LEFT_CLICK_ITEM_MENU) {
      if (amount == 1)
      {
        choosed = (Mouse.getY() - (((WindowRegion)region.get(currentregion)).y + 4)) / 20;
        if (choosed < 0) {
          choosed = 0;
        }
        if (choosed > level.tilehash[tileindex].items.size() - 1) {
          choosed = level.tilehash[tileindex].items.size() - 1;
        }
      }
      else
      {
        choosed = (Screen.height / 20 - 2) * (amount - (amount - currentregion)) + (Mouse.getY() - (((WindowRegion)region.get(currentregion)).y + 4)) / 20;
        if (choosed > level.tilehash[tileindex].items.size() - 1) {
          choosed = level.tilehash[tileindex].items.size() - 1;
        }
        if (currentregion == amount - 1)
        {
          if (choosed < currentregion * (Screen.height / 20 - 2)) {
            choosed = currentregion * (Screen.height / 20 - 2);
          }
          if (choosed > currentregion * (Screen.height / 20 - 2) + (level.tilehash[tileindex].items.size() - currentregion * (Screen.height / 20 - 2))) {
            choosed = currentregion * (Screen.height / 20 - 2) + (level.tilehash[tileindex].items.size() - currentregion * (Screen.height / 20 - 2));
          }
        }
        else
        {
          if (choosed < currentregion * (Screen.height / 20 - 2)) {
            choosed = currentregion * (Screen.height / 20 - 2);
          }
          if (choosed >= currentregion * (Screen.height / 20 - 2) + (Screen.height / 20 - 2))
          {
            choosed = currentregion * (Screen.height / 20 - 2) + (Screen.height / 20 - 3);
            showmore = true;
          }
          else
          {
            showmore = false;
          }
        }
      }
    }
  }
  
  private void updateheight()
  {
    if (amount == 1)
    {
      ((WindowRegion)region.get(currentregion)).height = (level.tilehash[tileindex].items.size() * 20 + 8);
      if ((TakeOptions.y < ((WindowRegion)region.get(currentregion)).y) || (TakeOptions.y >= ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 8)) {
        ((WindowRegion)region.get(currentregion)).y += ((TakeOptions.y - ((WindowRegion)region.get(currentregion)).y - ((WindowRegion)region.get(currentregion)).height) / 20 + 1) * 20;
      }
      if (TakeOptions.y + TakeOptions.height == Screen.height)
      {
        ((WindowRegion)region.get(currentregion)).y = (Screen.height - ((WindowRegion)region.get(currentregion)).height);
        if ((Mouse.getY() < ((WindowRegion)region.get(currentregion)).y) && (currentlyActivated == activated.LEFT_CLICK_ITEM_MENU)) {
          ((WindowRegion)region.get(currentregion)).y = (Mouse.getY() - 8);
        }
      }
      if (((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height > Screen.height) {
        ((WindowRegion)region.get(currentregion)).y = (Screen.height - ((WindowRegion)region.get(currentregion)).height);
      }
      if (((WindowRegion)region.get(currentregion)).y < 0) {
        ((WindowRegion)region.get(currentregion)).y = 0;
      }
    }
    else if (currentregion == amount - 1)
    {
      for (int i = 0; i < amount - 1; i++) {
        ((WindowRegion)region.get(i)).height = ((Screen.height / 20 - 1) * 20 + 8);
      }
      ((WindowRegion)region.get(amount - 1)).height = (level.tilehash[tileindex].items.size() * 20 - (Screen.height / 20 - 2) * 20 * (amount - 1) + 8);
      if (TakeOptions.y >= ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 8) {
        ((WindowRegion)region.get(currentregion)).y += ((TakeOptions.y - ((WindowRegion)region.get(currentregion)).y - ((WindowRegion)region.get(currentregion)).height) / 20 + 1) * 20;
      }
      if (TakeOptions.y + TakeOptions.height == Screen.height)
      {
        ((WindowRegion)region.get(currentregion)).y = (Screen.height - ((WindowRegion)region.get(currentregion)).height);
        if ((Mouse.getY() < ((WindowRegion)region.get(currentregion)).y) && (currentlyActivated == activated.LEFT_CLICK_ITEM_MENU)) {
          ((WindowRegion)region.get(currentregion)).y = (Mouse.getY() - 8);
        }
      }
      if (((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height > Screen.height) {
        ((WindowRegion)region.get(currentregion)).y = (Screen.height - ((WindowRegion)region.get(currentregion)).height);
      }
      if (((WindowRegion)region.get(currentregion)).y < 0) {
        ((WindowRegion)region.get(currentregion)).y = 0;
      }
    }
    else
    {
      for (int i = 0; i < region.size() - 1; i++)
      {
        ((WindowRegion)region.get(i)).height = ((Screen.height / 20 - 1) * 20 + 8);
        if (((WindowRegion)region.get(i)).y + ((WindowRegion)region.get(i)).height > Screen.height) {
          ((WindowRegion)region.get(i)).y = (Screen.height - ((WindowRegion)region.get(i)).height);
        }
      }
      if (region.size() - 1 == amount - 1)
      {
        ((WindowRegion)region.get(amount - 1)).height = (level.tilehash[tileindex].items.size() * 20 - (Screen.height / 20 - 2) * 20 * (amount - 1) + 8);
        if (((WindowRegion)region.get(amount - 1)).y + ((WindowRegion)region.get(amount - 1)).height > Screen.height) {
          ((WindowRegion)region.get(amount - 1)).y = (Screen.height - ((WindowRegion)region.get(amount - 1)).height);
        }
        ((WindowRegion)region.get(amount - 1)).y = (((WindowRegion)region.get(amount - 2)).y + ((WindowRegion)region.get(amount - 2)).height - ((WindowRegion)region.get(amount - 1)).height);
      }
      else
      {
        ((WindowRegion)region.get(region.size() - 1)).height = ((Screen.height / 20 - 1) * 20 + 8);
        if (((WindowRegion)region.get(region.size() - 1)).y + ((WindowRegion)region.get(region.size() - 1)).height > Screen.height) {
          ((WindowRegion)region.get(region.size() - 1)).y = (Screen.height - ((WindowRegion)region.get(region.size() - 1)).height);
        }
      }
    }
  }
  
  private void updateOptionMenuPosition()
  {
    TakeOptions.x = ((WindowRegion)region.get(currentregion)).x + ((WindowRegion)region.get(currentregion)).width;
    if (amount == 1)
    {
      TakeOptions.y = (Mouse.getY() - (((WindowRegion)region.get(currentregion)).y + 4)) / 20 * 20 + ((WindowRegion)region.get(currentregion)).y;
      if (TakeOptions.y >= ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 8) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 28;
      }
      if (TakeOptions.y < ((WindowRegion)region.get(currentregion)).y) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y;
      }
      TakeOptions.visible = true;
    }
    else if (currentregion == amount - 1)
    {
      TakeOptions.y = (Mouse.getY() - (((WindowRegion)region.get(currentregion)).y + 4)) / 20 * 20 + ((WindowRegion)region.get(currentregion)).y;
      if (TakeOptions.y >= ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 8) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 28;
      }
      if (TakeOptions.y < ((WindowRegion)region.get(currentregion)).y) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y;
      }
      TakeOptions.visible = true;
    }
    else
    {
      TakeOptions.y = (Mouse.getY() - (((WindowRegion)region.get(currentregion)).y + 4)) / 20 * 20 + ((WindowRegion)region.get(currentregion)).y;
      if (TakeOptions.y >= ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 8) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y + ((WindowRegion)region.get(currentregion)).height - 28;
      }
      if (TakeOptions.y < ((WindowRegion)region.get(currentregion)).y) {
        TakeOptions.y = ((WindowRegion)region.get(currentregion)).y;
      }
      if (!showmore) {
        TakeOptions.visible = true;
      } else {
        TakeOptions.visible = false;
      }
    }
    if (TakeOptions.x + TakeOptions.width > Screen.width) {
      TakeOptions.x = ((WindowRegion)region.get(currentregion)).x - TakeOptions.width;
    }
    if (TakeOptions.y + TakeOptions.height > Screen.height) {
      TakeOptions.y = Screen.height - TakeOptions.height;
    }
  }
  
  private void updateOptionInventoryMenuPosition()
  {
    TakeInventoryOptions.x = TakeOptions.x + TakeOptions.width;
    if (TakeInventoryOptions.x + TakeInventoryOptions.width > Screen.width) {
      TakeInventoryOptions.x = TakeOptions.x - TakeInventoryOptions.width;
    }
    TakeInventoryOptions.y = TakeOptions.y + 20;
    if (TakeInventoryOptions.y + TakeInventoryOptions.height > Screen.height) {
      TakeInventoryOptions.y = Screen.height - TakeInventoryOptions.height;
    }
  }
  
  private void clear()
  {
    currentregion = 0;
    this.visible = false;
    region.removeAll(region);
    currentlyActivated = activated.NONE;
    TakeOptions.visible = false;
    TakeOptions.choosed = 0;
    TakeInventoryOptions.choosed = 0;
    TakeInventoryOptions.visible = false;
    choosed = 0;
    tileindex = 0;
    showmore = false;
    InterfaceManager.currentMode = InterfaceManager.mode.GAME;
  }
  
  public void renderTake()
  {
    Screen.renderSprite(((WindowRegion)region.get(currentregion)).x + 4 + Screen.xOffset, ((WindowRegion)region.get(currentregion)).y + 4 + (choosed - currentregion * (Screen.height / 20 - 2)) * 20 + Screen.yOffset, sprite);
    for (int i = 0; i < region.size(); i++) {
      if (((WindowRegion)region.get(i)).activated)
      {
        Screen.renderWindow(((WindowRegion)region.get(i)).x, ((WindowRegion)region.get(i)).y, ((WindowRegion)region.get(i)).width, ((WindowRegion)region.get(i)).height);
        int items = 0;
        if (i < amount - 1) {
          items = Screen.height / 20 - 1;
        } else {
          items = level.tilehash[tileindex].items.size() - (Screen.height / 20 - 2) * (amount - 1);
        }
        if (items + i * (Screen.height / 20 - 2) > level.tilehash[tileindex].items.size()) {
          items = level.tilehash[tileindex].items.size() - i * (Screen.height / 20 - 2);
        }
        for (int j = 0; j < items; j++) {
          if ((i != amount - 1) && (j == items - 1)) {
            Font8.render(((WindowRegion)region.get(i)).x + 5, ((WindowRegion)region.get(i)).y + 20 * j + 10, "Show more >", true);
          } else {
            Font8.render(((WindowRegion)region.get(i)).x + 5, ((WindowRegion)region.get(i)).y + 20 * j + 10, ((ItemBag)level.tilehash[tileindex].items.get(j + i * (Screen.height / 20 - 2))).item.name, true);
          }
        }
      }
    }
    TakeOptions.render();
    TakeInventoryOptions.render();
  }
  
  public void renderInv() {}
  
  public void render()
  {
    switch (currentlyActivated)
    {
    case TAKE_MENU_INVENTORY_CHOOSING: 
      renderInv();
      break;
    case LEFT_CLICK_ITEM_MENU: 
      renderTake();
      break;
    case INVENTORY_OPTION_MENU: 
      break;
    case NONE: 
      renderTake();
      break;
    case TAKE_MENU: 
      renderTake();
      break;
    }
  }
  
  public static enum activated
  {
    NONE,  LEFT_CLICK_ITEM_MENU,  TAKE_MENU,  TAKE_MENU_INVENTORY_CHOOSING,  INVENTORY_OPTION_MENU;
  }
  
  public boolean check()
  {
    if (InterfaceManager.currentMode == InterfaceManager.mode.GAME)
    {
      if (Mouse.getButton() == 3)
      {
        int x = Screen.xOffset + Mouse.getX() >> 5;
        int y = Screen.yOffset + Mouse.getY() >> 5;
        if ((x < 0) || (x >= level.tilesx) || (y < 0) || (y >= level.tilesy)) {
          return false;
        }
        if (!level.tilehash[(x + y * level.tilesx)].items.isEmpty())
        {
          int currentx = Mouse.getX() - 10;
          int currenty = Mouse.getY() - 10;
          int currentheight = level.tilehash[(x + y * level.tilesx)].items.size() * 20;
          int currentwidth = 70;
          currentregion = 0;
          amount = 1;
          if (currentheight > (Screen.height / 20 - 1) * 20)
          {
            amount = currentheight / ((Screen.height / 20 - 2) * 20) + 1;
            currentheight = (Screen.height / 20 - 1) * 20 + 8;
          }
          else
          {
            currentheight += 8;
          }
          if (currentx + currentwidth > Screen.width) {
            currentx = Screen.width - currentwidth;
          }
          if (currenty + currentheight > Screen.height) {
            currenty = Screen.height - currentheight;
          }
          if (currentx < 0) {
            currentx = 0;
          }
          if (currenty < 0) {
            currenty = 0;
          }
          currentlyActivated = activated.LEFT_CLICK_ITEM_MENU;
          
          region.add(new WindowRegion(currentx, currenty, currentwidth, currentheight));
          ((WindowRegion)region.get(0)).activated = true;
          tileindex = x + y * level.tilesx;
          wasPressed3 = true;
          this.visible = true;
          InterfaceManager.currentMode = InterfaceManager.mode.LEFT_CLICK;
          InterfaceManager.activated = this;
          return true;
        }
        return false;
      }
    }
    else if (InterfaceManager.currentMode == InterfaceManager.mode.INVENTORY) {
      if (Mouse.getButton() == 3) {
        if (WindowInventory.invmode == WindowInventory.InvMode.REGULAR)
        {
          ((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).updateSelected();
          if (((InventoryRegion)WindowInventory.windows.get(WindowInventory.currentwindow)).selectedtype == InventoryRegion.Selected.ITEM)
          {
            currentlyActivated = activated.INVENTORY_OPTION_MENU;
            
            int currentx = Mouse.getX() - 10;
            int currenty = Mouse.getY() - 10;
            int currentwidth = 50;
            int currentheight = 68;
            if (currentx + currentwidth > Screen.width) {
              currentx = Screen.width - currentwidth;
            }
            if (currenty + currentheight > Screen.height) {
              currenty = Screen.height - currentheight;
            }
            if (currentx < 0) {
              currentx = 0;
            }
            if (currenty < 0) {
              currenty = 0;
            }
            InventoryOptions.x = currentx;
            InventoryOptions.y = currenty;
            InventoryOptions.width = currentwidth;
            InventoryOptions.height = currentheight;
            InventoryOptions.visible = true;
            InventoryOptions.choosed = 0;
            
            wasPressed3 = true;
            this.visible = true;
            InterfaceManager.currentMode = InterfaceManager.mode.LEFT_CLICK;
            InterfaceManager.activated = this;
            
            return true;
          }
        }
      }
    }
    return false;
  }
}



