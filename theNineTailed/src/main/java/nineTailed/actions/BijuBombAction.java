package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Tail;

public class BijuBombAction extends AbstractGameAction {
    public BijuBombAction() {
        this.actionType = ActionType.ENERGY;
    }

    public void update() {
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs)
        {
            if (o instanceof Tail)
            {
                count++;
            }
        }
        AbstractDungeon.player.gainEnergy(count);

        this.isDone = true;
    }
}
