package nineTailed.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.patches.HiraishinModifier;

import static nineTailed.NarutoMod.makeID;

public class HiraishinGrabAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BetterToHandAction");
    AbstractPlayer p;
    int drawCount = 0, discardCount = 0, exhaustCount = 0;
    CardGroup combined;

    public HiraishinGrabAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (p.drawPile.isEmpty() && p.discardPile.isEmpty() && p.exhaustPile.isEmpty()) {
                this.isDone = true;
            } else {
                CardGroup sortedDraw = makeSortedHiraishinGroup(p.drawPile);
                CardGroup sortedDiscard = makeSortedHiraishinGroup(p.discardPile);
                CardGroup sortedExhaust = makeSortedHiraishinGroup(p.exhaustPile);
                drawCount = sortedDraw.group.size();
                discardCount = sortedDiscard.group.size();
                exhaustCount = sortedExhaust.group.size();

                combined = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                if (drawCount > 0) {
                    for (AbstractCard c : sortedDraw.group) {
                        combined.addToTop(c);
                    }
                }
                if (discardCount > 0) {
                    for (AbstractCard c : sortedDiscard.group) {
                        combined.addToTop(c);
                    }
                }
                if (exhaustCount > 0) {
                    for (AbstractCard c : sortedExhaust.group) {
                        combined.addToTop(c);
                    }
                }

                if (combined.group.size() == 0) {
                    isDone = true;
                    return;
                }
                else if (combined.group.size() == 1) {
                    getCardFromIndex(combined.getTopCard(), 0);
                    isDone = true;
                    return;
                }

                AbstractDungeon.gridSelectScreen.open(combined, 1, uiStrings.TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    int index = combined.group.indexOf(c);
                    getCardFromIndex(c, index);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
    public CardGroup makeSortedHiraishinGroup(CardGroup parentGroup) {
        CardGroup group  = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if (!parentGroup.isEmpty()) {
            for (AbstractCard c : parentGroup.group) {
                if (CardModifierManager.hasModifier(c, HiraishinModifier.ID)) {
                    group.addToTop(c);
                }
            }
            group.sortAlphabetically(true);
            group.sortByRarityPlusStatusCardType(false);
        }

        return group;
    }
    void getCardFromIndex(AbstractCard c, int index) {
        //considering combined is a grouping of draw-discard-exhaust piles,
        //we can determine the appropriate pile by adding pile counts until
        //we get a number higher than the index
        CardCrawlGame.sound.play(makeID("RAIJIN"), 0.1f);
        if (drawCount > index) {
            getCard(c, p.drawPile);
        }
        else if (drawCount + discardCount > index) {
            getCard(c, p.discardPile);
        }
        else if (drawCount + discardCount + exhaustCount > index) {
            getCard(c, p.exhaustPile);
        }
        else {
            //shouldn't happen...
        }
    }
    void getCard(AbstractCard c, CardGroup group)
    {
        p.hand.addToTop(c);
        group.removeCard(c);

        c.lighten(false);
        c.unhover();

        p.hand.refreshHandLayout();
        p.hand.applyPowers();
    }
}