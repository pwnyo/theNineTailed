package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MultiCloneAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private DamageInfo.DamageType damageType;
    private int energyOnUse = -1;

    public MultiCloneAction(boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
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

        if (effect > 0) {
            if (!AbstractDungeon.player.orbs.isEmpty()) {
                AbstractOrb orb = AbstractDungeon.player.orbs.get(0);
                if (!(orb instanceof EmptyOrbSlot)) {
                    addToTop(new ChannelMultipleAction(orb));
                }
            }

            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}