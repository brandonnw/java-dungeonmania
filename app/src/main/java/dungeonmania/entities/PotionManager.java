package dungeonmania.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.collectables.potions.Potion;
import dungeonmania.entities.inventory.Inventory;

public class PotionManager {
    private Queue<Potion> queue = new LinkedList<>();
    private Potion inEffective = null;
    private int nextTrigger = 0;
    private Set<PotionListener> potionListeners = new HashSet<>();

    public Potion getEffectivePotion() {
        return inEffective;
    }

    public void triggerNext(int currentTick) {
        if (queue.isEmpty()) {
            inEffective = null;
            potionListeners.forEach(PotionListener::notifyNoPotion);
            return;
        }
        inEffective = queue.remove();
        potionListeners.forEach(e -> e.notifyPotion(inEffective));
        nextTrigger = currentTick + inEffective.getDuration();
    }

    public void usePotion(Potion potion, int tick, Inventory inventory) {
        inventory.remove(potion);
        queue.add(potion);
        if (inEffective == null) {
            triggerNext(tick);
        }
    }

    public void onTick(int tick) {
        if (inEffective == null || tick >= nextTrigger) {
            triggerNext(tick);
        }
    }

    public BattleStatistics applyBuff(BattleStatistics origin) {
        if (getEffectivePotion() != null) {
            return getEffectivePotion().applyBuff(origin);
        }

        return origin;
    }

    public void registerListener(PotionListener e) {
        potionListeners.add(e);

        if (getEffectivePotion() != null)
            e.notifyPotion(getEffectivePotion());
    }

    public void removeListener(PotionListener e) {
        potionListeners.remove(e);
    }
}
