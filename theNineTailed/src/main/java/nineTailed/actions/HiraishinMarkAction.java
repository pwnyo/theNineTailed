package nineTailed.actions;

import basemod.helpers.CardModifierManager;
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
import nineTailed.patches.HiraishinModifier;

public class HiraishinMarkAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(NarutoMod.makeID("HiraishinMarkAction"));
    private AbstractPlayer p;

    public HiraishinMarkAction() {
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
                CardModifierManager.addModifier(p.hand.getTopCard(), new HiraishinModifier());
                addToTop(new MakeTempCardInHandAction(new FlyingRaijin()));
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
            AbstractDungeon.player.hand.addToHand(tmpCard);
            CardModifierManager.addModifier(tmpCard, new HiraishinModifier());
            addToTop(new MakeTempCardInHandAction(new FlyingRaijin()));

            this.p.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            this.isDone = true;
        }

        this.tickDuration();
    }
}
