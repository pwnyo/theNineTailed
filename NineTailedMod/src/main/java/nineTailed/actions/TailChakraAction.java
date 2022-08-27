package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.orbs.Tail;
import nineTailed.powers.ChakraPower;

public class TailChakraAction extends AbstractGameAction {

    AbstractPlayer p;
    public TailChakraAction() {
        p = AbstractDungeon.player;
    }

    public void update() {
        int count = 0;
        for(int i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
            if (AbstractDungeon.player.orbs.get(i) instanceof Tail)  {
                count++;
            }
        }
        if (count > 0)
            addToBot(new ApplyPowerAction(p, p, new ChakraPower(p, count)));

        this.isDone = true;
    }
}