package nineTailed.cards.placeholder;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import nineTailed.NarutoMod;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;

@AutoAdd.Ignore
public class DefaultSecondMagicNumberSkill extends AbstractDynamicCard {
    
    public static final String ID = NarutoMod.makeID(DefaultSecondMagicNumberSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;
    
    private static final int COST = 1;
    
    private static final int VULNERABLE = 2;
    private static final int UPGRADE_PLUS_VULNERABLE = 3;
    
    private static final int POISON = 4;
    private static final int UPGRADE_PLUS_POISON = 5;
    
    public DefaultSecondMagicNumberSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = VULNERABLE;
        magicNumber2 = baseMagicNumber2 = POISON;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), this.magicNumber));
        
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber2), this.magicNumber2));
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
            this.upgradeMagic2(UPGRADE_PLUS_POISON);
            this.initializeDescription();
        }
    }
}