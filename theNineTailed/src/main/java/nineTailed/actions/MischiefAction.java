package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MischiefAction extends AbstractGameAction {

    public MischiefAction(int amount) {
        this.amount = amount;
    }// 17

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.BUFF) {
                addToTop(new GainBlockAction(p, amount));
            }
        }

        this.isDone = true;
    }
}
