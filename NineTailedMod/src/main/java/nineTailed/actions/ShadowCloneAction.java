package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import nineTailed.orbs.Clone;
import nineTailed.orbs.Tail;
import nineTailed.powers.ChakraPower;
import nineTailed.powers.LosePowerPower;

public class ShadowCloneAction extends AbstractGameAction {
    AbstractPlayer p;
    public ShadowCloneAction() {
        p = AbstractDungeon.player;
    }

    public void update() {
        int count = 0;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Clone) {
                count++;
            }
        }
        if (count > 0) {
            AbstractPower str = new StrengthPower(p, count);
            AbstractPower dex = new DexterityPower(p, count);
            addToBot(new ApplyPowerAction(p, p, str));
            addToBot(new ApplyPowerAction(p, p, dex));
            addToBot(new ApplyPowerAction(p, p, new LosePowerPower(p, str, count)));
            addToBot(new ApplyPowerAction(p, p, new LosePowerPower(p, dex, count)));
        }

        this.isDone = true;
    }
}
