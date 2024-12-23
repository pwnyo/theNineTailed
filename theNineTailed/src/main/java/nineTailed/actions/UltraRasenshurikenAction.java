package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.patches.CustomTags;

public class UltraRasenshurikenAction extends AbstractGameAction {
    public UltraRasenshurikenAction() {
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.hasTag(CustomTags.RASEN)) {
                    continue;
                }
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
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, (AbstractMonster) null));
                }

            }
        }

        this.tickDuration();
    }
}
