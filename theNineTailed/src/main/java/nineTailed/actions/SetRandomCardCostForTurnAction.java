package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SetRandomCardCostForTurnAction extends AbstractGameAction {
    private AbstractPlayer p;
    private boolean forCombat = false;

    public SetRandomCardCostForTurnAction(int cost) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = cost;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup highCost = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : p.hand.group)
            {
                if (c.costForTurn > 1)
                {
                    highCost.addToTop(c);
                }
            }
            for (int i = 0; i < Math.min(highCost.size(), amount); i++) {
                AbstractCard c = highCost.getRandomCard(AbstractDungeon.cardRandomRng);
                c.costForTurn = this.amount;
                c.isCostModifiedForTurn = true;
                highCost.removeCard(c);
            }
        }

        this.tickDuration();
    }
}
