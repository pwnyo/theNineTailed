package nineTailed.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import nineTailed.NarutoMod;
import nineTailed.patches.IOrbListenerOrb;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeID;
import static nineTailed.NarutoMod.makeOrbPath;

public class Clone extends AbstractOrb implements IOrbListenerOrb {

    public static final String ORB_ID = NarutoMod.makeID("Clone");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    private static final Texture IMG = TextureLoader.getTexture(makeOrbPath("clone.png"));

    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public Clone() {
        ID = ORB_ID;
        name = orbString.NAME;
        img = IMG;
        evokeAmount = baseEvokeAmount = 3;
        passiveAmount = basePassiveAmount = 1;
        updateDescription();
        if (AbstractDungeon.player != null) {
            AbstractPlayer p = AbstractDungeon.player;
            float speedTime = 0.6F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), speedTime));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, passiveAmount)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, passiveAmount)));
        }
        angle = MathUtils.random(360.0f);
        channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESC[0] + passiveAmount + DESC[1] + passiveAmount + DESC[2] + evokeAmount + DESC[3];
    }

    @Override
    public void applyFocus() {
    }

    @Override
    public void onEndOfTurn() {
        float speedTime = 0.6F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), speedTime));
        this.evokeAmount -= this.passiveAmount;
        if (evokeAmount <= 0)
        {
            AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(this));
        }
    }

    @Override
    public void onEvoke() {
        AbstractPlayer p = AbstractDungeon.player;
        if (evokeAmount > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VigorPower(p, evokeAmount)));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, evokeAmount));
        }
    }

    @Override
    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play(makeID("CLONE_EVOKE"), 0.1f);
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        //angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY));
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 108.0f, 108.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, 0, 0, 0, 108, 108, false, false);
        renderText(sb);
        hb.render(sb);
    }
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play(makeID("CLONE_CHANNEL"), 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Clone();
    }

    @Override
    public void onEvokeAndLoseOrRemove() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -passiveAmount)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -passiveAmount)));
    }
}