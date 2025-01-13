package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.actions.unique.NightmareAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Nightmare;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.NarutoMod;
import nineTailed.cards.temp.FlyingRaijin;

public class HiraishinAction  extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(NarutoMod.makeID("HiraishinAction"));
    private AbstractPlayer p;

    public HiraishinAction() {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], 1, false, false, false, false);// 67
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                makeCard(p.hand.getTopCard());
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                makeCard(c);
            }

            this.p.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    void makeCard(AbstractCard c) {
        AbstractDungeon.player.hand.addToHand(c);
        this.addToTop(new MakeTempCardInHandAction(new FlyingRaijin(c)));
    }
}
