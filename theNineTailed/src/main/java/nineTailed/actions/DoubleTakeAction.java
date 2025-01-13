package nineTailed.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Pray;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DoubleTakeAction extends AbstractGameAction {
    AbstractPlayer p;
    boolean upgraded;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;

    public DoubleTakeAction(int amount) {
        this.actionType = AbstractGameAction.ActionType.DRAW;
        p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() <= 1) {
                AbstractCard c = this.p.hand.getTopCard();
                discardAndCopy(c);

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
                discardAndCopy(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
    void discardAndCopy(AbstractCard c)
    {
        this.p.hand.moveToDiscardPile(c);
        c.triggerOnManualDiscard();

        GameActionManager.incrementDiscard(false);

        addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), amount, true, true));
    }
}