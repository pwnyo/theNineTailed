package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Tail;

public class TailBlockAction extends AbstractGameAction {
    AbstractPlayer p;
    int block;

    public TailBlockAction(int block) {
        p = AbstractDungeon.player;
        this.block = block;
        this.actionType = ActionType.BLOCK;
    }

    public void update() {
        int count = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof Tail)  {
                count++;
            }
        }
        if (count > 0)
            addToBot(new GainBlockAction(p, block * count));

        this.isDone = true;
    }
}