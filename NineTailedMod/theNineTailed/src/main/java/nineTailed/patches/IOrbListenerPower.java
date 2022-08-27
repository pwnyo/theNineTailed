package nineTailed.patches;

import com.megacrit.cardcrawl.orbs.AbstractOrb;

public interface IOrbListenerPower {
    void onTriggerPassive(AbstractOrb o, boolean atStart);
}
