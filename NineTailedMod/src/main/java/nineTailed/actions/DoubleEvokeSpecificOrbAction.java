package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class DoubleEvokeSpecificOrbAction extends AbstractGameAction {
    private AbstractOrb orb;

    public DoubleEvokeSpecificOrbAction(AbstractOrb orb) {
        this.duration = Settings.ACTION_DUR_FAST;// 14
        this.orb = orb;// 15
        this.actionType = ActionType.DAMAGE;// 16
    }// 17

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && this.orb != null) {// 22 23
            AbstractDungeon.player.orbs.remove(this.orb);// 24
            AbstractDungeon.player.orbs.add(0, this.orb);// 25
            addToBot(new AnimateOrbAction(1));// 33
            addToBot(new EvokeWithoutRemovingOrbAction(1));// 34
            addToBot(new AnimateOrbAction(1));
            addToBot(new EvokeOrbAction(1));
        }

        this.tickDuration();// 29
    }// 30
}
