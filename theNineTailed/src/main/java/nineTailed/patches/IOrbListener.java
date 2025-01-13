package nineTailed.patches;

import com.megacrit.cardcrawl.orbs.AbstractOrb;

public interface IOrbListener {
    void onChannel(AbstractOrb o);
    void onEvoke(AbstractOrb o);
    void onGainOrbSlot();
    void onLoseOrbSlot();
    void onRemoveOrb();
}

