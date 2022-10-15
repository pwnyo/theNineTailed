package nineTailed.cards.common.attack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.Tail;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class WildSwipe extends AbstractDynamicCard {
    public final static String ID = makeID(WildSwipe.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public WildSwipe() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new ChannelAction(new Tail()));
        addToBot(new DiscardAction(p, p, 1, !upgraded));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}