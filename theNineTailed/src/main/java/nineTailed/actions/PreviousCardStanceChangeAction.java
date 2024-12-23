package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class PreviousCardStanceChangeAction extends AbstractGameAction {
    private AbstractCard.CardType typeRequirement;
    private AbstractStance targetStance;

    public PreviousCardStanceChangeAction(AbstractCard.CardType typeRequirement, AbstractStance targetStance) {
        this.typeRequirement = typeRequirement;
        this.targetStance = targetStance;
    }

    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&
                (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type == typeRequirement) {
            addToTop(new ChangeStanceAction(targetStance.ID));
        }

        this.isDone = true;
    }
}
