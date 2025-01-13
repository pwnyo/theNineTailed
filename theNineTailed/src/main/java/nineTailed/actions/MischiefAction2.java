package nineTailed.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.TriggerPassiveAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import nineTailed.orbs.Clone;

public class MischiefAction2 extends AbstractGameAction {
    public MischiefAction2(int block) {
        this.amount = block;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Clone) {
                addToTop(new TriggerPassiveAction());
                addToTop(new GainBlockAction(p, amount));
            }
        }

        this.isDone = true;
    }
}
