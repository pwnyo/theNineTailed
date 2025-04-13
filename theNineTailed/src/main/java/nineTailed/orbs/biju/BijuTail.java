package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nineTailed.NarutoMod;
import nineTailed.orbs.Tail;
import nineTailed.util.TextureLoader;

import java.util.ArrayList;

import static nineTailed.NarutoMod.makeOrbPath;

public abstract class BijuTail extends Tail {
    private final OrbStrings orbString;
    protected final String[] DESC;

    public BijuTail(int passiveAmount, String orbId, String imgPath) {
        this(passiveAmount, 1, orbId, imgPath);
    }

    public BijuTail(int passiveAmount, int evokeAmount, String orbId, String imgPath) {
        super(passiveAmount, evokeAmount);
        ID =  NarutoMod.makeID(orbId);
        img = TextureLoader.getTexture(makeOrbPath(imgPath + ".png"));

        orbString = CardCrawlGame.languagePack.getOrbString(ID);
        DESC = orbString.DESCRIPTION;
        name = orbString.NAME;

        updateDescription();
    }

    @Override
    public void onStartOfTurn() {
        flash();
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    protected void gainPower(AbstractPlayer p, AbstractPower pow) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, pow));
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESC[0] + passiveAmount + DESC[1];
    }

    public static BijuTail getRandomBijuTail() {
        ArrayList<BijuTail> orbs = new ArrayList<>();
        orbs.add(new Tail1());
        orbs.add(new Tail2());
        orbs.add(new Tail3());
        orbs.add(new Tail4());
        orbs.add(new Tail5());
        orbs.add(new Tail6());
        orbs.add(new Tail7());
        orbs.add(new Tail8());
        return orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1));
    }
}
