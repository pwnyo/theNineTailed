package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Blizzard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.orbs.Clone;

public class UzumakiBarrageAction extends AbstractGameAction {
    private DamageInfo info = null;
    private AbstractCreature target;

    public UzumakiBarrageAction(AbstractCreature m, DamageInfo info) {
        this.info = info;// 15
        this.target = m;// 16
    }// 17

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = 0; i < p.orbs.size(); ++i) {
            if (p.orbs.get(i) instanceof Clone) {
                addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_LIGHT, true));
            }
        }

        this.isDone = true;
    }
}
