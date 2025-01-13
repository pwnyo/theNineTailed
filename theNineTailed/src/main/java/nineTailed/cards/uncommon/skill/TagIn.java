package nineTailed.cards.uncommon.skill;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.purple.Wish;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AttackPotion;
import nineTailed.cards.AbstractDynamicCard;
import nineTailed.cards.temp.tailChoices.*;
import nineTailed.characters.NineTailed;
import nineTailed.orbs.biju.Tail2;
import nineTailed.orbs.biju.Tail4;

import java.util.ArrayList;
import java.util.Iterator;

import static nineTailed.NarutoMod.makeCardPath;
import static nineTailed.NarutoMod.makeID;

public class TagIn extends AbstractDynamicCard {
    public final static String ID = makeID(TagIn.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = NineTailed.Enums.NARUTO_ORANGE;

    private static final int COST = 1;

    public TagIn() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new Tail1Choice());
        choices.add(new Tail2Choice());
        choices.add(new Tail3Choice());
        choices.add(new Tail4Choice());
        choices.add(new Tail5Choice());
        choices.add(new Tail6Choice());
        choices.add(new Tail7Choice());
        choices.add(new Tail8Choice());
        choices.add(new Tail9Choice());
        for (int i = 0; i < 6; i++) {
            choices.remove(AbstractDungeon.cardRandomRng.random(choices.size() - 1));
        }
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}