package nineTailed.orbs.biju;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;


public class Tail1 extends BijuTail {
    public Tail1() {
        super(1,"Tail1", "tail1");
    }

    @Override
    public void onStartOfTurn() {
        gainPower(p, new PlatedArmorPower(p, passiveAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Tail1();
    }
}
