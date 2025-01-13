package nineTailed.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlyingRaijinAction extends AbstractGameAction {
    AbstractCard cardTarget;
    AbstractPlayer p;

    public FlyingRaijinAction(AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        cardTarget = card;
        p = AbstractDungeon.player;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean found = false;
        if (p.drawPile.contains(cardTarget))
        {
            getCard(p.drawPile);
            found = true;
        }
        if (!found && p.discardPile.contains(cardTarget))
        {
            getCard(p.discardPile);
        }

        this.isDone = true;
    }
    void getCard(CardGroup group)
    {
        p.hand.addToTop(cardTarget);
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
    }
}