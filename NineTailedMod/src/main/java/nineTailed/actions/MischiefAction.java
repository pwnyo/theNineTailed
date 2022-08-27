package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nineTailed.orbs.Clone;

public class MischiefAction extends AbstractGameAction {
    private DamageInfo info = null;
    private AbstractCreature target;

    public MischiefAction(AbstractCreature m, DamageInfo info) {
        this.info = info;
        this.target = m;
    }// 17

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.BUFF) {
                addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
            }
        }

        this.isDone = true;
    }
}
