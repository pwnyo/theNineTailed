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
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import nineTailed.NarutoMod;
import nineTailed.cards.basic.Defend;
import nineTailed.cards.basic.Rasengan;
import nineTailed.cards.basic.ShadowClones;
import nineTailed.cards.basic.Strike;
import nineTailed.orbs.Truthseeker;
import nineTailed.powers.KuramaModePower;
import nineTailed.powers.SageModePower;
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

    private NarutoAnimState currentAnimState = NarutoAnimState.STANDARD;
    
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
        CardCrawlGame.sound.playA(makeID("CLONE_CHANNEL"), MathUtils.random(-0.2F, 0.2F));
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
    public void applyStartOfCombatPreDrawLogic() {
        super.applyStartOfCombatPreDrawLogic();

        //Chakra Mode
        if (hasRelic(SpiralScroll.ID)) {
            updateAnimation(NarutoAnimState.CHAKRA);
        }
        //Normal
        else {
            updateAnimation(NarutoAnimState.STANDARD);
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        //Chakra Mode
        if (hasRelic(SpiralScroll.ID)) {
            updateAnimation(NarutoAnimState.CHAKRA);
        }
        //Normal
        else {
            updateAnimation(NarutoAnimState.STANDARD);
        }
    }

    public void updateAnimation(NarutoAnimState animState) {
        if (currentAnimState != animState) {
            String animName;
            switch (animState) {
                case SAGE: animName = "sage"; break;
                case CHAKRA: animName = "chakra"; break;
                case KURAMA: animName = "kurama"; break;
                case SIXPATHS: animName = "sixpaths"; break;
                default: animName = "standard";
            }
            if (animState == NarutoAnimState.SAGE) {
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
            }
            else if (animState != NarutoAnimState.STANDARD) {
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));// 76
                AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
            }
            state.clearTrack(0);
            state.setAnimation(0, animName, true);
        }
    }

    public void checkAnimation() {
        //Six Paths
        if (!orbs.isEmpty()) {
            for (AbstractOrb o : orbs) {
                if (o instanceof Truthseeker) {
                    updateAnimation(NarutoAnimState.SIXPATHS);
                    return;
                }
            }
        }

        //Six Paths
        boolean hasKurama = hasPower(KuramaModePower.POWER_ID);
        boolean hasSage = hasPower(SageModePower.POWER_ID);
        if (hasKurama && hasSage) {
            updateAnimation(NarutoAnimState.SIXPATHS);
        }
        //Kurama Mode
        else if (hasKurama) {
            updateAnimation(NarutoAnimState.KURAMA);
        }
        //Sage Mode
        else if (hasSage) {
            updateAnimation(NarutoAnimState.SAGE);
        }
        //Chakra Mode
        else if (hasRelic(SpiralScroll.ID)) {
            updateAnimation(NarutoAnimState.CHAKRA);
        }
        else {
            updateAnimation(NarutoAnimState.STANDARD);
        }
    }
    public enum NarutoAnimState {
        STANDARD,
        SAGE,
        CHAKRA,
        KURAMA,
        SIXPATHS,
    }
}
