package nineTailed;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.events.IdentityCrisisEvent;
import nineTailed.potions.Ramen;
import nineTailed.potions.ToadOil;
import nineTailed.potions.YangVessel;
import nineTailed.relics.boss.Habanero;
import nineTailed.relics.boss.RightHand;
import nineTailed.relics.boss.SpiralScroll;
import nineTailed.relics.commoner.Bells;
import nineTailed.relics.commoner.CrystalNecklace;
import nineTailed.relics.commoner.PaperFlowers;
import nineTailed.relics.commoner.SealedScroll;
import nineTailed.relics.rarer.BrokenMask;
import nineTailed.relics.rarer.BullHorn;
import nineTailed.relics.rarer.ExplicitBook;
import nineTailed.relics.rarer.RedScarf;
import nineTailed.util.IDCheckDontTouchPls;
import nineTailed.util.TextureLoader;
import nineTailed.variables.DefaultCustomVariable;
import nineTailed.variables.DefaultSecondMagicNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class NarutoMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        AddAudioSubscriber
    {
    
    public static final Logger logger = LogManager.getLogger(NarutoMod.class.getName());
    private static String modID;
    
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;
    
    private static final String MODNAME = "The Nine-Tailed";
    private static final String AUTHOR = "Pwnyo";
    private static final String DESCRIPTION = "Adds Naruto to the game. Features ~80 new cards, 11 new relics, and 3 new potions.";
    
    public static final Color ORANGE = CardHelper.getColor(235.0f, 127.0f, 20.0f);
    
    private static final String ATTACK_NARUTO_ORANGE = "nineTailedResources/images/512/bg_attack_naruto.png";
    private static final String SKILL_NARUTO_ORANGE = "nineTailedResources/images/512/bg_skill_naruto.png";
    private static final String POWER_NARUTO_ORANGE = "nineTailedResources/images/512/bg_power_naruto.png";
    
    private static final String ENERGY_ORB_NARUTO_ORANGE = "nineTailedResources/images/512/card_naruto_energy.png";
    private static final String CARD_ENERGY_ORB = "nineTailedResources/images/512/card_naruto_energy_small.png";
    
    private static final String ATTACK_NARUTO_ORANGE_PORTRAIT = "nineTailedResources/images/1024/bg_attack_naruto.png";
    private static final String SKILL_NARUTO_ORANGE_PORTRAIT = "nineTailedResources/images/1024/bg_skill_naruto.png";
    private static final String POWER_NARUTO_ORANGE_PORTRAIT = "nineTailedResources/images/1024/bg_power_naruto.png";
    private static final String ENERGY_ORB_NARUTO_ORANGE_PORTRAIT = "nineTailedResources/images/1024/card_naruto_energy.png";
    
    private static final String NARUTO_BUTTON = "nineTailedResources/images/charSelect/naruto_button.png";
    private static final String NARUTO_PORTRAIT = "nineTailedResources/images/charSelect/naruto_big.png";
    public static final String NARUTO_NORMAL = "nineTailedResources/images/char/naruto/naruto_normal.png";
    public static final String NARUTO_SHOULDER_1 = "nineTailedResources/images/char/naruto/shoulder.png";
    public static final String NARUTO_SHOULDER_2 = "nineTailedResources/images/char/naruto/shoulder.png";
    public static final String NARUTO_CORPSE = "nineTailedResources/images/char/naruto/naruto_corpse.png";
    
    public static final String BADGE_IMAGE = "nineTailedResources/images/Badge.png";
    
    public static final String NARUTO_ATLAS = "nineTailedResources/images/char/naruto/DragonBones/naruto.atlas";
    public static final String NARUTO_JSON = "nineTailedResources/images/char/naruto/DragonBones/naruto.json";

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/images/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    public static String makeUIPath(String resourcePath) {
        return getModID() + "Resources/images/ui/" + resourcePath;
    }
    
    public NarutoMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
        
        setModID("nineTailed");
        
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + NineTailed.Enums.NARUTO_ORANGE.toString());
        
        BaseMod.addColor(NineTailed.Enums.NARUTO_ORANGE, ORANGE, ORANGE, ORANGE,
                ORANGE, ORANGE, ORANGE, ORANGE,
                ATTACK_NARUTO_ORANGE, SKILL_NARUTO_ORANGE, POWER_NARUTO_ORANGE, ENERGY_ORB_NARUTO_ORANGE,
                ATTACK_NARUTO_ORANGE_PORTRAIT, SKILL_NARUTO_ORANGE_PORTRAIT, POWER_NARUTO_ORANGE_PORTRAIT,
                ENERGY_ORB_NARUTO_ORANGE_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        
        
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
            
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }
    
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        
        InputStream in = NarutoMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        
        InputStream in = NarutoMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = NarutoMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        NarutoMod narutoMod = new NarutoMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + NineTailed.Enums.NARUTO.toString());
        
        BaseMod.addCharacter(new NineTailed("the Default", NineTailed.Enums.NARUTO),
                NARUTO_BUTTON, NARUTO_PORTRAIT, NineTailed.Enums.NARUTO);
        
        receiveEditPotions();
        logger.info("Added " + NineTailed.Enums.NARUTO.toString());
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        
        ModPanel settingsPanel = new ModPanel();
        
        
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {
                    
                    enablePlaceholder = button.enabled;
                    try {
                        
                        SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        
        settingsPanel.addUIElement(enableNormalsButton);
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);


        
        logger.info("Done loading badge Image and mod options");
    }
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        BaseMod.addPotion(Ramen.class, Color.ORANGE, Color.YELLOW, Color.YELLOW, Ramen.POTION_ID, NineTailed.Enums.NARUTO);
        BaseMod.addPotion(ToadOil.class, Color.GREEN, Color.CHARTREUSE, null, ToadOil.POTION_ID, NineTailed.Enums.NARUTO);
        BaseMod.addPotion(YangVessel.class, Color.WHITE, Color.WHITE, null, YangVessel.POTION_ID, NineTailed.Enums.NARUTO);
        
        logger.info("Done editing potions");
    }
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        BaseMod.addRelicToCustomPool(new SealedScroll(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new Bells(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new PaperFlowers(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new CrystalNecklace(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new ExplicitBook(), NineTailed.Enums.NARUTO_ORANGE);

        BaseMod.addRelicToCustomPool(new BullHorn(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new RedScarf(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new BrokenMask(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new SpiralScroll(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new Habanero(), NineTailed.Enums.NARUTO_ORANGE);
        BaseMod.addRelicToCustomPool(new RightHand(), NineTailed.Enums.NARUTO_ORANGE);

        UnlockTracker.markRelicAsSeen(SealedScroll.ID);
        UnlockTracker.markRelicAsSeen(Bells.ID);
        UnlockTracker.markRelicAsSeen(PaperFlowers.ID);
        UnlockTracker.markRelicAsSeen(CrystalNecklace.ID);
        UnlockTracker.markRelicAsSeen(ExplicitBook.ID);
        UnlockTracker.markRelicAsSeen(BullHorn.ID);
        UnlockTracker.markRelicAsSeen(RedScarf.ID);
        UnlockTracker.markRelicAsSeen(BrokenMask.ID);
        UnlockTracker.markRelicAsSeen(SpiralScroll.ID);
        UnlockTracker.markRelicAsSeen(Habanero.ID);
        UnlockTracker.markRelicAsSeen(RightHand.ID);

        logger.info("Done adding relics!");
    }
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        
        pathCheck();
        
        logger.info("Add variabls");
        
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");

        new AutoAdd("TheNineTailed")
                .packageFilter(AbstractDynamicCard.class)
                .setDefaultSeen(true)
                .cards();

        logger.info("Done adding cards!");
    }
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Card-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Power-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Relic-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Event-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Potion-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Character-Strings.json");
        
        
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-Orb-Strings.json");


        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/localization.zhs/NarutoMod-UI-Strings.json");

        
        logger.info("Done edittting strings");
    }
    
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/localization.zhs/NarutoMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveAddAudio() {
        addAudio("SUMMON", "summon");
        addAudio("CLONE_CHANNEL", "clone_channel");
        addAudio("CLONE_EVOKE", "clone_evoke");
        addAudio("RAIJIN", "raijin");
        addAudio("RASEN", "rasen");
        addAudio("RASENSHURIKEN", "rasenshuriken");
        addAudio("SUBSTITUTE", "substitute");
        addAudio("TAIL_CHANNEL", "tail_channel");
        addAudio("TAIL_EVOKE", "tail_evoke");
    }
    void addAudio(String audioKey, String fileName) {
        logger.info("adding nineTailedResources/audio/" + fileName + ".ogg");
        BaseMod.addAudio(makeID(audioKey), "nineTailedResources/audio/" + fileName + ".ogg");
    }

    public static String makeID(String idText) {
            return getModID() + ":" + idText;
        }
}
