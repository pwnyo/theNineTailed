package nineTailed.cards.placeholder;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import nineTailed.NarutoMod;
import nineTailed.actions.UncommonPowerAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;

@AutoAdd.Ignore
public class DefaultUncommonPower extends AbstractDynamicCard {
    
    public static final String ID = NarutoMod.makeID(DefaultUncommonPower.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");
    
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;
    
    private static final int COST = -1;
    private static final int MAGIC = 1;
    
    public DefaultUncommonPower() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        AbstractDungeon.actionManager.addToBottom(new UncommonPowerAction(p, m, magicNumber,
                upgraded, damageTypeForTurn, freeToPlayOnce, energyOnUse));
    }
    
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}