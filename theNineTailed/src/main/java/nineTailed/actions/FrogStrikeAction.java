package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

public class FrogStrikeAction extends AbstractGameAction {
    public FrogStrikeAction(AbstractMonster m, int amount) {
        this.target = m;
        this.amount = amount;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.stance.ID.equals(CalmStance.STANCE_ID)) {
            addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(target, amount, false)));
            addToBot(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            addToBot(new ChangeStanceAction(NeutralStance.STANCE_ID));
        } else {
            addToTop(new ChangeStanceAction(CalmStance.STANCE_ID));
        }

        this.isDone = true;
    }
}
