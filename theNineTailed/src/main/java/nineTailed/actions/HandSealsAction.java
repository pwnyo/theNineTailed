package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class HandSealsAction extends AbstractGameAction {
    AbstractPlayer p;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;

    public HandSealsAction() {
        this.actionType = ActionType.DRAW;
        p = AbstractDungeon.player;
        amount = 1;
    }

    public void update() {
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() <= 1) {
                AbstractCard c = this.p.hand.getTopCard();
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();

                GameActionManager.incrementDiscard(false);
                addToTop(new DrawCardAction(c.costForTurn));

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (this.p.hand.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();

                GameActionManager.incrementDiscard(false);
                addToTop(new DrawCardAction(c.costForTurn));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
}

