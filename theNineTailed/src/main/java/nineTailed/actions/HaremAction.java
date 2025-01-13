package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import nineTailed.orbs.Clone;

public class HaremAction extends AbstractGameAction {
    public HaremAction() {
        this.actionType = ActionType.DEBUFF;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int count = 0;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Clone) {
                count++;
            }
        }
        if (count > 0)
        {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, count, false), count, true));
                this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, count, false), count, true));
            }
        }
        this.isDone = true;
    }
}
