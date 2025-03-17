package nineTailed.relics.rarer;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnLoseHpRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.NarutoMod;
import nineTailed.powers.UnderstandingPower;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeRelicOutlinePath;
import static nineTailed.NarutoMod.makeRelicPath;

public class BrokenMask extends CustomRelic implements BetterOnLoseHpRelic {
    // ID, images, text.
    public static final String ID = NarutoMod.makeID(BrokenMask.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("shattered_mask.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("shattered_mask.png"));
    private static final int AMT = 2;

    public BrokenMask() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public int betterOnLoseHp(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner instanceof AbstractMonster &&
                info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0) {
            this.flash();
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, p, new UnderstandingPower(info.owner, AMT)));
        }
        return damageAmount;
    }
}
