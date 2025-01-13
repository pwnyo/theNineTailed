package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class SerenityAction extends AbstractGameAction {
    AbstractPlayer p;

    public SerenityAction(int amount) {
        this.amount = amount;
        p = AbstractDungeon.player;
    }

    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&
                (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type == AbstractCard.CardType.SKILL) {
            this.addToTop(new ApplyPowerAction(p, p, new MantraPower(p, amount)));
        }

        this.isDone = true;
    }
}
