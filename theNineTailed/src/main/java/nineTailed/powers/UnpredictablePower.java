package nineTailed.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import nineTailed.NarutoMod;
import nineTailed.patches.DiscardHookPatch;

public class UnpredictablePower extends AbstractPower implements DiscardHookPatch.OnDiscard2 {
    public AbstractCreature source;

    public static final String POWER_ID = NarutoMod.makeID("UnpredictablePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UnpredictablePower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("confusion");
        updateDescription();
    }


    @Override
    public void onManualDiscard2() {
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new VigorPower(owner, amount)));
        addToBot(new GainBlockAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
