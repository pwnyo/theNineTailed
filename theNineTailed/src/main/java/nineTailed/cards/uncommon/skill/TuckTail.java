package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.blue.Glacier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;
import nineTailed.actions.TailBlockAction;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Tail;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class TuckTail extends AbstractDynamicCard {
    public final static String ID = makeID(TuckTail.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 2;

    public TuckTail() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = 9;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new ChannelAction(new Tail()));
        }
        //addToBot(new TailBlockAction(block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}