package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.orbs.Clone;

public class TeamAction extends AbstractGameAction {
    AbstractPlayer p;
    boolean isOptional;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;

    public TeamAction(int count, boolean optional) {
        this.actionType = ActionType.DISCARD;
        p = AbstractDungeon.player;
        amount = count;
        isOptional = optional;
        duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() <= amount) {
                int size = AbstractDungeon.player.hand.size();
                for (int i = 0; i < size; i++) {
                    addToTop(new ChannelAction(new Clone()));
                }
                addToTop(new DiscardAction(p, p, size, false));

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (this.p.hand.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, isOptional);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                discardAndMakeClone(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
    void discardAndMakeClone(AbstractCard c)
    {
        this.p.hand.moveToDiscardPile(c);
        c.triggerOnManualDiscard();

        GameActionManager.incrementDiscard(false);

        addToTop(new ChannelAction(new Clone()));
    }
}
