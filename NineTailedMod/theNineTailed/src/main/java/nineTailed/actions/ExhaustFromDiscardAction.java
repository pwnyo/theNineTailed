package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import nineTailed.NarutoMod;

public class ExhaustFromDiscardAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean optional;

    public ExhaustFromDiscardAction(int amount, boolean optional) {
        this.actionType = ActionType.EXHAUST;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.optional = optional;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!this.p.discardPile.isEmpty() && this.amount > 0) {
                if (this.p.discardPile.size() <= this.amount && !this.optional) {

                    for (int i = 0; i < p.discardPile.size(); ++i) {
                        AbstractCard c = p.discardPile.getTopCard();
                        addToBot(new VFXAction(new ExhaustCardEffect(c)));
                        p.discardPile.moveToExhaustPile(c);
                    }

                    this.isDone = true;
                } else {
                    if (this.amount == 1) {
                        if (this.optional) {
                            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, true, TEXT[0]);
                        } else {
                            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, TEXT[0], false);
                        }
                    } else if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, true, TEXT[1] + this.amount + TEXT[2]);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, TEXT[1] + this.amount + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                NarutoMod.logger.info("d2e else");
                this.isDone = true;
            }
        } else {
            NarutoMod.logger.info("d2e megaelse");
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {

                for (int i = 0; i < AbstractDungeon.gridSelectScreen.selectedCards.size(); ++i) {
                    AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(i);
                    addToBot(new VFXAction(new ExhaustCardEffect(c)));
                    p.discardPile.moveToExhaustPile(c);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }

            this.tickDuration();

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT;
    }
}
