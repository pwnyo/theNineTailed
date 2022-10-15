package nineTailed.cards.common.skill;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.EmptyBodyAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.cards.blue.Fission;
import com.megacrit.cardcrawl.cards.blue.MultiCast;
import com.megacrit.cardcrawl.cards.blue.Recursion;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class Regroup extends AbstractDynamicCard {
    public final static String ID = makeID(Regroup.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public Regroup() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //(new ApplyPowerAction(p, p, new MantraPower(p, magicNumber)));

        addToBot(new AnimateOrbAction(1));
        addToBot(new EvokeOrbAction(1));
        if (upgraded) {
            addToBot(new AnimateOrbAction(1));
            addToBot(new EvokeWithoutRemovingOrbAction(1));
        }
        addToBot(new NotStanceCheckAction(NeutralStance.STANCE_ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        addToBot(new ChangeStanceAction(NeutralStance.STANCE_ID));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}