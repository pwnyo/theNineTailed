package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class FrogSongAction extends AbstractGameAction {
    AbstractPlayer p;

    public FrogSongAction(int amount) {
        this.amount = amount;
        p = AbstractDungeon.player;
    }

    public void update() {
        if (AbstractDungeon.player.stance.ID.equals("Calm")) {
            addToTop(new ChangeStanceAction(NeutralStance.STANCE_ID));
            addToTop(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));

            for (AbstractMonster m :  AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.hasPower(ArtifactPower.POWER_ID)) {
                    addToTop(new ApplyPowerAction(m, p, new GainStrengthPower(m, -amount), -amount, true, AttackEffect.NONE));
                }
            }

            for (AbstractMonster m :  AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToTop(new ApplyPowerAction(m, p, new StrengthPower(m, -amount), -amount, true, AttackEffect.NONE));
            }
            if (Settings.FAST_MODE) {
                this.addToTop(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));// 45
            } else {
                this.addToTop(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));// 51
            }
            addToTop(new SFXAction("ATTACK_PIERCING_WAIL"));

        } else {
            this.addToTop(new ChangeStanceAction("Calm"));
        }

        this.isDone = true;
    }
}
