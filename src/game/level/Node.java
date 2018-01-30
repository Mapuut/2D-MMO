/*
 * Decompiled with CFR 0_115.
 */
package game.level;

import game.util.Vector2i;

public class Node {
    public Vector2i tile;
    public Node parent;
    public double fcost;
    public double gcost;
    public double hcost;

    public Node(Vector2i tile, Node parent, double gcost, double hcost) {
        this.tile = tile;
        this.parent = parent;
        this.gcost = gcost;
        this.hcost = hcost;
        this.fcost = gcost + hcost;
    }
}

