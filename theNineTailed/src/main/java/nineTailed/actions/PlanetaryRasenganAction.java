package nineTailed.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.patches.CustomTags;

public class PlanetaryRasenganAction extends AbstractGameAction {
    public PlanetaryRasenganAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        CardGroup rasens = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomTags.RASEN)) {
                rasens.addToTop(c);
            }
        }
        if (rasens.size() > 0) {
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(rasens.getRandomCard(AbstractDungeon.cardRandomRng), AbstractDungeon.getRandomMonster()));
        }
        this.isDone = true;
    }
}