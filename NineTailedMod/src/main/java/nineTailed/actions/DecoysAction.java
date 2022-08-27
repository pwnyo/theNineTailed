package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

public class DecoysAction extends AbstractGameAction {
    private int block;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;
    AbstractPlayer p;

    public DecoysAction(int amount, int block) {
        this.actionType = ActionType.DISCARD;
        this.block = block;
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

            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();

                for(int i = 0; i < this.p.hand.size(); i++) {// 52
                    c = this.p.hand.getTopCard();// 53
                    this.p.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();

                    GameActionManager.incrementDiscard(false);// 58
                }

                AbstractDungeon.player.hand.applyPowers();// 61
                this.tickDuration();// 62
                return;// 63
            }
            if (this.amount < 0) {// 73
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);// 74
                AbstractDungeon.player.hand.applyPowers();// 75
                this.tickDuration();// 76
                return;// 77
            }

            if (this.p.hand.size() > this.amount) {// 80
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);// 81
            }

            AbstractDungeon.player.hand.applyPowers();// 84
            this.tickDuration();// 85
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 92
            int count = 0;

            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();// 95

            while(var4.hasNext()) {
                c = (AbstractCard)var4.next();
                this.p.hand.moveToDiscardPile(c);// 96
                c.triggerOnManualDiscard();// 97
                GameActionManager.incrementDiscard(false);// 98
                count++;
            }
            addToBot(new GainBlockAction(p, count * block));

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 100
        }

        this.tickDuration();
    }
}