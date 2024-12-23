package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BodyDoubleAction extends AbstractGameAction {
    public BodyDoubleAction() {
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (AbstractDungeon.player.hand.size() == 4)
        {
            addToTop(new IncreaseMaxOrbAction(1));
        }

        this.isDone = true;
    }
}
