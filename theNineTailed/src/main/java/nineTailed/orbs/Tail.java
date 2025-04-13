package nineTailed.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
import nineTailed.NarutoMod;
import nineTailed.powers.AsuraFormPower;
import nineTailed.powers.ChakraPower;
import nineTailed.util.TextureLoader;

import static nineTailed.NarutoMod.makeID;
import static nineTailed.NarutoMod.makeOrbPath;

public class Tail extends AbstractOrb {

    public static final String ORB_ID = NarutoMod.makeID("Tail");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String[] DESC = orbString.DESCRIPTION;

    private static final Texture IMG = TextureLoader.getTexture(makeOrbPath("tail9.png"));

    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    protected static final float ORB_WAVY_DIST = 0.04f;
    protected static final float PI_4 = 12.566371f;
    protected static final float PI_DIV_16 = 0.19634955F;
    protected static final float ORB_BORDER_SCALE = 1.2F;
    protected AbstractPlayer p;
    public boolean isBijuable;
    public boolean isBonus;

    public Tail() {
        this(1, 1);
        ID = ORB_ID;
        name = orbString.NAME;
        img = IMG;
        updateDescription();

        isBijuable = true;
    }
    public Tail(int passiveAmount, int evokeAmount) {
        p = AbstractDungeon.player;

        this.evokeAmount = baseEvokeAmount = evokeAmount;
        this.passiveAmount = basePassiveAmount = passiveAmount;

        angle = MathUtils.random(360.0f);
        channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESC[0] + passiveAmount + DESC[1];
    }

    @Override
    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower(AsuraFormPower.POWER_ID);
        if (power != null) {
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
        } else {
            this.passiveAmount = this.basePassiveAmount;
        }
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new SFXAction(makeID("TAIL_EVOKE")));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(evokeAmount));
    }

    @Override
    public void onStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChakraPower(p, passiveAmount)));
    }

    protected void flash() {
        float speedTime = 0.6F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), speedTime));
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        //angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(cX, cY));
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, 0, 0, 0, 108, 108, false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play(makeID("TAIL_EVOKE"), 0.1f);
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play(makeID("TAIL_CHANNEL"), 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail();
    }
}
