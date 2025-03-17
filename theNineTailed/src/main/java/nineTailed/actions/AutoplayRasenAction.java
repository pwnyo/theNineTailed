package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nineTailed.patches.CustomTags;

public class AutoplayRasenAction extends AbstractGameAction {
    private boolean playAll;
    public AutoplayRasenAction(boolean playAll) {
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.playAll = playAll;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (playAll) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (!c.hasTag(CustomTags.RASEN)) {
                        continue;
                    }
                    autoPlay(c);
                }
            }
            else {
                CardGroup rasens = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.hasTag(CustomTags.RASEN)) {
                        rasens.addToTop(c);
                    }
                }
                if (rasens.size() > 0) {
                    autoPlay(rasens.getRandomCard(AbstractDungeon.cardRandomRng));
                }
            }
        }

        this.tickDuration();
    }
    void autoPlay(AbstractCard c) {
        c.freeToPlayOnce = true;
        switch (c.target) {
            case SELF_AND_ENEMY:
            case ENEMY:
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getRandomMonster()));
                break;
            case SELF:
            case ALL:
            case ALL_ENEMY:
            case NONE:
            default:
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null));
        }
    }
}
