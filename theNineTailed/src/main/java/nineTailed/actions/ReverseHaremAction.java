package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class ReverseHaremAction extends AbstractGameAction {
    AbstractPlayer p;
    public ReverseHaremAction(int multiplier) {
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.amount = multiplier;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower(VigorPower.POWER_ID)) {
            int mult = amount - 1;
            int amt = this.p.getPower(VigorPower.POWER_ID).amount;
            this.addToTop(new ApplyPowerAction(this.p, this.p, new VigorPower(this.p, amt * mult), amt * mult));
        }

        this.tickDuration();
    }
}
