package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Clone;
import nineTailed.orbs.Tail;
import nineTailed.powers.AsuraFormPowerOld;
import nineTailed.powers.OutnumberPowerOld;

public class ChannelMultipleAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private int count;
    AbstractPlayer p;

    public ChannelMultipleAction(AbstractOrb newOrbType) {
        this(newOrbType, 1);
    }// 15

    public ChannelMultipleAction(AbstractOrb newOrbType, int count) {
        p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.orbType = newOrbType;
        this.count = count;
    }

    public void update() {
        if (orbType.ID.equals(Clone.ORB_ID) && p.hasPower(OutnumberPowerOld.POWER_ID)) {
            count += p.getPower(OutnumberPowerOld.POWER_ID).amount;
        }
        else if (orbType.ID.equals(Tail.ORB_ID) && p.hasPower(AsuraFormPowerOld.POWER_ID)) {
            count *= (p.getPower(AsuraFormPowerOld.POWER_ID).amount + 1);
        }

        for (int i = 0; i < count; i++) {
            addToTop(new ChannelAction(orbType.makeCopy()));
        }
        this.isDone = true;
    }
}
