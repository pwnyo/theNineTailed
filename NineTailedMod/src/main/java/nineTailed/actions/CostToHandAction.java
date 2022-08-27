package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class CostToHandAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private int targetCost;

    public CostToHandAction(int numberOfCards, boolean optional, int targetCost) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;

        this.targetCost = targetCost;
    }

    public CostToHandAction(int numberOfCards, int targetCost) {
        this(numberOfCards, false, targetCost);
    }

    public void update() {
        AbstractCard card;
        if (this.duration == Settings.ACTION_DUR_MED) {// 28
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);// 29
            Iterator var5 = this.player.discardPile.group.iterator();// 30

            while(var5.hasNext()) {
                AbstractCard c = (AbstractCard)var5.next();
                if (c.cost == targetCost) {// 31
                    tmp.addToRandomSpot(c);// 32
                }
            }

            if (tmp.size() == 0) {// 36
                this.isDone = true;// 37
            } else if (tmp.size() == 1) {// 39
                card = tmp.getTopCard();// 40
                if (this.player.hand.size() == 10) {// 42
                    //this.player.discardPile.moveToDiscardPile(card);
                    this.player.createHandIsFullDialog();
                } else {
                    card.unhover();// 46
                    card.lighten(true);// 47
                    card.setAngle(0.0F);// 48
                    card.drawScale = 0.12F;// 49
                    card.targetDrawScale = 0.75F;// 50
                    card.current_x = CardGroup.DISCARD_PILE_X;// 51
                    card.current_y = CardGroup.DISCARD_PILE_Y;// 52
                    //this.player.discardPile.removeCard(card);// 53
                    AbstractDungeon.player.hand.addToTop(card);// 54
                    AbstractDungeon.player.hand.refreshHandLayout();// 55
                    AbstractDungeon.player.hand.applyPowers();// 56
                }

                this.isDone = true;// 58
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);// 62
                this.tickDuration();// 63
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {// 69
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();// 70

                while(var1.hasNext()) {
                    card = (AbstractCard)var1.next();
                    card.unhover();// 71
                    if (this.player.hand.size() == 10) {// 73
                        //this.player.discardPile.moveToDiscardPile(card);
                        this.player.createHandIsFullDialog();// 75
                    } else {
                        //this.player.discardPile.removeCard(card);// 77
                        this.player.hand.addToTop(card);// 78
                    }

                    this.player.hand.refreshHandLayout();// 80
                    this.player.hand.applyPowers();// 81
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();// 83
                this.player.hand.refreshHandLayout();// 84
            }

            this.tickDuration();// 87
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
