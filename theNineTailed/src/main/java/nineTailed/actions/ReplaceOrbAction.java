package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ReplaceOrbAction extends AbstractGameAction {
    AbstractOrb replacementOrb;
    public ReplaceOrbAction(AbstractOrb orb) {
        this.duration = Settings.ACTION_DUR_FAST;// 14
        this.replacementOrb = orb;// 15
        this.actionType = AbstractGameAction.ActionType.DAMAGE;// 16
    }// 17

    public void update() {

        this.tickDuration();// 29
    }
}
