package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class HumanWaveAction extends AbstractGameAction {
    int[] multiDamage;
    int block;
    private boolean freeToPlayOnce = false;
    private DamageType damageType;
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public HumanWaveAction(AbstractPlayer p, int[] multiDamage, int block, DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.block = block;
        this.p = p;
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

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(effect, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(effect, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.BLUNT_LIGHT, true));
                addToBot(new GainBlockAction(p, block));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}

