package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class TuckTailAction extends AbstractGameAction {

    public TuckTailAction(int block) {
        this.actionType = ActionType.BLOCK;
        this.amount = block;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractOrb orb : p.orbs) {
            if (!(orb instanceof EmptyOrbSlot)) {
                addToTop(new GainBlockAction(p, amount));
            }
        }

        this.isDone = true;
    }
}
