package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

public class SageStrikeAction extends AbstractGameAction {
    private AbstractMonster m;
    private DamageInfo info;

    public SageStrikeAction(AbstractMonster m, DamageInfo info) {
        this.m = m;
        this.info = info;
    }

    public void update() {
        if (!AbstractDungeon.player.stance.ID.equals(NeutralStance.STANCE_ID)) {
            AbstractPlayer p = AbstractDungeon.player;

            addToTop(new ChangeStanceAction("Neutral"));
            addToTop(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            addToTop(new DamageAction(m, info, AttackEffect.BLUNT_HEAVY));
        }

        this.isDone = true;
    }
}
