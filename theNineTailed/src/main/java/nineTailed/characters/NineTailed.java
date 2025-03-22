package nineTailed.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import nineTailed.NarutoMod;
import nineTailed.cards.basic.Defend;
import nineTailed.cards.basic.Rasengan;
import nineTailed.cards.basic.ShadowClones;
import nineTailed.cards.basic.Strike;
import nineTailed.orbs.Tail;
import nineTailed.orbs.Truthseeker;
import nineTailed.powers.BijuTailPower;
import nineTailed.powers.KuramaModePower;
import nineTailed.powers.SageModePower;
import nineTailed.powers.TeamworkPower;
import nineTailed.relics.boss.SpiralScroll;
import nineTailed.relics.commoner.SealedScroll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static nineTailed.NarutoMod.*;
import static nineTailed.characters.NineTailed.Enums.NARUTO_ORANGE;

public class NineTailed extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(NarutoMod.class.getName());
    
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass NARUTO;
        @SpireEnum(name = "NARUTO_ORANGE")
        public static AbstractCard.CardColor NARUTO_ORANGE;
        @SpireEnum(name = "NARUTO_ORANGE")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType NARU_ORANGE;
    }
    
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 82;
    public static final int MAX_HP = 82;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;
    
    private static final String ID = makeID("Naruto");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public static final String[] orbTextures = {
            "nineTailedResources/images/char/naruto/orb/layer1.png",
            "nineTailedResources/images/char/naruto/orb/layer2.png",
            "nineTailedResources/images/char/naruto/orb/layer3.png",
            "nineTailedResources/images/char/naruto/orb/layer4.png",
            "nineTailedResources/images/char/naruto/orb/layer5.png",
            "nineTailedResources/images/char/naruto/orb/layer6.png",
            "nineTailedResources/images/char/naruto/orb/layer1d.png",
            "nineTailedResources/images/char/naruto/orb/layer2d.png",
            "nineTailedResources/images/char/naruto/orb/layer3d.png",
            "nineTailedResources/images/char/naruto/orb/layer4d.png",
            "nineTailedResources/images/char/naruto/orb/layer5d.png",};

    private NarutoAnimState currentAnimState = NarutoAnimState.NORMAL;
    private String prevStanceID;
    
    public NineTailed(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "nineTailedResources/images/char/naruto/orb/vfx.png", null,
                new SpineAnimation(NARUTO_ATLAS, NARUTO_JSON, 1f));
        
        
        initializeClass(NARUTO_NORMAL,

                NARUTO_SHOULDER_1,
                NARUTO_SHOULDER_2,
                NARUTO_CORPSE,
                getLoadout(), 0.0F, 0.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(
                NARUTO_ATLAS,
                NARUTO_JSON,
                1.2F);
        AnimationState.TrackEntry e = state.setAnimation(0, "standard", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);

        prevStanceID = NeutralStance.STANCE_ID;
    }
    
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }
    
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        
        logger.info("Begin loading starter Deck Strings");

        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Rasengan.ID);
        retVal.add(ShadowClones.ID);

        return retVal;
    }
    
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(SealedScroll.ID);
        
        UnlockTracker.markRelicAsSeen(SealedScroll.ID);
        
        return retVal;
    }
    
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA(makeID("SUMMON"), MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT,true);
    }
    
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return makeID("CLONE_CHANNEL");
    }
    
    @Override
    public int getAscensionMaxHPLoss() {
        return 6;
    }
    
    @Override
    public AbstractCard.CardColor getCardColor() {
        return NARUTO_ORANGE;
    }
    
    @Override
    public Color getCardTrailColor() {
        return NarutoMod.ORANGE;
    }
    
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }
    
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Rasengan();
    }
    
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }
    
    @Override
    public AbstractPlayer newInstance() {
        return new NineTailed(name, chosenClass);
    }
    
    @Override
    public Color getCardRenderColor() {
        return NarutoMod.ORANGE;
    }
    
    @Override
    public Color getSlashAttackColor() {
        return NarutoMod.ORANGE;
    }
    
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }
    
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }
    
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public void channelOrb(AbstractOrb orbToSet) {
        super.channelOrb(orbToSet);
        if (orbToSet instanceof Truthseeker) {
            trySetAnimation(NarutoAnimState.SIXPATHS);
        }
        else if (orbToSet instanceof Tail && hasRelic(SpiralScroll.ID)
                && (currentAnimState == NarutoAnimState.SAGE || currentAnimState == NarutoAnimState.NORMAL))
        {
            trySetAnimation(NarutoAnimState.CHAKRA);
        }
    }

    @Override
    public void onStanceChange(String id) {
        if (id.equals(DivinityStance.STANCE_ID)) {
            trySetAnimation(NarutoAnimState.SIXPATHS);
            return;
        }
        if (id.equals(WrathStance.STANCE_ID)
                && (currentAnimState == NarutoAnimState.SAGE || currentAnimState == NarutoAnimState.NORMAL))
        {
            trySetAnimation(NarutoAnimState.CHAKRA);
        }
        else if (prevStanceID.equals(CalmStance.STANCE_ID)
                && (currentAnimState == NarutoAnimState.CHAKRA || currentAnimState == NarutoAnimState.NORMAL))
        {
            trySetAnimation(NarutoAnimState.SAGE);
        }
        prevStanceID = id;
    }
    @Override
    public void applyStartOfCombatPreDrawLogic() {
        super.applyStartOfCombatPreDrawLogic();

        trySetAnimation(getBaseAnim());
    }

    @Override
    public void onVictory() {
        super.onVictory();
        trySetAnimation(NarutoAnimState.NORMAL);
    }
    public void trySetAnimation(NarutoAnimState newAnimState) {
        if (currentAnimState != newAnimState) {
            String animName;
            switch (newAnimState) {
                case SAGE: animName = "sage"; break;
                case CHAKRA: animName = "chakra"; break;
                case KURAMA: animName = "kurama"; break;
                case SIXPATHS: animName = "sixpaths"; break;
                default: animName = "standard";
            }
            if (newAnimState == NarutoAnimState.NORMAL) {
                AbstractDungeon.effectsQueue.add(new EmptyStanceEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY));
            }
            else if (newAnimState == NarutoAnimState.SAGE) {
                CardCrawlGame.sound.play("STANCE_ENTER_CALM");
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
            }
            else {
                CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
                AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
            }
            currentAnimState = newAnimState;
            state.clearTrack(0);
            state.setAnimation(0, animName, true);
        }
    }

    public void recheckAnimation() {
        if (isSixPaths()) {
            trySetAnimation(NarutoAnimState.SIXPATHS);
        }
        else if (isKurama()) {
            trySetAnimation(NarutoAnimState.KURAMA);
        }
        else if (hasPower(SageModePower.POWER_ID)) {
            trySetAnimation(NarutoAnimState.SAGE);
        }
        else if (hasRelic(SpiralScroll.ID) || stance.ID.equals(WrathStance.STANCE_ID)) {
            trySetAnimation(NarutoAnimState.CHAKRA);
        }
        else {
            trySetAnimation(NarutoAnimState.NORMAL);
        }
    }
    NarutoAnimState getBaseAnim() {
        if (hasRelic(SpiralScroll.ID)) {
            return NarutoAnimState.CHAKRA;
        }
        else {
            return NarutoAnimState.NORMAL;
        }
    }
    boolean isSixPaths() {
        if (stance.ID.equals(DivinityStance.STANCE_ID)) {
            return true;
        }
        if (!orbs.isEmpty()) {
            for (AbstractOrb o : orbs) {
                if (o instanceof Truthseeker) {
                    trySetAnimation(NarutoAnimState.SIXPATHS);
                    return true;
                }
            }
        }
        boolean hasSage = hasPower(SageModePower.POWER_ID);
        return hasSage && isKurama();
    }
    boolean isKurama() {
        return hasPower(KuramaModePower.POWER_ID) || hasPower(TeamworkPower.POWER_ID) || hasPower(BijuTailPower.POWER_ID);
    }
    public enum NarutoAnimState {
        NORMAL, //default when no other condition is met
        SAGE, //1) Sage Mode power or 2) after you exit Calm
        CHAKRA, //1) Spiral Scroll relic default or 2) after you enter Wrath
        //these next two modes take priority
        KURAMA, //1) Kurama Mode power, 2) Teamwork power, 3) Gathering power
        SIXPATHS, //1) After you Channel a Truthseeker, 2) you gain Sage Mode AND Kurama Mode, 3) after you enter Divinity
    }
}
