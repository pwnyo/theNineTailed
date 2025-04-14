package nineTailed.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.NarutoMod;

public class Ramen extends AbstractPotion {

    public static final String POTION_ID = NarutoMod.makeID(Ramen.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public Ramen() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FRUIT);
        labOutlineColor = Color.ORANGE;
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.clear();
        tips.add(new PowerTip(name, description));

        String vigorName = GameDictionary.VIGOR.NAMES[0];
        tips.add(new PowerTip(TipHelper.capitalize(vigorName), GameDictionary.keywords.get(vigorName)));

        String blockName = GameDictionary.BLOCK.NAMES[0];
        tips.add(new PowerTip(TipHelper.capitalize(blockName), GameDictionary.keywords.get(blockName)));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            target = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new VigorPower(target, potency), potency));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(target, potency));
        }
    }

    @Override
    public int getPotency(final int potency) {
        return 6;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new Ramen();
    }
}
