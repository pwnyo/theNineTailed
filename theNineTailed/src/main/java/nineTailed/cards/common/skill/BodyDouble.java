package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ImpulseAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class BodyDouble extends AbstractDynamicCard {
    public final static String ID = makeID(BodyDouble.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public BodyDouble() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        addToBot(new IncreaseMaxOrbAction(magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            exhaust = false;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}