package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BodyDoubleAction extends AbstractGameAction {
    AbstractPlayer p;

    public BodyDoubleAction(int gain) {
        p = AbstractDungeon.player;
        this.actionType = ActionType.SPECIAL;
        this.amount = gain;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasEmptyOrb()) {
            addToTop(new IncreaseMaxOrbAction(amount));
        }
        this.isDone = true;
    }
}
