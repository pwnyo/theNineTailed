package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class FrogSongAction extends AbstractGameAction {
    AbstractPlayer p;

    public FrogSongAction(int amount) {
        this.amount = amount;
        p = AbstractDungeon.player;
    }

    public void update() {
        if (AbstractDungeon.player.stance.ID.equals("Calm")) {
            addToTop(new ApplyPowerAction(p, p, new VigorPower(p, amount)));
            addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));// 45
            } else {
                this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));// 51
            }

            for (AbstractMonster m :  AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -amount), -amount, true, AttackEffect.NONE));
            }
            for (AbstractMonster m :  AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.hasPower(ArtifactPower.POWER_ID)) {
                    addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, -amount), -amount, true, AttackEffect.NONE));
                }
            }
        } else {
            this.addToTop(new ChangeStanceAction("Calm"));
        }

        this.isDone = true;
    }
}
