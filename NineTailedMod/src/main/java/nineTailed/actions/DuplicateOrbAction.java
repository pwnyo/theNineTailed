package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class DuplicateOrbAction extends AbstractGameAction {
    private AbstractOrb orb;

    public DuplicateOrbAction() {
        this.actionType = AbstractGameAction.ActionType.DAMAGE;// 12
    }// 13

    public void update() {
        if (!AbstractDungeon.player.orbs.isEmpty()) {// 17
            this.orb = (AbstractOrb)AbstractDungeon.player.orbs.get(0);// 18
            if (this.orb instanceof EmptyOrbSlot) {// 19
                this.isDone = true;// 20
            } else {
                this.addToTop(new ChannelAction(this.orb));
            }
        }

        this.isDone = true;// 27
    }
}
