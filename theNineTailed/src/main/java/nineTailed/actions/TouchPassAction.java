package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class TouchPassAction  extends AbstractGameAction {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GamblingChipAction");
    private static String[] TEXT = uiStrings.TEXT;
    private int bonus;

    public TouchPassAction(int amount, int bonus) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.bonus = bonus;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[1], amount, false, true);

            this.addToBot(new WaitAction(0.25F));
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    this.addToTop(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.handCardSelectScreen.selectedCards.group.size() + bonus));
                    for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                        AbstractDungeon.player.hand.moveToDiscardPile(c);
                        GameActionManager.incrementDiscard(false);
                        c.triggerOnManualDiscard();
                    }
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

        }
        this.tickDuration();
    }
}
