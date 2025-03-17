package nineTailed.relics.rarer;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import nineTailed.NarutoMod;
import nineTailed.orbs.Clone;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class ExplicitBook extends CustomRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(ExplicitBook.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("illicit_book.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("illicit_book.png"));
    private static boolean usedThisCombat = false;
    public static final int COUNT = 2, AMOUNT = 1;

    public ExplicitBook() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID);
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
        counter = COUNT;
        this.beginPulse();
        this.pulse = true;
    }

    @Override
    public void onEquip() {
        counter = 0;
    }

    @Override
    public void onEvokeOrb(AbstractOrb ammo) {
        if (usedThisCombat) {
            return;
        }
        if (ammo instanceof Clone) {
            AbstractPlayer p = AbstractDungeon.player;

            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, AMOUNT, false)));
                    addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, AMOUNT, false)));
                }
            }

            counter--;
            if (counter <= 0) {
                usedThisCombat = true;
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + COUNT + this.DESCRIPTIONS[1] + AMOUNT + this.DESCRIPTIONS[2];
    }
}
