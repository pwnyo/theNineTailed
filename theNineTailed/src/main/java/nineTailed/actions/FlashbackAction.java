package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.NarutoMod;

public class FlashbackAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(NarutoMod.makeID("FlashbackAction"));
    AbstractPlayer p;

    public FlashbackAction() {
        p = AbstractDungeon.player;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (this.p.discardPile.size() <= 1) {
                    if (this.p.discardPile.size() == 1) {
                        AbstractCard tmp = this.p.discardPile.getTopCard();
                        addToTop(new MakeTempCardInDrawPileAction(tmp.makeStatEquivalentCopy(), 1, true, true));
                    }
                    this.isDone = true;
                }
                else {
                    AbstractDungeon.gridSelectScreen.open(this.p.discardPile, 1, uiStrings.TEXT[0], false, false, false, false);
                    this.tickDuration();
                }
                return;
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, true));
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.tickDuration();
        }
    }
}
