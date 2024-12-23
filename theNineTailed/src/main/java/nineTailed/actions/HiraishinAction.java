package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.NarutoMod;
import nineTailed.cards.temp.FlyingRaijin;

public class HiraishinAction  extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;

    public HiraishinAction() {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);// 67
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                makeCard(p.hand.getTopCard().makeStatEquivalentCopy());
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                makeCard(c.makeStatEquivalentCopy());
            }

            this.p.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    void makeCard(AbstractCard c) {
        this.addToTop(new MakeTempCardInHandAction(new FlyingRaijin(c)));
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(NarutoMod.makeUIPath("HiraishinAction"));
        TEXT = uiStrings.TEXT;
    }
}
