package nineTailed.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ChakraArmsAction extends AbstractGameAction {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    private static String[] TEXT = uiStrings.TEXT;

    public ChakraArmsAction(int bonus) {
        this.actionType = ActionType.DRAW;
        this.amount = bonus;
    }

    public void update() {
        addToTop(new DrawCardAction(EnergyPanel.totalCount + amount));
        this.isDone = true;
    }
}
