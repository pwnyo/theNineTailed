package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.characters.NineTailed;

public class RecheckAnimationAction extends AbstractGameAction {
    public RecheckAnimationAction() {
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (AbstractDungeon.player instanceof NineTailed) {
                ((NineTailed) AbstractDungeon.player).recheckAnimation();
            }
        }

        this.tickDuration();
    }
}
