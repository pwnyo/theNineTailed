package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.stances.CalmStance;

public class SenjutsuAction extends AbstractGameAction {
    private AbstractPlayer p;

    public SenjutsuAction(int amount) {
        this.amount = amount;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        if (p.stance.ID.equals(CalmStance.STANCE_ID)) {
            addToTop(new ApplyPowerAction(p, p, new VigorPower(p, amount)));
        }
        else {
            addToTop(new ApplyPowerAction(p, p, new MantraPower(p, amount)));
        }
        this.isDone = true;
    }
}
