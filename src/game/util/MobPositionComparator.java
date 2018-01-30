/*
 * Decompiled with CFR 0_115.
 */
package game.util;

import game.entity.mob.Mob;
import java.util.Comparator;

public class MobPositionComparator
implements Comparator<Mob> {
    @Override
    public int compare(Mob mob1, Mob mob2) {
        if (mob1.y > mob2.y) {
            return 1;
        }
        if (mob1.y < mob2.y) {
            return -1;
        }
        return 0;
    }
}

