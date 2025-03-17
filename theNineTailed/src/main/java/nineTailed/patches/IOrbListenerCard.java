package nineTailed.patches;

import com.megacrit.cardcrawl.orbs.AbstractOrb;

public interface IOrbListenerCard {
    void onChannel(AbstractOrb o);
    void onGainOrbSlot();
    void onLoseOrbSlot();
    void onEvokeAndLoseOrRemove();
}

