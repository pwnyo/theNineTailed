package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import nineTailed.orbs.Clone;

public class CloneImpulseAction extends AbstractGameAction {
    boolean useAll;
    public CloneImpulseAction(boolean all) {
        useAll = all;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && !AbstractDungeon.player.orbs.isEmpty()) {

            AbstractPlayer p = AbstractDungeon.player;
            for (AbstractOrb o : p.orbs) {
                if (o instanceof Clone) {
                    o.onStartOfTurn();
                    o.onEndOfTurn();
                    if (!useAll)
                        break;
                }
            }

            if (AbstractDungeon.player.hasRelic("Cables") && !(AbstractDungeon.player.orbs.get(0) instanceof EmptyOrbSlot)) {
                (p.orbs.get(0)).onStartOfTurn();
                (p.orbs.get(0)).onEndOfTurn();
            }
        }

        this.tickDuration();
    }
}