package nineTailed.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import nineTailed.NarutoMod;
import nineTailed.orbs.Truthseeker;

public class YangVessel extends AbstractPotion {

    public static final String POTION_ID = NarutoMod.makeID(YangVessel.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public YangVessel() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPHERE, PotionColor.WHITE);
        potency = getPotency();
        description = DESCRIPTIONS[0];
        labOutlineColor = Color.ORANGE;
        isThrown = false;
    }

    @Override
    public void initializeData() {
        potency = getPotency();
        if (potency == 1) {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        else {
            this.description = potionStrings.DESCRIPTIONS[1] + potency + potionStrings.DESCRIPTIONS[2];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (int i = 0; i < potency; i++) {
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Truthseeker()));
            }
        }
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new YangVessel();
    }
}
