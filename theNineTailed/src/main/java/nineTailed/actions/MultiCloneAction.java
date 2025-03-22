package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MultiCloneAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int energyOnUse = -1;
    int bonus;

    public MultiCloneAction(boolean freeToPlayOnce, int energyOnUse, int bonus) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.bonus = bonus;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        effect += bonus;

        if (effect > 0) {
            if (!AbstractDungeon.player.orbs.isEmpty()) {
                AbstractOrb originalOrb = AbstractDungeon.player.orbs.get(0);
                if (!(originalOrb instanceof EmptyOrbSlot)) {
                    for (int i = 0; i < effect; i++) {
                        AbstractOrb copy = originalOrb.makeCopy();
                        copy.passiveAmount = originalOrb.passiveAmount;
                        copy.evokeAmount = originalOrb.evokeAmount;
                        copy.updateDescription();
                        addToTop(new ChannelAction(copy));
                    }
                }
            }

            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}