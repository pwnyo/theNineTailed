package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import nineTailed.orbs.Clone;

import java.util.Iterator;

public class MultiCloneAction extends AbstractGameAction {
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;
    AbstractPlayer p;

    public MultiCloneAction(int amount) {
        this.actionType = ActionType.DISCARD;
        this.amount = amount;
        p = AbstractDungeon.player;
        this.duration = DURATION;
    }

    public void update() {
        AbstractCard c;
        if (this.duration == DURATION) {// 42
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 43
                this.isDone = true;// 44
                return;// 45
            }

            if (this.p.hand.size() <= 0) {
                this.tickDuration();
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, true, true);
            AbstractDungeon.player.hand.applyPowers();// 84
            this.tickDuration();// 85
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 92
            int count = 0;

            for (AbstractCard ca : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(ca);// 96
                ca.triggerOnManualDiscard();// 97
                GameActionManager.incrementDiscard(false);// 98
                count++;
            }
            for (int i = 0; i < count; i++) {
                addToBot(new ChannelAction(new Clone()));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 100
        }

        this.tickDuration();
    }
}