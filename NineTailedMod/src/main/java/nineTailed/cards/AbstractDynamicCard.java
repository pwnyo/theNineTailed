package nineTailed.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nineTailed.cards.placeholder.AbstractDefaultCard;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

    protected final CardStrings cardStrings;

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {
        
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        cardStrings = languagePack.getCardStrings(id);
    }

    public void dmg(AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        dmg(m, damage, effect);
    }
    public void dmg(AbstractMonster m, int dmg, AbstractGameAction.AttackEffect effect) {
        addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, dmg), effect));
    }
    public void block() {
        addToBot(new GainBlockAction(AbstractDungeon.player, block));
    }
    public void glow(boolean setting) {
        if (setting) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}